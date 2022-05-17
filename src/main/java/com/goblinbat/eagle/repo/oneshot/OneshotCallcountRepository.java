package com.goblinbat.eagle.repo.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotCallcountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName : com.goblinbat.eagle.repo.oneshot
 * fileName : OneshotCallcountRepository
 * author : goodhyoju
 * date : 2022/05/17 10:37 PM
 * description :
 */

public interface OneshotCallcountRepository extends JpaRepository<OneshotCallcountEntity, Integer> {

    @Modifying
    @Transactional
    @Query("update oneshot_callcount w set w.call_count=w.call_count+1 where w.ipaddress = :ip")
    void updateCallCount(@Param("ip") String ipaddress);
}
