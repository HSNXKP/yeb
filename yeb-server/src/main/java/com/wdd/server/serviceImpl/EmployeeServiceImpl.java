package com.wdd.server.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdd.server.mapper.EmployeeMapper;
import com.wdd.server.mapper.MailLogMapper;
import com.wdd.server.pojo.*;
import com.wdd.server.service.EmployeeService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MailLogMapper mailLogMapper;

    /**
     * 普通所有员工
     *
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
        return respPageBean;
    }

    /**
     * 获得工号
     * @return
     */
    @Override
    public RespBean maxWorkId() {
        //转化为Map格式的List 一条数据 key为max(workId) value为 值
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workId)"));
        //将获得的Map第一个值 通过key取值 取出toString后转化为Integer类型 再格式化为String类型
        //添加工号的情况下是获得最大的工号 +1以后就是添加以后的工号
        return RespBean.success(null,String.format("%08d",Integer.parseInt(maps.get(0).get("max(workId)").toString())+1));
    }

    /**
     * 增加员工
     * @param employee
     * @return
     */
    @Override
    public RespBean addEmployee(Employee employee) {
        //处理合同期限，保留两位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        //日期转化 转化为天数
        long days = beginContract.until(endContract,ChronoUnit.DAYS);
        //保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        //转化为年  并加入到合同期限中去
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));
        if (1==employeeMapper.insert(employee)){
            // 获取员工
            Employee emp = employeeMapper.getEmployeeExcel(employee.getId()).get(0);
            // 唯一标识符UUID
            String msgID = UUID.randomUUID().toString();
            MailLog mailLog = new MailLog();
            mailLog.setMsgId(msgID);
            mailLog.setEid(employee.getId());
            mailLog.setStatus(0);
            mailLog.setRouteKey(MailConstant.MAIL_ROUTING_KEY_NAME);
            mailLog.setExchange(MailConstant.MAIL_EXCHANGE_NAME);
            mailLog.setCount(0);
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstant.MSG_TIMEOUT));
            mailLog.setCreateTime(LocalDateTime.now());
            mailLog.setUpdateTime(LocalDateTime.now());
            // 消息入库
            mailLogMapper.insert(mailLog);
            // 发送消息
            rabbitTemplate.convertAndSend(MailConstant.MAIL_EXCHANGE_NAME,MailConstant.MAIL_ROUTING_KEY_NAME,emp,new CorrelationData(msgID));
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");

    }

    /**
     * 导出员工
     * @param id
     * @return
     */
    @Override
    public List<Employee> getEmployeeExcel(Integer id) {
        return employeeMapper.getEmployeeExcel(id);
    }

    /**
     * 获得员工账套
     * @return
     */

    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
        Page<Employee> page = new Page<>(currentPage,size);
        IPage<Employee> employeeIPage=employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
        return respPageBean;
    }

}
