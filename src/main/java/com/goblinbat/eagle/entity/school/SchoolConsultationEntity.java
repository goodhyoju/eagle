package com.goblinbat.eagle.entity.school;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * packageName : com.goblinbat.eagle.entity.school
 * fileName : SchoolConsultationEntity
 * author : goodhyoju
 * date : 2022/05/27 10:16 AM
 * description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="school_consultation")
public class SchoolConsultationEntity {
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

    @JoinColumn(name = "email")
    private String email;

    @JoinColumn(name = "info")
    private String info;

    @JoinColumn(name = "status")
    private int status;
}
