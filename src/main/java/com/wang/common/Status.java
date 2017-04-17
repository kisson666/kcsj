package com.wang.common;

/**
 * @className: Stutas
 * @description: 这个类里面存放一些表示状态的常量
 */
public class Status {

    // 成功
    public static short SUCCESS = 0;
    // 格式出错
    public static short FORMAT_ERROR = 1;
    // 禁止访问
    public static short FORBIDDEN_ERROR = 2;
    // 未找到
    public static short NOT_FOUND_ERROR = 3;
    // 服务器错误
    public static short SERVER_ERROR = 4;
    // 验证错误
    public static short VALIDATION_ERROR = 5;
    // 数据库错误
    public static short DB_ERROR = 6;
    // 未登录
    public static short LOGIN_ERROR = 7;

}
