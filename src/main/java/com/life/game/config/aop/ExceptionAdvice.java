package com.life.game.config.aop;

import com.life.game.config.ResponseCodeEnum;
import com.life.game.config.exception.RestException;
import com.life.game.config.result.RestResult;
import com.life.game.config.result.ResultGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理切面类
 * @author Wagic
 */
@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionAdvice.class);

    /**
     * 信息无法读取
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResult handleHttpMessageNotReadableException(Exception e) {
        e.printStackTrace();
        return ResultGenerator.createFailResult(ResponseCodeEnum.UNABLE_TO_READ, "无法读取");
    }

    /**
     * 处理参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResult handleMethodArgumentNotValidException(Exception e) {
        return ResultGenerator.createFailResult(ResponseCodeEnum.PARAM_ERROR, "参数验证失败");
    }

    /**
     * 处理自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RestException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public RestResult handleIException(RestException e) {
        return ResultGenerator.createFailResult(ResponseCodeEnum.CUSTOM_EXCEPTION,
                "自定义异常");
    }

    /**
     * 数学计算错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult handleArithmeticException(ArithmeticException e) {
        return ResultGenerator.createFailResult(ResponseCodeEnum.INTERNAL_ERROR, "服务器内部错误");
    }

    /**
     * 登陆错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult handleAuthenticationException(AuthenticationException e) {
        LOGGER.error(e);
        return ResultGenerator.createFailResult(ResponseCodeEnum.LOGIN_FAILED, "登陆错误");
    }

    @ExceptionHandler(UnknownAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult handleUnknownAccountException(UnknownAccountException e) {
        LOGGER.error(e);
        return ResultGenerator.createFailResult(ResponseCodeEnum.SESSION_ERROR, "请登录");
    }


    /**
     * 没有权限——shiro
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RestResult handleUnauthorizedException(UnauthorizedException e) {
        return ResultGenerator.createFailResult(ResponseCodeEnum.NO_AUTHORITY, "没有权限");
    }
}
