package com.min.controller;

import com.min.common.utils.CommonResult;
import com.min.search.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName SearchItemController.java
 * @Description ok
 * @createTime 2018年11月25日 20:34:00
 */
@Controller
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemServiceImpl;
    @RequestMapping("/index/item/import")
    @ResponseBody
    public CommonResult impotItemIndex(){
        CommonResult commonResult = searchItemServiceImpl.importItmes();
        return commonResult;

    }
}
