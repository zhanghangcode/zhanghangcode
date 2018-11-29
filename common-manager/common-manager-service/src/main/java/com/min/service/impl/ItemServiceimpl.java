package com.min.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.common.jedis.JedisClient;
import com.min.common.pojo.EasyUIDataGridResult;
import com.min.common.utils.CommonResult;
import com.min.common.utils.IDUtils;
import com.min.common.utils.JsonUtils;
import com.min.mapper.TbItemDescMapper;
import com.min.mapper.TbItemMapper;
import com.min.pojo.TbItem;
import com.min.pojo.TbItemDesc;
import com.min.pojo.TbItemExample;
import com.min.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceimpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    //同步索引库
    @Autowired
    private JmsTemplate jmsTemplate;
    //命名规则必须使用 topicDestination
    @Resource
    private Destination topicDestination;
    @Autowired
    private JedisClient jedisClient;


    private String REDIS_ITEM_PRE ="ITEM_INFO";


    private Integer ITEM_CACHE_EXPIRE=3600;

    @Override
    public TbItem getItemById(long itemId) {
        try {
            //查询缓存
            String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":BASE");
            if (StringUtils.isNotBlank(json)) {
                //把json转换为java对象
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据商品id查询商品信息
        //TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        //设置查询条件
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = tbItemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            TbItem item = list.get(0);
            try {
                //把数据保存到缓存
                jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":BASE", JsonUtils.objectToJson(item));
                //设置缓存的有效期
                jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":BASE", ITEM_CACHE_EXPIRE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return item;
        }
        return null;

    }

    @Override
    public EasyUIDataGridResult<Page> getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page,rows);
        //执行查询
        TbItemExample tbItemExample=new TbItemExample();
        List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);
        //创建一个返回值对象
        EasyUIDataGridResult<Page> result=new EasyUIDataGridResult<>();
        result.setRows((Page)tbItems);
        //分页结果
        PageInfo<TbItem> pageInfo=new PageInfo<>(tbItems);
        //获取总记录数
        long total = pageInfo.getTotal();
        result.setTotal(total);

        return result;
    }

    @Override
    public CommonResult addItem(TbItem tbItem, String desc) {
        //1 生成商品Id
       final long id = IDUtils.genItemId();
        tbItem.setId(id);
        //设置商品状态
        //商品状态，1-正常，2-下架，3-删除
        tbItem.setStatus((byte)1);
        Date date=new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        //向商品表插入数据
        tbItemMapper.insert(tbItem);
        //创建一个TBItemDesc对象
        TbItemDesc tbItemDesc=new TbItemDesc();
        // 5、补全TbItemDesc的属性
        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        //插入数据
        tbItemDescMapper.insert(tbItemDesc);
        //发送一个商品添加的消息
        jmsTemplate.send( topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(id + "");
                return textMessage;
            }
        });
        return CommonResult.ok();
    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        //查询缓存
        try {
            String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":DESC");
            if(StringUtils.isNotBlank(json)) {
                TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return tbItemDesc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        //把结果添加到缓存
        try {
            jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
            //设置过期时间
            jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":DESC", ITEM_CACHE_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemDesc;
    }
}
