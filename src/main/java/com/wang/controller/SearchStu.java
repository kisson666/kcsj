package com.wang.controller;

import com.wang.model.Student;
import com.wang.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hppc on 2017/4/11.
 */
@Controller
@RequestMapping("/search")
public class SearchStu {
    @Autowired
    StuService stuService;

    @RequestMapping("/all")
    @ResponseBody
    public List<Student> search_all(){
        System.out.println("查询所有学生信息");
        return  stuService.getAll();
    }

    @RequestMapping("/academy/{academy}")
    @ResponseBody
    public List<Student> search_academy(@PathVariable String academy){
        System.out.println("根据学院查询:"+academy);
        return stuService.getStuByAcademy(academy);
    }
    @RequestMapping("/major/{major}")
    @ResponseBody
    public List<Student> search_major(@PathVariable String major) {
        System.out.println("根据专业查询:"+major);
        return stuService.getStuByMajor(major);
    }
    @RequestMapping("/name/{name}")
    @ResponseBody
    public List<Student> search_name(@PathVariable String name) {
        System.out.println("根据姓名查询:"+name);
        return stuService.getStuByName(name);
    }
    @RequestMapping("/stuId/{stuId}")
    @ResponseBody
    public List<Student> search_stuId(@PathVariable String stuId) {
        System.out.println("根据学号查询:"+stuId);
        return stuService.getStuByStuId(stuId);
    }
    @RequestMapping("/sex/{sex}")
    @ResponseBody
    public List<Student> search_sex(@PathVariable String sex) {
        System.out.println("根据性别查询:"+sex);
        return stuService.getStuBySex(sex);
    }
}
