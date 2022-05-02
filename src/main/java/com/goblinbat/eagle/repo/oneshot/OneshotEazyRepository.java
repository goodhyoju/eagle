package com.goblinbat.eagle.repo.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotEazyEntity;
import com.goblinbat.eagle.entity.oneshot.OneshotNotisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName : com.goblinbat.eagle.repo.oneshot
 * fileName : OneshotEazyRepository
 * author : goodhyoju
 * date : 2022/04/19 10:26 PM
 * description :
 */

@Repository
public interface OneshotEazyRepository extends JpaRepository<OneshotEazyEntity, Integer> {


    List<OneshotEazyEntity> findAllByNameEqualsAndPhoneEquals(@Param("name")  String name, @Param("phone")  String phone);

    @Query(value ="SELECT * FROM oneshot_eazy w ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<OneshotEazyEntity> findOneshotEazyAll(@Param("start")  int start, @Param("limit")  int limit);

    @Query(value ="SELECT * FROM oneshot_eazy w WHERE w.name LIKE %:keyword% OR w.phone LIKE %:keyword%  ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<OneshotEazyEntity> findOneshotEazyBySearch(@Param("keyword") String keyword, @Param("start")  int start, @Param("limit")  int limit);

    @Query(value ="SELECT count(*) FROM oneshot_eazy w WHERE w.name LIKE %:keyword% OR w.phone LIKE %:keyword%", nativeQuery = true)
    int findOneshotEazyBySearchCount(@Param("keyword") String keyword);

    @Modifying
    @Transactional
    @Query("delete oneshot_eazy w where w.idx = :idx")
    void deleteOneshotEazyById(@Param("idx") Long idx);

    @Modifying
    @Transactional
    @Query("update oneshot_eazy w set w.status = :status, w.consultant = :consultant where w.idx = :idx")
    void updateOneshotEazyByIdAndConsultant(@Param("status") int status,@Param("consultant") String consultant,@Param("idx") Long idx);
}


