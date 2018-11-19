package com.min.controller;

import com.min.common.pojo.EasyUIDataGridResult;
import com.min.pojo.TbItem;
import com.min.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    private TbItem getItemById(@PathVariable long itemId){
        TbItem itemById = itemService.getItemById(itemId);
        return itemById;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    private EasyUIDataGridResult getItemList(Integer page,Integer rows){
        EasyUIDataGridResult itemList = itemService.getItemList(page, rows);
        return itemList;
    }
}
