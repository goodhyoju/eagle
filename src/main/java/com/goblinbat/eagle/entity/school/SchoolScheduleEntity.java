package com.goblinbat.eagle.entity.school;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * packageName : com.goblinbat.eagle.entity.school
 * fileName : SchoolScheduleEntity
 * author : goodhyoju
 * date : 2022/05/26 10:16 AM
 * description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="school_schedule")
public class SchoolScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @CreationTimestamp
    @JoinColumn(name = "time")
    private Timestamp time;

    @JoinColumn(name = "start")
    private String start;

    @JoinColumn(name = "end")
    private String end;

    @JoinColumn(name = "title")
    private String title;

    @JoinColumn(name = "color")
    private String color;
}
