package com.min.content.service.impl;

import com.min.common.pojo.EasyUITreeNode;
import com.min.common.utils.CommonResult;
import com.min.content.service.ContentCategoryService;
import com.min.mapper.TbContentCategoryMapper;
import com.min.pojo.TbContentCategory;
import com.min.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ContentCategoryServiceImpl.java
 * @Description ok
 * @createTime 2018年11月22日 10:43:00
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(Long parentId) {
        //1 取查询id
        //2根据parentId查询tb_content_category,查询子节点列表
        TbContentCategoryExample categoryExample=new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        // 3、得到List<TbContentCategory>
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(categoryExample);
        // 4、把列表转换成List<EasyUITreeNode>ub
        List<EasyUITreeNode> easyUITreeNodes=new ArrayList<>();
        for (TbContentCategory category:tbContentCategories){
            EasyUITreeNode node=new EasyUITreeNode();
            node.setText(category.getName());
            node.setId(category.getId());
            node.setState(category.getIsParent()?"closed":"open");
            easyUITreeNodes.add(node);
        }
        return easyUITreeNodes;
    }

    @Override
    public CommonResult addContentCategory(long parentId, String name) {
        //接收两个参数 parentId, name
        //向tb_content_category表中插入数据。
        TbContentCategory tbContentCategory=new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        tbContentCategory.setIsParent(false);
        //排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
        tbContentCategory.setSortOrder(1);
        //状态。可选值:1(正常),2(删除)
        tbContentCategory.setStatus(1);
        Date date=new Date();
        tbContentCategory.setUpdated(date);
        tbContentCategory.setCreated(date);
        //向tb_content_category表中插入数据
        tbContentCategoryMapper.insert(tbContentCategory);
        // 3、判断父节点的isparent是否为true，不是true需要改为true。
        TbContentCategory key = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!key.getIsParent()){
            key.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(key);
        }
        //返回E3Result，其中包装TbContentCategory对象
        return CommonResult.ok(tbContentCategory);
    }
}
