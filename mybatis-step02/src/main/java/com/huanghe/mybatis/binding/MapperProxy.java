package com.huanghe.mybatis.binding;

import com.huanghe.mybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Mapper接口的代理类实现JDK的InvocationHandler接口。复写invoke方法
 */
public class MapperProxy<T> implements InvocationHandler {

    /**
     * 使用Map模拟sqlSession进行实际处理逻辑
     */
    private final SqlSession sqlSession;

    /**
     * 获取mapper的类名称（Mybatis中通过Mapper的类名称和方法名称找到一条sql）
     */
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession,Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 判断如果当前的method是Object的方法，那么就不走代理
        if (Object.class.equals(method.getDeclaringClass())) {
            method.invoke(proxy, args);
        }
        // 修改点，实际执行的方法是使用的是sqlSession中的方法进行执行的。sqlSession中定义了一系列的sql操作
        return sqlSession.selectOne(method.getName(), args);
    }
}
