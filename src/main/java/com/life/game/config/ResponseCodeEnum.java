package com.life.game.config;

/**
 * 返回码枚举类
 */
public enum  ResponseCodeEnum {

    // 成功
    SUCCESS("成功", 1),
    // 系统异常
    EXCEPTION("系统异常", 2),
    //参数错误
    PARAM_ERROR("参数错误", 3),
    // 数据库异常
    DB_ERROR("数据库异常", 4),
    LOGIN_FAILED("登陆失败", 5),
    // 登陆失效
    SESSION_ERROR("会话失效或者未登录", 6),
    UNABLE_TO_READ("无法读取", 7),
    CUSTOM_EXCEPTION("自定义异常", 8),
    NO_AUTHORITY("没有权限", 8),
    USER_EXIST("已存在用户", 9),
    INTERNAL_ERROR("服务器内部错误", 500);

    private final int code;
    private final String name;

    ResponseCodeEnum(String name,int code) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    };

    public String getName() {
        return name;
    };

    public boolean eq(int code) {
        return code == this.code;
    };
}
