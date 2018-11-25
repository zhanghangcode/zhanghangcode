package com.min.search;

import com.min.common.pojo.SearchResult;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName SearchService.java
 * @Description ok
 * @createTime 2018年11月25日 21:24:00
 */

public interface SearchService {
    SearchResult search(String keyWord, int page, int rows) throws Exception;
}
