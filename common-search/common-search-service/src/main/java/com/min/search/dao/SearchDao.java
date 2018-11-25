package com.min.search.dao;

import com.min.common.pojo.SearchItem;
import com.min.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName SearchDao.java
 * @Description ok
 * @createTime 2018年11月25日 21:26:00
 */
@Repository
public class SearchDao {
    @Autowired
    private SolrServer solrServer;

    public SearchResult searchItem(SolrQuery solrQuery) throws Exception{
        SearchResult result=new SearchResult();
        //创建商品列表对象
        List<SearchItem> searchItems=new ArrayList<>();
        //根据查询条件查询索引库
        QueryResponse queryResponse=solrServer.query(solrQuery);
        //获取查询结果总记录数
        SolrDocumentList results = queryResponse.getResults();
        //得到总的查询数量
        long numFound = results.getNumFound();
        result.setRecordCount(numFound);
        //取高亮后的结果
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        for (SolrDocument document:results){
            //获取商品信息
            SearchItem searchItem=new SearchItem();
            searchItem.setCategory_name((String) document.get("item_category_name"));
            searchItem.setId((String) document.get("id"));
            searchItem.setImage((String) document.get("item_image"));
            searchItem.setPrice((long) document.get("item_price"));
            searchItem.setSell_point((String) document.get("item_sell_point"));
            //获取高亮的结果
            List<String> strings = highlighting.get(document.get("id")).get("item_title");
            String itemTitle="";
            if (strings!=null && strings.size()>0){
                itemTitle=strings.get(0);
            }else {
                itemTitle=(String) document.get("item_title");
            }
            searchItem.setTitle(itemTitle);
            //添加到商品列表
            searchItems.add(searchItem);
        }

        result.setItemList(searchItems);
        return result;
    }
}
