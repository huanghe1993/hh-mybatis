package com.huanghe.mybatis.session;

import com.huanghe.mybatis.binding.MapperRegistry;

public interface SqlSessionFactory {

    /**
     * 该接口的主要的作用是t提供一个统一的方法获取到SqlSession
     * @return sqlSession
     */
    SqlSession getSqlSession();
}
