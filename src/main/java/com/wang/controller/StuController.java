package com.wang.controller;

import com.wang.common.Response;
import com.wang.common.Status;
import com.wang.model.Student;
import com.wang.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hppc on 2017/4/11.
 */
@Controller
@RequestMapping("/stu")
public class StuController {
    @Autowired
    StuService stuService;
    @RequestMapping(value = "/oper",method = RequestMethod.POST)
    @ResponseBody
    public Response checkDel(@RequestParam(required = false) String stuId,@RequestParam String oper,@RequestParam(required = false) String name,@RequestParam(required = false) String sex,
                           @RequestParam(required = false) String major,@RequestParam(required = false) String academy,@RequestParam(required = false) String entry_year,
                           @RequestParam(required = false) String hometown,@RequestParam(required = false) String phone,@RequestParam(required = false) String id){
        System.out.println(oper);
        if (oper.equals("add")) {   //用equals判断 ==只是判断地址是否相同
            System.out.println("执行添加");
            Student student = new Student(stuId,name,sex,major,academy,entry_year,hometown,phone);
            String res = stuService.addStudent(student);
            if (res == "添加成功"){
                System.out.println("添加成功");
                return new Response(Status.SUCCESS);
            } else {
                System.out.println("添加失败");
                return new Response(Status.SERVER_ERROR);
            }
        } else if (oper.equals("del")){
            //执行删除
            String res = stuService.delStu(Integer.valueOf(id));  //将id转换成int 传过来的参数是string
            if (res.equals("删除成功")) {
                return new Response(Status.SUCCESS);
            } else {
                return new Response(Status.DB_ERROR);
            }
        } else {
            Student student = new Student(stuId,name,sex,major,academy,entry_year,hometown,phone,Integer.valueOf(id));
            System.out.println("执行修改");
            String res = stuService.setStu(student);
            if (res.equals("修改成功")) {
                return new Response(Status.SUCCESS);
            } else {
                return new Response(Status.DB_ERROR);
            }
        }
    }
}
