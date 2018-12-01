package com.min.sso.service;

import com.min.common.utils.CommonResult;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description ok
 * @createTime 2018年12月01日 18:14:00
 */

public interface UserService {
    CommonResult checkData(String param,Integer type);
}
