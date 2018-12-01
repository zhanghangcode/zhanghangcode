package com.min.sso.controller;

import com.min.common.utils.CommonResult;
import com.min.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description ok
 * @createTime 2018年12月01日 15:07:00
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public CommonResult checkData(@PathVariable String param,@PathVariable Integer type){
        CommonResult commonResult = userService.checkData(param, type);
        return commonResult;
    }
}
