package com.life.game.config.exception;


/**
 * @author Wagic
 */

public enum ExceptionCode {

    OK(0, "成功"),
    FAIL(1, "异常");

    private Integer code;
    private String msg;

    ExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
