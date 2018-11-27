package com.min.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName GlobalException.java
 * @Description ok
 * @createTime 2018年11月27日 20:09:00
 */

public class GlobalException implements HandlerExceptionResolver {
    private  static final Logger logger=LoggerFactory.getLogger(GlobalException.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
        //打印控制台
        e.printStackTrace();
        //写日志
        logger.debug("测试输出的日志。。。");
        logger.info("系统发生异常");
        logger.error("系统发生异常",e);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }
}
