package com.life.game.config.result;


import com.life.game.config.ResponseCodeEnum;

/**
 * @author Wagic
 */
public class ResultGenerator {

    private static <T> RestResult<T> createResult(Integer code, String msg, T data) {

        RestResult<T> result = RestResult.newInstance();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> RestResult<T> createSucResult() {
        return createResult(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getName(), null);
    }

    public static <T> RestResult<T> createSucResult(T data) {
        return createResult(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getName(), data);
    }


    public static <T> RestResult<T> createSucResult(String msg, T data) {
        return createResult(ResponseCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> RestResult<T> createFailResult(ResponseCodeEnum respCode) {
        return createResult(respCode.getCode(), respCode.getName(), null);
    }

    public static <T> RestResult<T> createFailResult(ResponseCodeEnum respCode, String msg) {
        return createResult(respCode.getCode(), msg, null);
    }

    public static <T> RestResult<T> createFailResult(ResponseCodeEnum respCode, T data) {
        return createResult(respCode.getCode(), respCode.getName(), data);
    }

}
