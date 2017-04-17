package com.wang.record;

import com.wang.common.Response;
import com.wang.model.Log;
import com.wang.model.Student;
import com.wang.model.User;
import com.wang.myinterface.LogInterface;
import com.wang.myinterface.StudentInterface;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * Created by hppc on 2017/4/15.
 */

@Component
@Scope("request")
@Aspect
public class Record {
    @Autowired
    LogInterface logInterface;
    @Autowired
    StudentInterface studentInterface;
    @Autowired
    HttpSession session;

    void createLog(String content) {
        Log log = new Log();
        String email = ((User)session.getAttribute("user")).getEmail();
        String name = ((User)session.getAttribute("user")).getRealName();
        log.setName(name); log.setEmail(email);
        log.setTime(new Timestamp(System.currentTimeMillis()));
        log.setContent(content);
        logInterface.addLog(log);
    }

    @AfterReturning(returning = "response",pointcut = "execution(* com.wang.controller.UserController.login(..))")
    public void checkLogin(JoinPoint joinPoint, Response response) {
        if (response.getStatus() == 0) {
            createLog("成功登录");
        } else {
            User user = (User) joinPoint.getArgs()[0];
            Log log = new Log();
            log.setEmail(user.getEmail()); log.setContent("登录失败:email:"+user.getEmail()+",password:"+user.getPassword());
            log.setTime(new Timestamp(System.currentTimeMillis()));
            logInterface.addLog(log);
        }
    }
    @AfterReturning(returning = "response",pointcut = "execution(* com.wang.controller.UserController.setpsw(..))")
    public void record_setpsw(Response response) {
        if (response.getStatus() == 0) {
            createLog("修改密码成功");
            session.invalidate();//清空当前session的所有信息
        } else {
            createLog("修改密码失败");
        }
    }

    @AfterReturning(pointcut = "execution(* com.wang.controller.UserController.logout(..))")
    public void record_logout() {
       createLog("注销登录");
       session.invalidate();
    }
    @AfterReturning(returning = "response",pointcut = "execution(* com.wang.controller.UserController.setInfo(..))")
    public void record_setInfo(Response response) {
        if (response.getStatus() == 0) {
            createLog("修改个人资料成功");
        } else {
            createLog("修改个人资料失败");
        }
    }





    //学生信息模块的日志   //execution表达式配的包必须要能被aop声明所在的配置文件扫描到才行
    @AfterReturning(returning = "res",pointcut = "execution(* com.wang.service.StuService.addStudent(..))")
    public void record_addStudent(JoinPoint joinPoint, String res) {
        if (res.equals("添加成功")) {
            createLog("添加学生信息成功:"+joinPoint.getArgs()[0].toString());
        } else {
            createLog("添加学生信息失败:"+joinPoint.getArgs()[0].toString());
        }
    }


    @AfterReturning(returning = "res",pointcut = "execution(* com.wang.service.StuService.delStu(..))")
    public void record_delStu(String res) {
        System.out.println("删除学生信息的aop");
//        Student student = studentInterface.findById((Integer) joinPoint.getArgs()[0]);
        if (res.equals("删除成功")) {
            createLog("删除学生信息成功:"+student.toString());
        } else {
            createLog("删除学生信息失败:"+student.toString());
        }
    }
    private Student student;
    @Before("execution(* com.wang.service.StuService.delStu(..))")
    public void record_delStu_before(JoinPoint joinPoint) {
        student = studentInterface.findById((Integer) joinPoint.getArgs()[0]);
        System.out.println("要删除的是:"+student);
    }

    @AfterReturning(returning = "res",pointcut = "execution(* com.wang.service.StuService.setStu(..))")
    public void record_setStu(JoinPoint joinPoint,String res) {
        Student student = (Student) joinPoint.getArgs()[0];
        if (res.equals("修改成功")) {
            createLog("修改学生信息成功:"+student.toString());
        } else {
            createLog("修改学生信息失败:"+student.toString());
        }
    }


    @Before("execution(* com.wang.controller.SearchStu.*(String))")
    public void record_searchStu(JoinPoint joinPoint) {
       String res = (String) joinPoint.getArgs()[0];
        System.out.println(res);
            createLog(joinPoint.getSignature().getName()+" "+"关键词是: "+res);
    }

    @Before("execution(* com.wang.controller.SearchStu.search_all(..))")
    public void record_searchStu_all() {
        createLog("查询所有学生信息");
    }



    @AfterReturning(returning = "response",pointcut = "execution(* com.wang.controller.UploadController.upload_excel(..))")
    public void record_upload_excel(Response response) {
        if (response.getStatus() == 0) {
            createLog("导入excel成功");
        } else {
            System.out.println(response.getStatus());
            createLog("导入excel失败");
        }
    }
}
