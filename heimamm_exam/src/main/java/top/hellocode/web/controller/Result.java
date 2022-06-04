package top.hellocode.web.controller;

import java.io.Serializable;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 19:46
 */
// 页面返回的结果数据封装对象
public class Result implements Serializable {
    private String message;     // 对应操作的返回消息
    private boolean flag;       // 对应操作的返回结果是否成功
    private Object data;        // 对应操作返回的具体数据
    private Integer code;       // 对应操作的返回状态码

    public Result(String message, Object data) {
        this.message = message;
        this.data = data;
        this.code = Code.SUCCESS;
        this.flag = true;
    }

    public Result(String message, boolean flag, Object data, Integer code) {
        this.message = message;
        this.flag = flag;
        this.data = data;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
