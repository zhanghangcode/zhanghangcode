package com.min.content.service.impl;

import com.min.common.jedis.JedisClient;
import com.min.common.utils.CommonResult;
import com.min.common.utils.JsonUtils;
import com.min.content.service.ContentService;
import com.min.mapper.TbContentMapper;
import com.min.pojo.TbContent;
import com.min.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ContentServiceImpl.java
 * @Description ok
 * @createTime 2018年11月22日 20:50:00
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

    @Override
    public CommonResult addContent(TbContent tbContent) {
        Date date=new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.insert(tbContent);
        //缓存同步：删除缓存中的数据
        jedisClient.hdel(CONTENT_LIST,tbContent.getCategoryId().toString());
        return CommonResult.ok();
    }

    /**
     * 根据内容分类id查询内容列表
     * @param cid
     * @return
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {

        //查询缓存
        try {
            String json = jedisClient.hget(CONTENT_LIST, cid + "");
            //判断json是否为空
            if (StringUtils.isNotBlank(json)){
                //将json转换成list
                List<TbContent> tbContents = JsonUtils.jsonToList(json, TbContent.class);
                return tbContents;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbContentExample contentExample=new TbContentExample();
        TbContentExample.Criteria criteria = contentExample.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(contentExample);
        //向缓存中添加数据
        try {
            jedisClient.hset(CONTENT_LIST,cid+"",JsonUtils.objectToJson(tbContents));
        }catch (Exception e){
            e.printStackTrace();
        }

        return tbContents;
    }
}
