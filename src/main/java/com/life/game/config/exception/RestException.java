package com.life.game.config.exception;


/**
 * @author Wagic
 */
public class RestException extends RuntimeException {

    private Integer code = 1;

    public RestException(Integer code) {
        this.code = code;
    }

    public RestException(String msg) {
        super(msg);
    }

    public RestException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
