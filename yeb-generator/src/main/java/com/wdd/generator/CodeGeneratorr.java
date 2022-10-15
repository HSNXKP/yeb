package com.wdd.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wdd
 *
 */
public class CodeGeneratorr {
    public static void main(String[] args) {
        List<String > tables = new ArrayList<>();
        tables.add("t_admin");
        tables.add("t_admin_role");
        tables.add("t_appraise");
        tables.add("t_department");
        tables.add("t_employee");
        tables.add("t_employee_ec");
        tables.add("t_employee_remove");
        tables.add("t_employee_train");
        tables.add("t_joblevel");
        tables.add("t_mail_log");
        tables.add("t_menu");
        tables.add("t_nation");
        tables.add("t_oplog");
        tables.add("t_politics_status");
        tables.add("t_position");
        tables.add("t_role");
        tables.add("t_role_menu");
        tables.add("t_salary");
        tables.add("t_salary_adjust");
        tables.add("t_sys_msg");
        tables.add("t_sys_msg_content");



        FastAutoGenerator.create("jdbc:mysql://localhost:3306/yeb?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true","root","root")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("wdd")
                            .enableSwagger()
                            .outputDir("D:\\src\\main\\java")
                            .fileOverride();
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.wdd")
                            .moduleName("generator")
                            .entity("pojo")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,"D:\\src\\main\\resource"));

                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .addTablePrefix("t_")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("delete")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .enableHyphenStyle()  //不使用驼峰
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap() //resultMap
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation() //@Mapper
                            .formatXmlFileName("%sMapper");


                })
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();




    }

}
