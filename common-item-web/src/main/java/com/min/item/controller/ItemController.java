package com.min.item.controller;

import com.min.item.pojo.Item;
import com.min.pojo.TbItem;
import com.min.pojo.TbItemDesc;
import com.min.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ItemController.java
 * @Description ok
 * @createTime 2018年11月29日 20:01:00
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model){
        //根据ID查询商品信息
        TbItem itemById = itemService.getItemById(itemId);
        Item item=new Item(itemById);
        //根据商品描述查Id
        TbItemDesc itemDescById = itemService.getItemDescById(itemId);
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDescById);
        return "item";
    }

}
