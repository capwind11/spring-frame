package com.allen.spring.db.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author allenyzhang
 * @time 2020/9/14, 23:46
 */
@Configuration
@MapperScan(basePackages = DBConfig.PACKAGE,sqlSessionFactoryRef = "sqlSessionFactory")
public class DBConfig {

    static final String PACKAGE = "com.allen.spring.db.dao";

    static final String MAPPER_LOCATION = "classpath:mybatis/**/*.xml";

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "datasource")
    public DataSource setDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "globalConfig")
    public GlobalConfig setGlobalConfig() {
        GlobalConfig config = new GlobalConfig();
        config.setSqlInjector(new CustomerSqlInjector());
        return config;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource,
                                                  @Qualifier("globalConfig") GlobalConfig config) throws Exception {
        final MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setGlobalConfig(config);
        sessionFactoryBean.setTypeAliasesPackage(PACKAGE);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        sessionFactoryBean.setPlugins(new PaginationInterceptor());
        return sessionFactoryBean.getObject();

    }
}
