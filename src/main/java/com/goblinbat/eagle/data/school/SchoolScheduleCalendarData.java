package com.goblinbat.eagle.data.school;

import lombok.Builder;
import lombok.Data;

/**
 * packageName : com.goblinbat.eagle.data.school
 * fileName : SchoolScheduleCalendarData
 * author : goodhyoju
 * date : 2022/05/26 11:33 AM
 * description :
 */
@Builder
@Data
public class SchoolScheduleCalendarData {
    private Long id;
    private String start;
    private String end;
    private String title;
    private String color;
}
