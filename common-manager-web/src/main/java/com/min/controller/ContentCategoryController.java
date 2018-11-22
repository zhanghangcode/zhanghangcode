package com.min.controller;

import com.min.common.pojo.EasyUITreeNode;
import com.min.common.utils.CommonResult;
import com.min.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ContentCategoryController.java
 * @Description ok
 * @createTime 2018年11月22日 10:57:00
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id" ,defaultValue = "0") Long parentId){
        List<EasyUITreeNode> contentCatList = contentCategoryService.getContentCatList(parentId);
        return contentCatList;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult creatCategory(Long parentId, String name){
        CommonResult commonResult = contentCategoryService.addContentCategory(parentId, name);
        return commonResult;
    }
}
