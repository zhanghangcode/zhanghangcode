package com.min.content.service;

import com.min.common.pojo.EasyUITreeNode;
import com.min.common.utils.CommonResult;

import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ContentCategoryService.java
 * @Description ok
 * @createTime 2018年11月22日 10:41:00
 */

public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(Long parentId);
    CommonResult addContentCategory(long parentId,String name);
}
