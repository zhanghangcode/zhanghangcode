package com.min.search.mapper;

import com.min.common.pojo.SearchItem;

import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ItemMapper.java
 * @Description ok
 * @createTime 2018年11月25日 19:59:00
 */

public interface ItemMapper {
    List<SearchItem> getItemList();
    SearchItem getItemById(Long id);
}
