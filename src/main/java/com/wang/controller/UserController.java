package com.wang.controller;

import com.wang.common.Response;
import com.wang.common.Status;
import com.wang.model.User;
import com.wang.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hppc on 2017/2/27.
 */
@Controller
@RequestMapping("/user")
public class UserController {
//    private static final Log log=LogFactory.getLog(UserController.class);
    @Autowired
    UserService userService;

    /**
     *
     * @param user
     * @param httpSession
     * @description:验证登陆
     */
    @RequestMapping(value = "/identify", method = RequestMethod.POST)
    @ResponseBody    //@requestbody接收的是json字符串  不是json对象
    public Response login(@RequestBody User user ,HttpSession httpSession){
        String errmsg;
        user = userService.checkLogin(user);
        if(user != null){
            httpSession.setAttribute("user",user);
            Response response = new Response(Status.SUCCESS);
            return response;
        } else {
            errmsg = "The name and password you entered did not match our records.Please double-check and try again.";
            return new Response(Status.VALIDATION_ERROR,errmsg);
        }
    }
    /**
     * @description:修改密码
     */
    @RequestMapping(value = "/setPsw",method = RequestMethod.POST)
    @ResponseBody
    public Response setpsw(@RequestParam String oldPassword,@RequestParam String newPassword,
                           HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        String err;
        System.out.println("请求修改密码");
        err = userService.setPassword(newPassword,oldPassword,user.getEmail());
        if (err.equals("setPassword successfully")){
//            httpSession.invalidate();  //清除当前session所有相关信息   在aop里面删除 要不然aop得不到session中的user值
            return new Response(Status.SUCCESS,err);
        } else {
            return new Response(Status.VALIDATION_ERROR,err);
        }
    }
    /**
     * @description:获得当前用户信息
     */
    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    @ResponseBody
    public Response userInfo(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        Map<String,Object> body = new HashMap<>();
        if (null != user) {
            //将密码置空避免传给前台
            user.setPassword(null);
            body.put("user",user);
            return new Response(Status.SUCCESS,body);
        } else {
            return new Response(Status.FORBIDDEN_ERROR);
        }
    }
    /**
     * @description:注销当前登录
     */
    @RequestMapping("/exit")
    public String logout(HttpSession httpSession) {
//        httpSession.invalidate(); //清除当前session的所有信息
        return "redirect:/login.html";//redirect：  为直接url访问
    }

    /**
     * 用户修改资料
     */
    @RequestMapping(value = "/setInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response setInfo(@RequestBody User user, HttpSession session){
        User user1 = (User) session.getAttribute("user");
        user.setEmail(user1.getEmail());
        String errmsg = userService.setInfo(user);
        if (errmsg.equals("修改个人资料成功")) {
            user.setPassword(null);
            user.setRealName(user1.getRealName());
            user.setId(user1.getId());
            user.setIco(user1.getIco());
            session.setAttribute("user",user);
            return new Response(Status.SUCCESS,errmsg);
        } else {
            return new Response(Status.VALIDATION_ERROR,errmsg);
        }
    }
}
