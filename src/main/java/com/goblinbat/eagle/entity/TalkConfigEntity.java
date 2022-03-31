package com.goblinbat.eagle.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 * packageName : com.goblinbat.eagle.controller
 * fileName : TestController
 * author : goodhyoju
 * date : 2022/03/21 10:26 PM
 * description :
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="talk_config")
public class TalkConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @CreationTimestamp
    @Column(name = "stime")
    private Timestamp stime;

    @Column(name = "division")
    private String division;

    @Column(name = "mtype")
    private String mtype;

    @Column(name = "price")
    private int price;

    @Column(name = "used")
    private int used;

    @Column(name = "names")
    private String names;

    @Column(name = "phones")
    private String phones;

    @Column(name = "template")
    private String template;

    @Column(name = "rdcount")
    private int rdcount;

    @Column(name = "commission")
    private int commission;
}
