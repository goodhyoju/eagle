package com.goblinbat.eagle.repo.oneshot;


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
 * fileName : OneshotNotisRepository
 * author : goodhyoju
 * date : 2022/03/21 10:26 PM
 * description :
 */

@Repository
public interface OneshotNotisRepository extends JpaRepository<OneshotNotisEntity, Integer> {
    /**
     *
     * @param keyword
     * @param start
     * @param limit
     * @return
     */
    @Query(value ="SELECT * FROM oneshot_notis w WHERE w.title LIKE %:keyword% OR w.comment LIKE %:keyword%  ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<OneshotNotisEntity> findOneshotNotisBySearch(@Param("keyword") String keyword, @Param("start")  int start, @Param("limit")  int limit);

    /**
     *
     * @param keyword
     * @return
     */
    @Query(value ="SELECT count(*) FROM oneshot_notis w WHERE w.title LIKE %:keyword% OR w.comment LIKE %:keyword%", nativeQuery = true)
    int findOneshotNotisBySearchCount(@Param("keyword") String keyword);

    /**
     *
     * @param start
     * @param limit
     * @return
     */
    @Query(value ="SELECT * FROM oneshot_notis w ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<OneshotNotisEntity> findOneshotNotisAll(@Param("start")  int start, @Param("limit")  int limit);

    /**
     *
     * @return
     */
    @Query(value ="SELECT count(*) FROM oneshot_notis w ORDER by w.time", nativeQuery = true)
    int findOneshotNotisAllCount();

    @Modifying
    @Transactional
    @Query("update oneshot_notis w set w.rcount = w.rcount+1 where w.idx = :idx")
    void updateOneshotNotisByCount(@Param("idx") Long idx);
}

