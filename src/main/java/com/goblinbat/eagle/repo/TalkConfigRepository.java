package com.goblinbat.eagle.repo;

import com.goblinbat.eagle.entity.TalkConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 * packageName : com.goblinbat.eagle.controller
 * fileName : TestController
 * author : goodhyoju
 * date : 2022/03/21 10:26 PM
 * description :
 */

@Repository
public interface TalkConfigRepository extends JpaRepository<TalkConfigEntity, Long> {
    /**
     * all change for used
     * @param pre_used
     * @param next_used
     */
    @Modifying
    @Query("update talk_config t set t.used = :next_used where t.used = :pre_used and t.idx <> :idx")
    void setUsed(@Param("pre_used") int next_used, @Param("next_used") int pre_used, @Param("idx") long idx);
    TalkConfigEntity findTalkConfigEntitiesByUsed(int used);

    TalkConfigEntity findTalkConfigEntitiesByUsedAndMtype(int used, String mtype);
}
