package com.goblinbat.eagle.entity.school;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * packageName : com.goblinbat.eagle.entity.school
 * fileName : SchoolQnaEntity
 * author : goodhyoju
 * date : 2022/05/26 10:16 AM
 * description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="school_qna")
public class SchoolQnaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @CreationTimestamp
    @JoinColumn(name = "time")
    private Timestamp time;

    @JoinColumn(name = "type")
    private String type;

    @JoinColumn(name = "title")
    private String title;

    @JoinColumn(name = "comment")
    private String comment;

    @JoinColumn(name = "writer")
    private String writer;

    @JoinColumn(name = "answer")
    private String answer;
}
