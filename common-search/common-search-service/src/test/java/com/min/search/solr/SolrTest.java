package com.min.search.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName SolrTest.java
 * @Description Solr搜索测试
 * @createTime 2018年11月25日 19:48:00
 */

public class SolrTest {

    //添加文档
    @Test
    public void addDocument() throws Exception{
        //首先创建一个SolrServer
        SolrServer solrServer=new HttpSolrServer("http://192.168.25.133:8080/solr");
        //创建一个文档对象SolrDocument
        SolrInputDocument fields=new SolrInputDocument();
        //向文档中添加域必须有id域，域的名称必须在schema.xml中定义
        fields.addField("id","test001");
        fields.addField("item_title", "测试商品");
        fields.addField("item_price", "199");
        //把文档添加到索引库中
        solrServer.add(fields);
        //提交
        solrServer.commit();

    }
    //删除文档
    @Test
    public void delDocument() throws Exception{
        //首先创建一个SolrServer
        SolrServer solrServer=new HttpSolrServer("http://192.168.25.133:8080/solr");
        //根据id删除
        solrServer.deleteById("test001");
        solrServer.commit();
    }
}
