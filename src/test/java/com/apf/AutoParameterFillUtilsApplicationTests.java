package com.apf;

import com.apf.controller.DemoController;
import com.apf.dto.PersonDTO;
import com.apf.result.Result;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.apf.constant.Constants.JSON;
import static com.apf.constant.Constants.OS_NAME;
import static com.apf.constant.Constants.REQ_RELATIVE_PATH;
import static com.apf.constant.Constants.RESP_RELATIVE_PATH;
import static com.apf.constant.Constants.USER_DIR;
import static com.apf.constant.Constants.WIN;

@SpringBootTest
class AutoParameterFillUtilsApplicationTests {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DemoController demoController;

    private String projectRootPath;
    private String reqQelativePath;
    private String respQelativePath;

    @BeforeEach
    public void setUp(){
        projectRootPath = System.getProperty(USER_DIR);
        reqQelativePath = REQ_RELATIVE_PATH;
        respQelativePath = RESP_RELATIVE_PATH;
        // 不是windows默认就是linux，替换文件分隔符
        if (!System.getProperty(OS_NAME).contains(WIN)) {
            reqQelativePath = reqQelativePath.replace("\\", "/");
            respQelativePath = respQelativePath.replace("\\", "/");
        }
    }

    @Test
    void testDemo3() throws IOException {
        String methodName = "demo3";
        // 生成json文件的完整相对地址
        reqQelativePath += methodName + JSON;
        respQelativePath += methodName + JSON;

        File reqFile = new File(projectRootPath, reqQelativePath);
        File respFile = new File(projectRootPath, respQelativePath);

        // 兼容多参数情况，要用list接收
        List<Object> personDTOList = objectMapper.readValue(reqFile, List.class);

        // 按需传参，这里demo3只有一个参数，有多个参数要先解析list，jackson默认会把带复杂类型的pojo对象用linkedHashmap存储，用convertValue转换
        Result realResult = demoController.demo3(objectMapper.convertValue(personDTOList.get(0), PersonDTO.class));
        Result expectResult = objectMapper.readValue(respFile, Result.class);
        PersonDTO personDTO1 = objectMapper.convertValue(expectResult.getData(), PersonDTO.class);
        expectResult.setData(personDTO1);
        Assert.isTrue(Objects.equals(expectResult, realResult));
    }
}
