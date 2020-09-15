package com.allen.spring.db.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author allenyzhang
 * @since 2020-09-14
 */
public class Stu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("num")
    private String num;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("grade")
    private String grade;

    @TableField("hobby")
    private String hobby;

    @TableField("dob")
    private Date dob;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Stu{" +
            "id=" + id +
            ", num=" + num +
            ", name=" + name +
            ", age=" + age +
            ", grade=" + grade +
            ", hobby=" + hobby +
            ", dob=" + dob +
        "}";
    }
}
