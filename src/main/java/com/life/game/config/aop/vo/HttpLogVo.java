package com.life.game.config.aop.vo;

import java.util.Arrays;


/**
 * @author Wagic
 */
public class HttpLogVo {

    private String url;
    private String httpMethod;
    private String clientIp;
    private String classMethod;
    private Object[] args;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {

        return "{" +
                "url='" + url + '\'' +
                ", httpMethod:'" + httpMethod + '\'' +
                ", clientIp:'" + clientIp + '\'' +
                ", classMethod:'" + classMethod + '\'' +
                ", args:" + Arrays.toString(args) +
                '}';
    }
}
