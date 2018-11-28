package com.min.search.listener;

import com.min.search.service.SearchItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ItemChangeListener.java
 * @Description ok
 * @createTime 2018年11月28日 21:15:00
 */

public class ItemChangeListener implements MessageListener {
    @Autowired
    private SearchItemServiceImpl searchItemService;

    @Override
    public void onMessage(Message message) {


        try {
            TextMessage textMessage=null;
            Long itemId=null;
            //取商品ID
            if (message instanceof Message){
                textMessage= (TextMessage) message;
                itemId=Long.parseLong(textMessage.getText());
            }
            //睡1秒
            Thread.sleep(1000);
            //向索引库中添加文档
            searchItemService.addDocument(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
