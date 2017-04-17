package com.wang.controller;

import com.wang.model.Log;
import com.wang.myinterface.LogInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hppc on 2017/4/17.
 */
@Controller
@RequestMapping("/log")
public class SearchLog {
    @Autowired
    LogInterface logInterface;

    @ResponseBody
    @RequestMapping("/all")
    public List<Log> search_all() {
        System.out.println("查看用户操作日志");
        return logInterface.findAll();
    }
}
