package com.goblinbat.eagle.entity.oneshot;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * packageName : com.goblinbat.eagle.entity.oneshot
 * fileName : OneshotFreeEntity
 * author : goodhyoju
 * date : 2022/04/19 10:16 PM
 * description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="oneshot_free")
public class OneshotFreeEntity {
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

}
