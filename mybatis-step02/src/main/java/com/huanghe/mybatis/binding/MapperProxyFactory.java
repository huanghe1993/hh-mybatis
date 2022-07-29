package com.huanghe.mybatis.binding;

import com.huanghe.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * MapperProxyFactory主要是创建Mapper的代理类对象，JDK中创新代理类的实现方式为
 * Proxy.newProxyInstance(),该方法有三个参数，第一个参数是类加载器，第二个参数是接口，第三个参数是实现InvocationHandler的类
 * 由于我们是给不同的Mapper接口都需要生成代理对象，所以可以接口任意类型的Mapper接口class
 */
public class MapperProxyFactory<T> {

    /**
     * mapper接口的class类对象.对哪一个Mapper进行代理需要调用方传递
     */
    private final Class<T> mapperInterface;


    public MapperProxyFactory(Class<T> mapper) {
        this.mapperInterface = mapper;
    }


    /**
     * 通过工厂获取Mapper接口的代理对象
     */
    public T getInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        // JDK创建代理对象
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
