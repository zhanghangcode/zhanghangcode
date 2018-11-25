package com.min.search.controller;

import com.min.common.pojo.SearchResult;
import com.min.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName SearchController.java
 * @Description ok
 * @createTime 2018年11月25日 21:51:00
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchServiceImpl;

    @Value("${SEARCH_RESULT_ROWS}")
    private Integer SEARCH_RESULT_ROWS;

    @RequestMapping("/search")
    public String search(String keyword,
                         @RequestParam(defaultValue="1") Integer page, Model model) throws Exception{
        keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
        //调用Service查询商品信息
        SearchResult result = searchServiceImpl.search(keyword, page, SEARCH_RESULT_ROWS);
        //把结果传递给jsp页面
        model.addAttribute("query", keyword);
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("recourdCount", result.getRecordCount());
        model.addAttribute("page", page);
        model.addAttribute("itemList", result.getItemList());
        //返回逻辑视图
        return "search";

    }
}
