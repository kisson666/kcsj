package com.wang.interceptors;

import com.wang.model.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hppc on 2017/4/2.
 */
public class UserInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();//若无session会自动创建 默认括号里的参数为true
        User user = (User) httpSession.getAttribute("user");
        String path = request.getServletPath();//获取当前路径
        System.out.println("当前path: "+path);
        if (user != null){
            System.out.println("user 不为 null: "+user);
            if (path.startsWith("/user/identify")){  //若为登陆验证接口 就重定向要主页
                System.out.println("当前已登录，但是路径为登录验证，可能用户想登录另一个账号,放行");
//                response.sendRedirect("/index.html");
                return true;
            } else {
                System.out.println("当前已登录，路径不为登录验证，放行");
                return true;  //return true 执行下一个拦截器 若是最后一个拦截器 就执行请求
            }
        } else {
            System.out.println("session中user为null");
            if (path.startsWith("/user/identify")){  //若为登陆验证接口 就重定向要主页
                System.out.println("当前未登录，路径为登录验证,放行");
                return true;
            } else {
                System.out.println("当前未登录，路径不为登录验证，重定向到登录界面");
                response.sendRedirect("/login.html");
                return false;  //return true 执行下一个拦截器 若是最后一个拦截器 就执行请求
            }
        }
    }
}
