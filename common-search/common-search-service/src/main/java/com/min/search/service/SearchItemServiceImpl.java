package com.min.search.service;

import com.min.common.pojo.SearchItem;
import com.min.common.utils.CommonResult;
import com.min.search.SearchItemService;
import com.min.search.mapper.ItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName SearchItemServiceImpl.java
 * @Description ok
 * @createTime 2018年11月25日 20:08:00
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public CommonResult importItmes()  {

        try {
            //首先查询商品列表
            List<SearchItem> itemList = itemMapper.getItemList();
            //导入索引库
            for(SearchItem searchItem:itemList){
                //创建文档对象
                SolrInputDocument inputFields=new SolrInputDocument();
                inputFields.addField("id",searchItem.getId());
                inputFields.addField("item_title",searchItem.getTitle());
                inputFields.addField("item_sell_point",searchItem.getSell_point());
                inputFields.addField("item_price",searchItem.getPrice());
                inputFields.addField("item_image",searchItem.getImage());
                inputFields.addField("item_category_name",searchItem.getCategory_name());
                solrServer.add(inputFields);
            }
            //一定要提交
            solrServer.commit();
            return CommonResult.ok();
        }  catch (Exception e) {
            e.printStackTrace();
            return CommonResult.build(500,"导入商品失败");
        }


    }
}
