package com.wang.controller;

import com.wang.common.Response;
import com.wang.common.Status;
import com.wang.model.User;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hppc on 2017/4/14.
 */
@Controller
@RequestMapping("/userInfo")
public class SearchUser {
    @Autowired
    UserService userService;
    @RequestMapping("/id/{id}")
    @ResponseBody
    public Response searchId(@PathVariable String id) {
        User user = userService.getById(id);
        System.out.println(user);
        Map<String,Object> body = new HashMap<>();
        if (user != null) {
            body.put("user",user);
            return new Response(Status.SUCCESS,body);
        } else {
            return new Response(Status.DB_ERROR,"暂时无此用户信息");
        }
    }

    @RequestMapping("/userList")
    public ModelAndView getUserList(){
        System.out.println("用户请求得到所有用户列表");
        List<User> users = userService.getAll();
        System.out.println(users);
        ModelAndView mav = new ModelAndView("userList");
        mav.addObject("users",users);
        return mav;
    }

}
