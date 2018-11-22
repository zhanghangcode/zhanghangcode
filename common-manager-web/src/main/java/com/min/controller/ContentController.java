package com.min.controller;

import com.min.common.utils.CommonResult;
import com.min.content.service.ContentService;
import com.min.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ContentController.java
 * @Description ok
 * @createTime 2018年11月22日 20:54:00
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/save")
    @ResponseBody
    public CommonResult addContent(TbContent content){
        CommonResult commonResult = contentService.addContent(content);
        return commonResult;
    }
}
