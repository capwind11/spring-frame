package com.allen.spring.db.controller;

import com.allen.spring.db.dao.entity.Stu;
import com.allen.spring.db.dao.mapper.StuMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author allenyzhang
 * @time 2020/9/15, 15:27
 */
@RestController
public class testController {

    @Autowired
    private StuMapper stuMapper;

    @RequestMapping(method = RequestMethod.GET,value = "/getStudents")
    public List<Stu> getStudent() {
        QueryWrapper<Stu> stuQueryWrapper = new QueryWrapper<>();
        stuQueryWrapper.last("limit 10");
        List<Stu> stus = stuMapper.selectList(stuQueryWrapper);
        return stus;
    }
}
