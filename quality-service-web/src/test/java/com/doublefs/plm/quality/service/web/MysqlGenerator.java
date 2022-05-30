package com.doublefs.plm.quality.service.web;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.doublefs.plm.quality.service.data.entity.BaseEntity;
import com.doublefs.plm.quality.service.service.base.BaseService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class MysqlGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入").append(tip).append("：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        String[] models = {"pattern-service-data", "pattern-service-service", "pattern-service-web"};
        for (String model : models) {
            shell(model);
        }
    }

    private static void shell(String model) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        File file = new File(model);
        String path = file.getAbsolutePath();
        //path = path.substring(0, path.lastIndexOf(File.separator));
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(path + "/src/main/java");
        gc.setAuthor("");
        gc.setOpen(false);
        gc.setBaseResultMap(false);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setDateType(DateType.ONLY_DATE);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("Base%sService");
        gc.setServiceImplName("Base%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://cn-test-1.db.doublefs.com:3306/quality_service?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("quality_service");
        dsc.setPassword("sN]!akNzH8xR{x[+*6nM");
        mpg.setDataSource(dsc);

        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("temp");
//        pc.setParent("com.doublefs.plm.pattern.service.web");
//        mpg.setPackageInfo(pc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.doublefs.plm.pattern.service");
        pc.setController("web.controller");
        pc.setServiceImpl("service.base");
        pc.setMapper("data.mapper");
        pc.setEntity("data.entity");
        //pc.setModuleName("test");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        mpg.setCfg(cfg);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);

        if ("pattern-service-web".equals(model)) {
            tc.setEntity(null);
            tc.setService(null);
            tc.setServiceImpl(null);
            tc.setMapper(null);
        } else if ("pattern-service-data".equals(model)) {
            tc.setController(null);
            tc.setService(null);
            tc.setServiceImpl(null);
            List<FileOutConfig> focList = new ArrayList<>();
            //xml 文件配置
            focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return path + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                }
            });
            cfg.setFileOutConfigList(focList);
        } else if ("pattern-service-service".equals(model)) {
            tc.setController(null);
            tc.setMapper(null);
            tc.setEntity(null);
            tc.setService(null);
            tc.setServiceImpl(null);
        }
        mpg.setTemplate(tc);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        // mpg.setTemplate(tc);

        //库里的表明 pattern_table
//        scanner("表名");
        String tableName = "quality_control_task";
        //将表名下划线转驼峰  patternTable
        String tableNameCamelCase = StrUtil.toCamelCase(tableName);
        //将驼峰首字符大写，patternTable
        String upperFirst = StrUtil.upperFirst(tableNameCamelCase);

//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setXml(null);
//        templateConfig.setService("");
//        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperServiceImplClass(BaseService.class);
        strategy.setSuperMapperClass("com.doublefs.plm.pattern.service.data.mapper.AaBaseMapper");
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableName);
        strategy.setSuperEntityClass(BaseEntity.class);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntitySerialVersionUID(false);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}