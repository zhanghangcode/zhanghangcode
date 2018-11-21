package com.min.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName IndexController.java
 * @Description ok
 * @createTime 2018年11月21日 20:32:00
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showInde(){
        return "index";
    }
}
