package com.min.service;

import com.min.common.pojo.EasyUIDataGridResult;
import com.min.common.utils.CommonResult;
import com.min.pojo.TbItem;
import com.min.pojo.TbItemDesc;

public interface ItemService<T> {
    TbItem getItemById(long itemId);
    EasyUIDataGridResult<T> getItemList(int page,int rows);
    CommonResult addItem(TbItem tbItem,String desc);
    TbItemDesc getItemDescById(long itemId);
}
