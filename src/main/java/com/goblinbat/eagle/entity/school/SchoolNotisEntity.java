package com.goblinbat.eagle.entity.school;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * packageName : com.goblinbat.eagle.entity.school
 * fileName : SchoolNotisEntity
 * author : goodhyoju
 * date : 2022/05/26 10:16 AM
 * description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="school_notis")
public class SchoolNotisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @CreationTimestamp
    @JoinColumn(name = "time")
    private Timestamp time;

    @JoinColumn(name = "site")
    private String site;

    @JoinColumn(name = "type")
    private String type;

    @JoinColumn(name = "title")
    private String title;

    @JoinColumn(name = "comment")
    private String comment;

    @JoinColumn(name = "writer")
    private String writer;

    @JoinColumn(name = "rcount")
    private int rcount;
}
