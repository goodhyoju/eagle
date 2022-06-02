package com.goblinbat.eagle.repo.school;

import com.goblinbat.eagle.entity.school.SchoolScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName : com.goblinbat.eagle.repo.school
 * fileName : SchoolScheduleRepository
 * author : goodhyoju
 * date : 2022/05/26 10:26 AM
 * description :
 */

@Repository
public interface SchoolScheduleRepository extends JpaRepository<SchoolScheduleEntity, Integer> {
    @Modifying
    @Transactional
    @Query("delete school_schedule w where w.idx = :idx")
    void deleteSchoolSchedulefreeById(@Param("idx") Long idx);
}


