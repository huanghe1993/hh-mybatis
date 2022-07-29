package com.huanghe.mybatis.binding;


import cn.hutool.core.lang.ClassScanner;
import com.huanghe.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Mapper的注册机，其主要的目的是希望用户提供一个包路径，那么我们就能把所有的Mapper代理类注入进来.不需要用户进行每次手动的注册，完成痛点需求1
 */
public class MapperRegistry {

    /**
     * 将已经添加的映射器代理加入到HashMap
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    /**
     * 提供一个方法，用户传入的是包的路径，可以自动的扫描该包下面的类，对所有的类进行注册
     */
    public void addMappers(String packageName) {
        // 使用开源的三方组件
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapper : mapperSet) {
            addMapper(mapper);
        }
    }

    /**
     * Mapper注册机应该有加入Mapper的功能
     */
    public <T> void addMapper(Class<T> type) {
        // Mapper必须是接口才可以注册进来
        if (type.isInterface()) {
            // 判断是否已经存在注册了的该接口
            if (hasMapper(type)) {
                throw new RuntimeException("Type" + type + "is already known to the knownMappers");
            }
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    private <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        if (!knownMappers.containsKey(type)) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        knownMappers.get(type).getInstance(sqlSession);
    }
}
