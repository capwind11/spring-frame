package com.allen.spring.db.mybatis;

/**
 * @author allenyzhang
 * @time 2020/9/14, 22:01
 */

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * mybatis plus代码生成器
 * 直接右键run即可
 * 如果有连接多个数据库，可以复制本工具类，每个数据库一个，省的一直改连接配置
 * 注意点
 * 1:xml 会生成在java 代码的mybatis/ext中，需要自己移动到resource目录
 * 2：生成的entity缺失 @TableName("{表名}") 需要自己补上
 *
 * @author daochuzhang
 * @time 2019/8/30 17:01
 */
public class MybatisPlusGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/db-operation/src/main/java");
        gc.setAuthor("allenyzhang");
        gc.setOpen(false);
        //允许覆盖生成
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);
        //应对低版本MYSQL，使用java.util.date
        gc.setDateType(DateType.ONLY_DATE);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        //根据需要修改数据库连接
        dsc.setUrl("jdbc:mysql://103.26.79.35:3306/teaching?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("user");
        dsc.setPassword("123");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.allen.spring.db");
        pc.setController("controller");
        pc.setEntity("dao.entity");
        pc.setMapper("dao.mapper");
        pc.setService("biz");
        pc.setServiceImpl("biz.impl");
        //xml只需要包含连表部分操作，这里会弄到java代码路径，需要手工移动，如果是多数据源，需要自己加数据源目录，比如mybatis/ndb
        pc.setXml("mybatis");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";*/

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
//                checkDir(filePath);
                //对于已存在的文件，只需重复生成 entity 和 mapper.xml
                File file = new File(filePath);
                boolean exist = file.exists();
                if (exist) {
                    //只有实体需要重新生产，xml只有ext部分都是自己手写的,不需要重新生产，要自己手改
                    if (FileType.ENTITY == fileType) {
                        return true;
                    } else {
                        return false;
                    }
                }
                //不存在的文件只创建Mapper,entity 和 mapper.xml
                else {
                    if (FileType.ENTITY == fileType || FileType.MAPPER == fileType || FileType.XML == fileType) {
                        return true;
                    } else {
                        return false;
                    }

                }
            }
        });

//        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

//        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //关闭Lombok
        strategy.setEntityLombokModel(false);
        //填写表字段
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        //不用生产Controller
        strategy.setRestControllerStyle(false);
        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}

