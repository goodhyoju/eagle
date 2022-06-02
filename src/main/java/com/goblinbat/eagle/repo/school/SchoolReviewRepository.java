package com.goblinbat.eagle.repo.school;

import com.goblinbat.eagle.entity.school.SchoolReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.goblinbat.eagle.repo.oneshot
 * fileName : OneshotReviewRepository
 * author : goodhyoju
 * date : 2022/04/18 10:26 PM
 * description :
 */

@Repository
public interface SchoolReviewRepository extends JpaRepository<SchoolReviewEntity, Integer> {
    @Query(value ="SELECT * FROM school_review w ORDER by w.time desc LIMIT :start,:limit  ", nativeQuery = true)
    List<SchoolReviewEntity> findSchoolReviewAll(@Param("start")  int start, @Param("limit")  int limit);
}


