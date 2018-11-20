package com.min.service.impl;

import com.min.common.pojo.EasyUITreeNode;
import com.min.mapper.TbItemCatMapper;
import com.min.pojo.TbItemCat;
import com.min.pojo.TbItemCatExample;
import com.min.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ItemCatServiceImpl.java
 * @Description ok
 * @createTime 2018年11月19日 19:23:00
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    //注入dao
    @Autowired
    private TbItemCatMapper tbItemMapper;

    @Override
    public List<EasyUITreeNode> getCatList(long parentId) {
        //1根据parentId查询节点列表
        TbItemCatExample example=new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemMapper.selectByExample(example);
        //转换成EasyUITreeNode列表
        List<EasyUITreeNode> easyUITreeNodes=new ArrayList<>();
        System.out.print("有多少个数据"+tbItemCats.size());
        for (TbItemCat tbItemCat:tbItemCats){
            EasyUITreeNode node=new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            easyUITreeNodes.add(node);
        }

        return easyUITreeNodes;
    }
}
