package com.min.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName PageController.java
 * @Description ok
 * @createTime 2018年11月17日 20:47:00
 */
@Controller
public class PageController  {

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
