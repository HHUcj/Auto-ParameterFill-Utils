package com.apf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: PersonDTO
 * Description:
 *
 * @Author: 陈杰
 * @Create: 2024/11/28 - 下午10:49
 * @Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private int id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
