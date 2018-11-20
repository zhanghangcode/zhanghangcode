package com.min.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.mapper.TbItemMapper;
import com.min.pojo.TbItem;
import com.min.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName PageHelperTest.java
 * @Description ok
 * @createTime 2018年11月18日 11:52:00
 */

public class PageHelperTest {

    @Test
    public void TestPageHepler(){
        //初始化spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        //从容器中获取map代理对象
        TbItemMapper bean = applicationContext.getBean(TbItemMapper.class);
        //执行sql语句之前，设置分页信息
        PageHelper.startPage(1,10);
        //执行查询
        TbItemExample itemExample=new TbItemExample();
        List<TbItem> tbItems = bean.selectByExample(itemExample);
        //去分页信息
        PageInfo<TbItem> pageInfo=new PageInfo<>(tbItems);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        System.out.print("total"+total+"pages"+pages);
    }
}
