package com.apf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: DistrictDTO
 * Description:
 *
 * @Author: 陈杰
 * @Create: 2024/11/28 - 下午10:51
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {
    private int id;
    private PersonDTO person;

    @Override
    public String toString() {
        return "DistrictDTO{" +
                "id=" + id +
                ", person=" + person +
                '}';
    }
}
