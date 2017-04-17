package com.wang.controller;

import com.wang.common.Response;
import com.wang.common.Status;
import com.wang.model.Student;
import com.wang.model.User;
import com.wang.service.StuService;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hppc on 2017/4/5.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    UserService userService;
    @Autowired
    StuService stuService;
    @RequestMapping("/ico")
    @ResponseBody
    public Response upload_ico(@RequestParam(value = "userIco") MultipartFile multipartFile, HttpSession session){
        System.out.println("开始");
        String filename = multipartFile.getOriginalFilename();//得到上传的文件的本来的名字
        System.out.println("本来的文件名字："+filename);
        User user = (User) session.getAttribute("user");
       user =  userService.setIco(multipartFile,user);
        if (user != null) {
            session.setAttribute("user",user);
            return new Response(Status.SUCCESS,user.getIco());
        } else {
            return new Response(Status.FORMAT_ERROR);
        }

    }

    @RequestMapping(value = "/excel",method = RequestMethod.POST)
    @ResponseBody
    public Response upload_excel(@RequestParam(value = "stu_excel") MultipartFile mfile,HttpSession session) {
        System.out.println("开始");
        System.out.println("文件本来的名字"+mfile.getOriginalFilename());
        String email = ((User)session.getAttribute("user")).getEmail();
        List<Student> students = stuService.inpExcel(mfile,email); //这个方法分析excel表格 并保存文件到服务器
        System.out.println(students);
        String res = stuService.insertStus(students);
        if (res.equals("导入成功")) {
            return new Response(Status.SUCCESS,res);
        } else {
            return new Response(Status.DB_ERROR,res);
        }
    }
}
