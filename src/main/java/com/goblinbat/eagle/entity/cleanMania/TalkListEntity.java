package com.goblinbat.eagle.entity.cleanMania;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * packageName : com.goblinbat.eagle.entity.cleanMania
 * fileName : TalkListEntity
 * author : goodhyoju
 * date : 2022/03/25 3:10 PM
 * description :
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="talk_list")
public class TalkListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @CreationTimestamp
    @Column(name = "stime")
    private Timestamp stime;

    @Column(name = "sender_name")
    private String sender_name;

    @Column(name = "receiver_name")
    private String receiver_name;

    @Column(name = "receiver_phone")
    private String receiver_phone;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "customer_phone")
    private String customer_phone;

    @Column(name = "message")
    private String message;

    @Column(name = "commission")
    private int commission;

    @Column(name = "price")
    private int price;
}
