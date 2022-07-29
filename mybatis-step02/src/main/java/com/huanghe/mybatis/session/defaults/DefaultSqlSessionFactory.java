package com.huanghe.mybatis.session.defaults;

import com.huanghe.mybatis.binding.MapperRegistry;
import com.huanghe.mybatis.session.SqlSession;
import com.huanghe.mybatis.session.SqlSessionFactory;

/**
 * 有了功能服务（sqlSession）以后还需要给这个功能服务提供一个工厂，来对外统一提供这类服务。
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession getSqlSession() {
        return new DefaultSqlSession(mapperRegistry);
    }

}
