package com.huanghe.mybatis.session.defaults;

import com.huanghe.mybatis.binding.MapperRegistry;
import com.huanghe.mybatis.session.SqlSession;

/**
 * 默认的sqlSession的实现类，该类主要的作用是对sqlSession的实现
 */
public class DefaultSqlSession implements SqlSession {

    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T)"现在对sql的操作进行模拟，后面再具体的实现";
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T)"现在对sql的操作进行模拟，后面再具体的实现";
    }

    /**
     * 获取Mapper的代理实现。
     * 从实现的MapperRegister的过程中可以看到，MapperRegister主要是把我们传递进去的包下的Mapper接口都注册到了KnownMapper容器中进行保存
     * 所以获取Mapper应该也是从MapperRegister中的容器中进行获取的，MapperRegister主要提供的是注册功能。而KnownMapper是私有变量，所以
     * 应该提供一个getMapper的方法出来
     *
     * @param type Mapper interface class
     * @param <T>  泛型
     * @return Mapper的代理对象
     */
    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
