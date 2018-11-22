package com.min.content.service.impl;

import com.min.common.utils.CommonResult;
import com.min.content.service.ContentService;
import com.min.mapper.TbContentMapper;
import com.min.pojo.TbContent;
import com.min.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public CommonResult addContent(TbContent tbContent) {
        Date date=new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.insert(tbContent);
        return CommonResult.ok();
    }

    /**
     * 根据内容分类id查询内容列表
     * @param cid
     * @return
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample contentExample=new TbContentExample();
        TbContentExample.Criteria criteria = contentExample.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(contentExample);
        return tbContents;
    }
}
