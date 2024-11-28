package com.apf.controller;

import com.apf.dto.DistrictDTO;
import com.apf.dto.PersonDTO;
import com.apf.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * ClassName: DemoCOntroller
 * Description:
 *
 * @Author: 陈杰
 * @Create: 2024/11/28 - 下午5:21
 * @Version: v1.0
 */
@RestController
@ResponseBody
public class DemoController {

    // 多参数情况
    @GetMapping("/demo/get")
    public Result demo1(@RequestParam Integer[] ids, @RequestParam Integer[] ods) {
        return Result.success(ids);
    }

    // 路径传参情况
    @GetMapping("/demo/{status}")
    public Result demo2(@PathVariable Integer status) {
        return Result.success(status);
    }

    // 普通DTO情况
    @PostMapping("/demo/post/person")
    public Result demo3(@RequestBody PersonDTO personDTO) {
        return Result.success(personDTO);
    }

    // 嵌套DTO情况
    @PostMapping("/demo/post/district")
    public Result demo4(@RequestBody DistrictDTO districtDTO) {
        return Result.success(districtDTO);
    }
}
