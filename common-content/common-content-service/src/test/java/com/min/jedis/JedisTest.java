package com.min.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName JedisTest.java
 * @Description Redis客户端测试
 * @createTime 2018年11月23日 16:19:00
 */

public class JedisTest {

    @Test
    public void jedisConnect(){
        //1 创建一个Jedis连接对象
        Jedis jedis=new Jedis("192.168.25.131",6379);
        //直接使用jedis操作Redis
        jedis.set("test321","fist test jedis");
        String test321 = jedis.get("test321");
        System.out.print(test321);
        //关闭连接
        jedis.close();
    }
}
