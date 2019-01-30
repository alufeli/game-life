package com.life.game.config.aop.vo;


/**
 * @author Wagic
 */
public class DurationVo {

    private String method;

    private long duration;

    public DurationVo(String method, long duration) {
        this.method = method;
        this.duration = duration;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "{" +
                "method:'" + method + '\'' +
                ", duration:" + duration +
                '}';
    }
}
