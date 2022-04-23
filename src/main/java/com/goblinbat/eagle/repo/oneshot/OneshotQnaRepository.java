package com.goblinbat.eagle.repo.oneshot;


import com.goblinbat.eagle.entity.oneshot.OneshotQnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName : com.goblinbat.eagle.repo.oneshot
 * fileName : OneshotQnaRepository
 * author : goodhyoju
 * date : 2022/03/21 10:26 PM
 * description :
 */

@Repository
public interface OneshotQnaRepository extends JpaRepository<OneshotQnaEntity, Integer> {
    /**
     *
     * @param keyword
     * @param start
     * @param limit
     * @return
     */
    @Query(value ="SELECT * FROM oneshot_qna w WHERE w.title LIKE %:keyword% OR w.comment LIKE %:keyword%  OR w.writer LIKE %:keyword%  ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<OneshotQnaEntity> findOneshotQnaBySearch(@Param("keyword") String keyword,@Param("start")  int start,@Param("limit")  int limit);

    /**
     *
     * @param keyword
     * @return
     */
    @Query(value ="SELECT count(*) FROM oneshot_qna w WHERE w.title LIKE %:keyword% OR w.comment LIKE %:keyword% OR w.writer LIKE %:keyword%", nativeQuery = true)
    int findOneshotQnaBySearchCount(@Param("keyword") String keyword);

    /**
     *
     * @param start
     * @param limit
     * @return
     */
    @Query(value ="SELECT * FROM oneshot_qna w ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<OneshotQnaEntity> findOneshotQnaAll(@Param("start")  int start,@Param("limit")  int limit);

    /**
     *
     * @return
     */
    @Query(value ="SELECT count(*) FROM oneshot_qna w ORDER by w.time", nativeQuery = true)
    int findOneshotQnaAllCount();

    /**
     *
     * @param idx
     */
    @Modifying
    @Transactional
    @Query("update oneshot_qna w set w.answer = :answer where w.idx = :idx")
    void updateOneshotQnaByAnswer(@Param("idx") Long idx,@Param("answer") String answer);
}


