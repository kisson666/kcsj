package com.wang.myinterface;

import com.wang.model.Log;

import java.util.List;

/**
 * Created by hppc on 2017/4/15.
 */
public interface LogInterface {
    int addLog(Log log);
    List<Log> findAll();
}
