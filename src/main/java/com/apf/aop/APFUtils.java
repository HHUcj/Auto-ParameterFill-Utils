package com.apf.aop;

import com.apf.result.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.apf.constant.Constants.JSON;
import static com.apf.constant.Constants.OS_NAME;
import static com.apf.constant.Constants.REQ_RELATIVE_PATH;
import static com.apf.constant.Constants.RESP_RELATIVE_PATH;
import static com.apf.constant.Constants.USER_DIR;
import static com.apf.constant.Constants.WIN;

/**
 * ClassName: APFUtils
 * Description:
 *
 * @Author: 陈杰
 * @Create: 2024/11/28 - 下午5:00
 * @Version: v1.0
 */
@Component
@Aspect
@Slf4j
public class APFUtils {

    @Pointcut("execution(* com.apf.controller.*.*(..))")
    public void pointcut() {}


    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws IOException {
        // 切点获取方法入参
        Object[] args = joinPoint.getArgs();

        // 切点获取方法名
        String methodName = joinPoint.getSignature().getName();

        ObjectMapper objectMapper = new ObjectMapper();

        // 获取项目根路径
        String projectRootPath = System.getProperty(USER_DIR);

        // resources文件夹相对路径
        String relativePath = REQ_RELATIVE_PATH;

        // 不是windows默认就是linux，替换文件分隔符
        if (!System.getProperty(OS_NAME).contains(WIN)) {
            relativePath = relativePath.replace("\\", "/");
        }

        // 生成json文件的完整相对地址
        relativePath += methodName + JSON;

        File file = new File(projectRootPath, relativePath);

        // 序列化到resources目录下，该目录可通过relativePath自行更改
        objectMapper.writeValue(file, args);

        log.info("JSON file has been written to: " + relativePath);
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) throws IOException, ClassNotFoundException, NoSuchMethodException {
        // 切点获取方法入参
        Object[] args = joinPoint.getArgs();

        // 切点获取方法名
        String methodName = joinPoint.getSignature().getName();

        // 获取项目根路径
        String projectRootPath = System.getProperty(USER_DIR);

        ObjectMapper objectMapper = new ObjectMapper();

        // resources文件夹相对路径
        String relativePath = RESP_RELATIVE_PATH;

        // 不是windows默认就是linux，替换文件分隔符
        if (!System.getProperty(OS_NAME).contains(WIN)) {
            relativePath = relativePath.replace("\\", "/");
        }

        // 生成json文件的完整相对地址
        relativePath += methodName + JSON;

        File file = new File(projectRootPath, relativePath);

        //返回值序列化成json
        objectMapper.writeValue(file, (Result) result);

        log.info("JSON file has been written to: " + relativePath);
    }
}