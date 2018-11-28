package com.min.search.solr;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName SlorCloudTest.java
 * @Description ok
 * @createTime 2018年11月28日 20:24:00
 */

public class SlorCloudTest {

    @Test
    public void SlorCloudAddItem() throws  Exception{
        CloudSolrServer cloudSolrServer=new CloudSolrServer("192.168.25.133:2181,192.168.25.133:2182,192.168.25.133:2183");
        //设置属性DefaultCollection
        cloudSolrServer.setDefaultCollection("collection2");
        //创建SolrInputDocument对象
        SolrInputDocument document=new SolrInputDocument();
        document.addField("item_title", "测试商品");
        document.addField("item_price", "100");
        document.addField("id", "test001");
        // 第六步：把文档对象写入索引库。
        cloudSolrServer.add(document);
        // 第七步：提交。
        cloudSolrServer.commit();

    }

}
