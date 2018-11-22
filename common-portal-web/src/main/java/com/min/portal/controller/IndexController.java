package com.min.portal.controller;

import com.min.content.service.ContentService;
import com.min.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName IndexController.java
 * @Description ok
 * @createTime 2018年11月21日 20:32:00
 */
@Controller
public class IndexController {

    @Value("${CONTENT_LUNBO_ID}")
    private Long CONTENT_LUNBO_ID;
    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showInde(Model model){
        List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
        //结果传递给页面
        model.addAttribute("ad1List", ad1List);
        return "index";
    }
}
