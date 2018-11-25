package com.min.search.service;

import com.min.common.pojo.SearchResult;
import com.min.search.SearchService;
import com.min.search.dao.SearchDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName SearchServiceImpl.java
 * @Description ok
 * @createTime 2018年11月25日 21:25:00
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;


    @Override
    public SearchResult search(String keyWord, int page, int rows) throws Exception {
        //创建一个SolrQuery对象
        SolrQuery solrQuery=new SolrQuery();
        //设置查询条件
        solrQuery.setQuery(keyWord);
        //设置分页条件
        solrQuery.setStart((page-1)*rows);
        //设置rows
        solrQuery.setRows(rows);
        //设置默认搜索域
        solrQuery.set("df","item_title");
        //设置高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        //执行查询
        SearchResult result = searchDao.searchItem(solrQuery);
        //计算总页数
        long recordCount = result.getRecordCount();
        int pages=((int) recordCount) / rows;
        if (((int) recordCount) / rows>0)pages++;
        result.setTotalPages(pages);
        return result;
    }
}
