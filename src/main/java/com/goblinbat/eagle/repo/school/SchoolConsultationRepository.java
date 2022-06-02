package com.goblinbat.eagle.repo.school;


import com.goblinbat.eagle.entity.school.SchoolConsultationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName : com.goblinbat.eagle.repo.oneshot
 * fileName : SchoolConsultationRepository
 * author : goodhyoju
 * date : 2022/05/27 10:26 AM
 * description :
 */

@Repository
public interface SchoolConsultationRepository extends JpaRepository<SchoolConsultationEntity, Integer> {
    /**
     *
     * @param keyword
     * @param start
     * @param limit
     * @return
     */
    @Query(value ="SELECT * FROM school_consultation w WHERE w.name LIKE %:keyword% OR w.phone LIKE %:keyword%  ORDER by w.time desc LIMIT :start,:limit", nativeQuery = true)
    List<SchoolConsultationEntity> findSchoolConsultationBySearch(@Param("keyword") String keyword,@Param("start")  int start,@Param("limit")  int limit);

    /**
     *
     * @param keyword
     * @return
     */
    @Query(value ="SELECT count(*) FROM school_consultation w WHERE w.name LIKE %:keyword% OR w.phone LIKE %:keyword%", nativeQuery = true)
    int findSchoolConsultationBySearchCount(@Param("keyword") String keyword);

    /**
     *
     * @param start
     * @param limit
     * @return
     */
    @Query(value ="SELECT * FROM school_consultation w ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<SchoolConsultationEntity> findSchoolConsultationAll(@Param("start")  int start,@Param("limit")  int limit);

    /**
     *
     * @return
     */
    @Query(value ="SELECT count(*) FROM school_consultation w ORDER by w.time", nativeQuery = true)
    int findSchoolConsultationAllCount();

    /**
     *
     * @param idx
     */
    @Modifying
    @Transactional
    @Query("update school_consultation w set w.status = :status where w.idx = :idx")
    void updateSchoolConsultationByStatus(@Param("idx") Long idx,@Param("status") int status);
}


