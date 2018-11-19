package com.min.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.common.pojo.EasyUIDataGridResult;
import com.min.mapper.TbItemMapper;
import com.min.pojo.TbItem;
import com.min.pojo.TbItemExample;
import com.min.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceimpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        return tbItem;
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
}
