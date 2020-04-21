package com.ctsi.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * ClassName DynamicDataSourceAspect
 * Description //TODO
 * Author tongliwei
 * Date 2020/1/31 16:35
 * Version v1.0
 **/
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE-1)
public class DynamicDataSourceAspect {
    @Pointcut("@annotation(com.ctsi.datasource.DS)")
    public void pointCut(){}

    @Before("pointCut()")
    public void beforeSwitchDS(JoinPoint point){
        Class<?> className=point.getTarget().getClass();
        String methodName=point.getSignature().getName();
        Class[] argClass=((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource=DataSourceContextHolder.DEFAULT_DS;
        try{
            Method method =className.getMethod(methodName,argClass);
            if(method.isAnnotationPresent(DS.class)){
                DS annotation=method.getAnnotation(DS.class);
                dataSource=annotation.value();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DataSourceContextHolder.setDB(dataSource);
    }
    @After("pointCut()")
    public void afterSwitchDS(JoinPoint point){
        DataSourceContextHolder.clearDB();
    }
}
