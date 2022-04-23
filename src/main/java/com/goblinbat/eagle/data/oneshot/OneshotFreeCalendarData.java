package com.goblinbat.eagle.data.oneshot;

import lombok.Builder;
import lombok.Data;

/**
 * packageName : com.goblinbat.eagle.data.oneshot
 * fileName : OneshotFreeCalendarData
 * author : goodhyoju
 * date : 2022/04/19 11:33 PM
 * description :
 */
@Builder
@Data
public class OneshotFreeCalendarData {
    private Long id;
    private String start;
    private String end;
    private String title;
}
