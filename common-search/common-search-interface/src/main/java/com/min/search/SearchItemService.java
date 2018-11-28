package com.min.search;

import com.min.common.utils.CommonResult;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName SearchItemService.java
 * @Description ok
 * @createTime 2018年11月25日 20:08:00
 */

public interface SearchItemService {
    CommonResult importItmes() ;
    CommonResult addDocument(long itemId)  throws Exception;
}
