package com.life.game.config.aop;

import com.life.game.config.aop.vo.DurationVo;
import com.life.game.config.aop.vo.HttpLogVo;
import com.life.game.config.exception.RestException;
import com.life.game.config.result.RestResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


/**
 * @author Wagic
 */
@Aspect
@Configuration
public class AspectLog {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 申明切面
     */
    @Pointcut("execution(*  com..controller..*.*(..))")
    public void controllerPointcut() {
    }

    @Pointcut("execution(*  com..service..*.*(..))")
    public void servicePointcut() {
    }

    @Pointcut("execution(*  com..repository..*.*(..))")
    public void repositoryPointcut() {
    }

    /**
     * 合并多个切面
     */
    @Pointcut("controllerPointcut()||servicePointcut()||repositoryPointcut()")
    public void methodPointcut() {
    }

    @Around("methodPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("请求耗时记录：" + new DurationVo(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), end - start).toString());

        return result;
    }


    @Before("controllerPointcut()")
    public void httpBefore(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        HttpLogVo httpLogVo = new HttpLogVo();
        httpLogVo.setUrl(request.getRequestURL().toString());
        httpLogVo.setHttpMethod(request.getMethod());
        httpLogVo.setClientIp(request.getRemoteAddr());
        httpLogVo.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        httpLogVo.setArgs(joinPoint.getArgs());

        log.info("http请求记录" + httpLogVo.toString());

    }


    @AfterReturning(returning = "object", pointcut = "controllerPointcut()")
    public void doAfterReturning(Object object) throws UnsupportedEncodingException {

        if (!(object instanceof RestResult)) {
            throw new RestException(new String("返回结果类型错误".getBytes("utf-8")));
        }

        log.info("http返回记录:" + object);

    }


}
