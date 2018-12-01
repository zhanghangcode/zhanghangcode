package com.min.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description ok
 * @createTime 2018年12月01日 19:17:00
 */
@Controller
public class LoginController {

    @RequestMapping("/page/login")
    public String showLogin(){
        return "login";
    }
}
