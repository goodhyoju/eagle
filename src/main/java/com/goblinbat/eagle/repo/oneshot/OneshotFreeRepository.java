package com.goblinbat.eagle.repo.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotFreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName : com.goblinbat.eagle.repo.oneshot
 * fileName : OneshotFreeRepository
 * author : goodhyoju
 * date : 2022/04/19 10:26 PM
 * description :
 */

@Repository
public interface OneshotFreeRepository extends JpaRepository<OneshotFreeEntity, Integer> {
    @Modifying
    @Transactional
    @Query("delete oneshot_free w where w.idx = :idx")
    void deleteOneshotfreeById(@Param("idx") Long idx);
}


