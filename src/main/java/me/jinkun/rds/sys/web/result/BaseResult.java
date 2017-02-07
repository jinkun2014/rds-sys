package me.jinkun.rds.sys.web.result;

/**
 * @Description: HelloWorld！ <br/>
 * @Autor: Created by JinKun on 2016-12-30.
 */
public class BaseResult {
    public final static int CODE_OK = 200;
    public final static int CODE_FAIL = 300;

    //业务状态
    protected int code;
    //业务状态消息
    protected String msg;
    //业务数据
    protected Object data;

    public BaseResult() {
    }

    public BaseResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static BaseResult ok(String msg) {
        return ok(msg, null);
    }

    public static BaseResult ok(String msg, Object data) {
        return new BaseResult(BaseResult.CODE_OK, msg, data);
    }

    public static BaseResult fail(String msg) {
        return fail(msg, null);
    }

    public static BaseResult fail(String msg, Object data) {
        return new BaseResult(BaseResult.CODE_FAIL, msg, data);
    }
}
