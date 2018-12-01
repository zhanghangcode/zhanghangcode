package com.min.sso.service.impl;

import com.min.common.utils.CommonResult;
import com.min.mapper.TbUserMapper;
import com.min.pojo.TbUser;
import com.min.pojo.TbUserExample;
import com.min.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName UserServerImpl.java
 * @Description ok
 * @createTime 2018年12月01日 18:18:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public CommonResult checkData(String param, Integer type) {
        // 1、从tb_user表中查询数据
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        // 2、查询条件根据参数动态生成。
        //1、2、3分别代表username、phone、email
        if (type == 1) {
            criteria.andUsernameEqualTo(param);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(param);
        } else if (type == 3) {
            criteria.andEmailEqualTo(param);
        } else {
            return CommonResult.build(400, "非法的参数");
        }
        //执行查询
        List<TbUser> list = tbUserMapper.selectByExample(example);
        // 3、判断查询结果，如果查询到数据返回false。
        if (list == null || list.size() == 0) {
            // 4、如果没有返回true。
            return CommonResult.ok(true);
        }
        // 5、使用e3Result包装，并返回。

        return CommonResult.ok(false);
    }
}
