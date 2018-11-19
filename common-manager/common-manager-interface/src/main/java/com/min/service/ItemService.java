package com.min.service;

import com.min.common.pojo.EasyUIDataGridResult;
import com.min.pojo.TbItem;

public interface ItemService<T> {
    TbItem getItemById(long itemId);
    EasyUIDataGridResult<T> getItemList(int page,int rows);
}
