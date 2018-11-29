package com.min.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.common.pojo.EasyUIDataGridResult;
import com.min.common.utils.CommonResult;
import com.min.common.utils.IDUtils;
import com.min.mapper.TbItemDescMapper;
import com.min.mapper.TbItemMapper;
import com.min.pojo.TbItem;
import com.min.pojo.TbItemDesc;
import com.min.pojo.TbItemExample;
import com.min.service.ItemService;
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

    @Override
    public TbItem getItemById(long itemId) {
        //根据商品ID查询商品信息

//        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andIdEqualTo(itemId);
        //执行查询
        List<TbItem> list = tbItemMapper.selectByExample(example);
        return list.get(0);
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
        return tbItemDescMapper.selectByPrimaryKey(itemId);
    }
}
