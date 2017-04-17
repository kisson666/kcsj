package com.wang.common;

/**
 * @author [FeN]July E-mail: newfenjuly@gmail.com
 * @version 创建时间：2014-5-16 上午10:43:46 所有的返回结果放在这个类里面
 */

public class Response {
    protected short status;
    protected String errmsg;
    protected Object body;

    public Response() {
    }

    public Response(short status) {
        this.status = status;
    }

    public Response(short status, String errmsg) {
        this.status = status;
        this.errmsg = errmsg;
    }

    public Response(short status, Object body) {
        this(status);
        this.body = body;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}