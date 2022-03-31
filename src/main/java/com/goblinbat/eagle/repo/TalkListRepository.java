package com.goblinbat.eagle.repo;

import com.goblinbat.eagle.entity.TalkListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName : com.goblinbat.eagle.controller
 * fileName : TestController
 * author : goodhyoju
 * date : 2022/03/21 10:26 PM
 * description :
 */

@Repository
public interface TalkListRepository extends JpaRepository<TalkListEntity, Long> {
    /**
     *
     * @return
     */
    @Query(value ="SELECT * FROM talk_list group by sender_name,receiver_name", nativeQuery = true)
    List<TalkListEntity> findTalkListEntitiesGroupBySenderName();

    @Query(value ="SELECT * FROM talk_list WHERE idx=:idx", nativeQuery = true)
    TalkListEntity findByRandomId(@Param("idx") int idx);
    /**
     *
     * @param stime
     * @param sender_name
     * @param receiver_name
     * @param receiver_phone
     * @param message
     * @param commission
     * @param price
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO talk_list VALUE(stime=:stime,sender_name=:sender_name,receiver_name=:receiver_name,receiver_phone=:receiver_phone,message=:message,commission=:commission,price=:price)", nativeQuery = true)
    public int  saveToTalkList(@Param("stime") long stime,@Param("sender_name") String sender_name,@Param("receiver_name") String receiver_name,@Param("receiver_phone") String receiver_phone,@Param("message") String message,@Param("commission") int commission,@Param("price") int price);
}
