package com.devin.love.music.common.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.devin.love.music.common.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 2024/11/16 23:53
 * <p>
 * 自定义日志切面类
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Aspect
@Slf4j
@Component
public class LogAspect {
    /**
     * 切点
     */
    @Pointcut("@annotation(com.devin.love.music.common.annotation.Log)")
    public void pointCut() {}

    /**
     * 前置通知
     * @param joinPoint
     */
    @Around("pointCut()")
    public Object before(ProceedingJoinPoint point) {

        // 获取类名，方法名
        String className = point.getSignature().getDeclaringTypeName();
        String methodName = point.getSignature().getName();

        // 获取方法签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 获取参数名
        String[] paramNames = signature.getParameterNames();
        // 获取参数列表
        Object[] args = point.getArgs();

        // 获取注解内容
        Log annotation = signature.getMethod().getAnnotation(Log.class);
        // 模块名 + 类名
        String module = annotation.module().concat("(" + className + ")");
        // 描述 + 方法名
        String desc = annotation.desc().concat("(" + methodName + ")");

        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < paramNames.length; i++) {
            map.put(paramNames[i], args[i]);
        }

        Object result = null;

        try {
            long startTime = System.currentTimeMillis();
            log.info("【{}--{}】===> 请求参数：{}", module, desc, JSONObject.toJSONString(map));

            result = point.proceed(point.getArgs());

            long endTime = System.currentTimeMillis();
            log.info("【{}--{}】===> 返回结果：{} ===> 请求耗时：{}", module, desc, JSONObject.toJSONString(result), (endTime - startTime));
        } catch (Throwable e) {
            log.error("【{}-{}】==>返回异常: {}", module, desc, e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }
}
