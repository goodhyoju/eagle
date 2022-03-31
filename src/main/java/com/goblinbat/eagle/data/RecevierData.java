package com.goblinbat.eagle.data;

import lombok.Builder;
import lombok.Data;

/**
 * packageName : com.goblinbat.eagle.data
 * fileName : RecevierData
 * author : goodhyoju
 * date : 2022/03/25 2:31 PM
 * description :
 */

@Data
@Builder
public class RecevierData {
    private String name;
    private String phone;
}
