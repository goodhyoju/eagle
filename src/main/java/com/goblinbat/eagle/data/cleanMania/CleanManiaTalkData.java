package com.goblinbat.eagle.data.cleanMania;

import lombok.Data;

/**
 * packageName : com.goblinbat.eagle.data.cleanMania
 * fileName : CleanManiaTalkData
 * author : goodhyoju
 * date : 2022/03/24 9:22 AM
 * description :
 */

@Data
public class CleanManiaTalkData {
    private String template;
    private String name;
    private String phone;
    private String movedate;
    private String start;
    private String end;
    private String receivernum;  // talkconfig에서 받아옴
    private String sender;
    private String sendernum;

    private String type;
    private String selectName;
    private String selectPhone;
}
