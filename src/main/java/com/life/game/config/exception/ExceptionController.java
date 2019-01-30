package com.life.game.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 全局异常处理
 *
 * @author Wagic
 */
@Controller
@EnableConfigurationProperties({ServerProperties.class})
public class ExceptionController implements ErrorController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ErrorAttributes errorAttributes;

    @Autowired
    private ServerProperties serverProperties;

    public ExceptionController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "未检测到异常");
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error", produces = "text/html")
    public ModelAndView doHtmlError(HttpServletRequest request) {

        //获取异常信息
        Map<String, Object> model = getErrorAttributes(request, true);
        //打印日志
        logger.error("异常：{}", model);
        //移除异常栈（页面上不允许看到）
        model.remove("trace");

        //返回异常页面
        return new ModelAndView("error/exception", model);

    }

    @RequestMapping(value = "/error")
    @ResponseBody
    public Map<String, Object> doJsonError(HttpServletRequest request) {

        //获取异常信息
        Map<String, Object> model = getErrorAttributes(request, true);
        //打印日志
        logger.error("异常：{}", model);
        //移除异常栈（页面上不允许看到）
        model.remove("trace");

        //返回异常格式
        return model;
    }


    /**
     * 获取错误的信息
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {

        return this.errorAttributes.getErrorAttributes(new ServletRequestAttributes(request), includeStackTrace);
    }


}
