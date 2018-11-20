package com.min.service;

import com.min.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ItemCatService.java
 * @Description ok
 * @createTime 2018年11月19日 19:23:00
 */

public interface ItemCatService {
     List<EasyUITreeNode> getCatList(long parentId);
}
