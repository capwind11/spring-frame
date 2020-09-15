package com.allen.spring.db.dao.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;

import java.util.List;

/**
 * mybatis plus 加入自定义方法
 *
 * @author zhangdaochuan
 * @time 2019/9/6 10:36
 */
public class CustomerSqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //增加自定义方法
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}
