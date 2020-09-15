package com.allen.spring.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author allenyzhang
 * @time 2020/9/15, 15:33
 */
@SpringBootApplication(scanBasePackages = {"com.allen.spring.db"})//需要加入common
// 包扫描和自己工程扫描
@EnableTransactionManagement//事务管理器，如果不需要事务管理，可以删除
@EnableDiscoveryClient
public class DBApplication {
    public static void main(String[] args) {
        SpringApplication.run(DBApplication.class, args);
    }
}