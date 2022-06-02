package com.goblinbat.eagle.repo.school;


import com.goblinbat.eagle.entity.school.SchoolNotisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName : com.goblinbat.eagle.repo.oneshot
 * fileName : OneshotNotisRepository
 * author : goodhyoju
 * date : 2022/03/21 10:26 PM
 * description :
 */

@Repository
public interface SchoolNotisRepository extends JpaRepository<SchoolNotisEntity, Integer> {
    /**
     *
     * @param keyword
     * @param start
     * @param limit
     * @return
     */
    @Query(value ="SELECT * FROM school_notis w WHERE w.title LIKE %:keyword% OR w.comment LIKE %:keyword%  ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<SchoolNotisEntity> findSchoolNotisBySearch(@Param("keyword") String keyword, @Param("start")  int start, @Param("limit")  int limit);

    /**
     *
     * @param keyword
     * @return
     */
    @Query(value ="SELECT count(*) FROM school_notis w WHERE w.title LIKE %:keyword% OR w.comment LIKE %:keyword%", nativeQuery = true)
    int findSchoolNotisBySearchCount(@Param("keyword") String keyword);

    /**
     *
     * @param start
     * @param limit
     * @return
     */
    @Query(value ="SELECT * FROM school_notis w ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<SchoolNotisEntity> findSchoolNotisAll(@Param("start")  int start, @Param("limit")  int limit);

    /**
     *
     * @return
     */
    @Query(value ="SELECT count(*) FROM school_notis w ORDER by w.time", nativeQuery = true)
    int findSchoolNotisAllCount();

    @Modifying
    @Transactional
    @Query("update school_notis w set w.rcount = w.rcount+1 where w.idx = :idx")
    void updateSchoolNotisByCount(@Param("idx") Long idx);
}

