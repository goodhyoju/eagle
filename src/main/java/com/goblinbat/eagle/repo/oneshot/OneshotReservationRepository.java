package com.goblinbat.eagle.repo.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName : com.goblinbat.eagle.repo.oneshot
 * fileName : OneshotReservationRepository
 * author : goodhyoju
 * date : 2022/04/21 10:26 AM
 * description :
 */

@Repository
public interface OneshotReservationRepository extends JpaRepository<OneshotReservationEntity, Integer> {

    List<OneshotReservationEntity> findAllByNameEqualsAndPasswordEquals(@Param("name")  String name, @Param("password")  String password);

    List<OneshotReservationEntity> findAllByNameEqualsAndPhoneEquals(@Param("name")  String name, @Param("phone")  String phone);

    @Query(value ="SELECT * FROM oneshot_reservation w WHERE w.phone =:phone AND w.time BETWEEN DATE_ADD(NOW(),INTERVAL -1 MONTH ) AND NOW()", nativeQuery = true)
    List<OneshotReservationEntity> findAllByPhoneAndMonthAgo(@Param("phone")  String phone);

    @Query(value ="SELECT * FROM oneshot_reservation w ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<OneshotReservationEntity> findOneshotReservationAll(@Param("start")  int start, @Param("limit")  int limit);

    @Query(value ="SELECT * FROM oneshot_reservation w WHERE w.name LIKE %:keyword% OR w.phone LIKE %:keyword% OR w.home_addr LIKE %:keyword% OR w.start_addr LIKE %:keyword% OR w.end_addr LIKE %:keyword% OR w.service LIKE %:keyword%  ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<OneshotReservationEntity> findOneshotReservationBySearch(@Param("keyword") String keyword, @Param("start")  int start, @Param("limit")  int limit);

    @Query(value ="SELECT count(*) FROM oneshot_reservation w WHERE w.name LIKE %:keyword% OR w.phone LIKE %:keyword%  OR w.home_addr LIKE %:keyword% OR w.start_addr LIKE %:keyword% OR w.end_addr LIKE %:keyword% OR w.service", nativeQuery = true)
    int findOneshotReservationBySearchCount(@Param("keyword") String keyword);

    @Modifying
    @Transactional
    @Query("delete oneshot_reservation w where w.idx = :idx")
    void deleteOneshotReservationById(@Param("idx") Long idx);

    @Modifying
    @Transactional
    @Query("update oneshot_reservation w set w.status = :status, w.consultant = :consultant where w.idx = :idx")
    void updateOneshotReservationByIdAndConsultant(@Param("status") int status,@Param("consultant") String consultant,@Param("idx") Long idx);
}


