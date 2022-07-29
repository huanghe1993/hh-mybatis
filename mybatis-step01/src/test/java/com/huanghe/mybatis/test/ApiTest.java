package com.huanghe.mybatis.test;

import com.huanghe.mybatis.binding.MapperProxyFactory;
import com.huanghe.mybatis.test.dao.IUserDao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ApiTest {


    @Test
    public void test_MapperProxyFactory() {
        Class<IUserDao> mapperInterface = IUserDao.class;
        MapperProxyFactory<IUserDao> mapperProxyFactory = new MapperProxyFactory<>(mapperInterface);

        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("com.huanghe.mybatis.test.dao.IUserDao.queryUserByName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        IUserDao userDao = mapperProxyFactory.getInstance(sqlSession);

        String name = userDao.queryUserByName("huanghe");
        System.out.println("测试结果：" + name);


    }

}
