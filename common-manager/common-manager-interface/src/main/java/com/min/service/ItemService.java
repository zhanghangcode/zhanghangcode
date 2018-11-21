package com.min.service;

import com.min.common.pojo.EasyUIDataGridResult;
import com.min.common.utils.CommonResult;
import com.min.pojo.TbItem;

public interface ItemService<T> {
    TbItem getItemById(long itemId);
    EasyUIDataGridResult<T> getItemList(int page,int rows);
    CommonResult addItem(TbItem tbItem,String desc);
}
