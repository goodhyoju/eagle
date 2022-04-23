package com.goblinbat.eagle.entity.oneshot;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * packageName : com.goblinbat.eagle.entity.oneshot
 * fileName : OneshotNotisEntity
 * author : goodhyoju
 * date : 2022/04/16 1:16 PM
 * description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="oneshot_notis")
public class OneshotNotisEntity {
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
