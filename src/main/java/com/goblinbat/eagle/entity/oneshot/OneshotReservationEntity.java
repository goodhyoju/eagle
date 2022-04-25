package com.goblinbat.eagle.entity.oneshot;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * packageName : com.goblinbat.eagle.entity.oneshot
 * fileName : Oneshot
 * author : goodhyoju
 * date : 2022/04/21 9:40 AM
 * description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="oneshot_reservation")
public class OneshotReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @CreationTimestamp
    @JoinColumn(name = "time")
    private Timestamp time;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "phone")
    private String phone;


    @JoinColumn(name = "home_addr")
    private String home_addr;

    @JoinColumn(name = "start_addr")
    private String start_addr;

    @JoinColumn(name = "end_addr")
    private String end_addr;

    @JoinColumn(name = "home_size")
    private int home_size;

    @JoinColumn(name = "aply_date")
    private String aply_date;

    @JoinColumn(name = "service")
    private String service;

    @JoinColumn(name = "password")
    private String password;

    @JoinColumn(name = "status")
    private int status;
}
