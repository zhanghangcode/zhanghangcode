package com.min.content.service;

import com.min.common.utils.CommonResult;
import com.min.pojo.TbContent;

import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName ContentService.java
 * @Description ok
 * @createTime 2018年11月22日 20:49:00
 */

public interface ContentService {
    CommonResult addContent(TbContent tbContent);
    List<TbContent> getContentListByCid(long cid);
}
