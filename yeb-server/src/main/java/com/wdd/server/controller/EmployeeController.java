package com.wdd.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.wdd.server.pojo.*;
import com.wdd.server.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PoliticsStatusService politicsStatusService;

    @Autowired
    private JoblevelService joblevelService;

    @Autowired
    private NationService nationService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;




    @ApiOperation(value = "普通所有员工（员工）")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee,
                                    LocalDate[] beginDateScope){
        return employeeService.getEmployeeByPage(currentPage,size,employee,beginDateScope);
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获得所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJoblevels(){
        return joblevelService.list();
    }

    @ApiOperation(value = "获得所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.list();
    }

    @ApiOperation(value = "获得所有职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.list();
    }

    @ApiOperation(value = "获得所有部门")
    @GetMapping("/departments")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "获得工号")
    @GetMapping("/maxWorkId")
    public RespBean maxWorkId(){
        return employeeService.maxWorkId();
    }

    @ApiOperation(value = "增加员工")
    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public RespBean updateEmployee(@RequestBody Employee employee){
        if (employeeService.updateById(employee)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmployee(@PathVariable Integer id){
        if (employeeService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "导出员工")
    @GetMapping(value = "/export",produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response){
        List<Employee> list = employeeService.getEmployeeExcel(null);
        //导出以后的文件名字  系的名字  Excel的类型 HSSF对应.xls   XSSF 对应 .xlsx
        ExportParams params = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        //导出的文件名+ 需要导出的类 查询到的list
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, list);
        ServletOutputStream out=null;
        try {
            //输出设置成流的形式
            response.setHeader("content-type","application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("员工表.xls","UTF-8"));
             out = response.getOutputStream();
             workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=out){
                try {
                    out.close();
                } catch (IOException e) {
                  e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "导入员工")
    @PostMapping("/import")
    public RespBean importEmployee(MultipartFile file){
        //导出
        ImportParams params = new ImportParams();
        //消除标题
        params.setTitleRows(1);
        List<Nation> nations = nationService.list();
        List<PoliticsStatus> politicsStatuses = politicsStatusService.list();
        List<Department> departments = departmentService.list();
        List<Joblevel> joblevels = joblevelService.list();
        List<Position> positions = positionService.list();

        try {
            List<Employee> list = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            list.forEach(employee -> {
//                Nation nation = new Nation(employee.getNation().getName());
//                Nation build = Nation.builder()
//                        .name(employee.getNation().getName())
//                        .build();

                // 获得民族id
                employee.setNationId(nations.get( nations.indexOf(new Nation(employee.getNation().getName()))).getId());
                // 获得政治面貌id
                employee.setPoliticId(politicsStatuses.get( politicsStatuses.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                // 获得部门id
                employee.setDepartmentId(departments.get( departments.indexOf(new Department(employee.getDepartment().getName()))).getId());
                // 获得职称id
                employee.setJobLevelId(joblevels.get( joblevels.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId());
                // 获得职位id
                employee.setPosId(positions.get( positions.indexOf(new Position(employee.getPosition().getName()))).getId());
            });
            if (employeeService.saveBatch(list)){
                return RespBean.success("导入成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败");
    }

}
