package com.min.controller;

import com.min.common.pojo.EasyUITreeNode;
import com.min.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ItemCatController.java
 * @Description ok
 * @createTime 2018年11月19日 19:34:00
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatServiceImpl;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        List<EasyUITreeNode> catList = itemCatServiceImpl.getCatList(parentId);
        return catList;
    }
}
