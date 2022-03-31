package com.goblinbat.eagle.scheduler;

import com.goblinbat.eagle.entity.TalkListEntity;
import com.goblinbat.eagle.repo.TalkListRepository;
import com.goblinbat.eagle.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Random;

/**
 * packageName : com.goblinbat.eagle.scheduler
 * fileName : DataScheduler
 * author : goodhyoju
 * date : 2022/03/29 5:43 PM
 * description :
 */
@Slf4j
@Controller
public class DataScheduler {

    @Autowired
    private TalkListRepository talkListRepository;

    //@Scheduled(cron="0/10 * * * * *")
    //@Scheduled(fixedRate = 300)
    public void test_create_talkList(){
        Random rd = new Random();
        List<TalkListEntity> list = talkListRepository.findTalkListEntitiesGroupBySenderName();
        if(list != null && list.size() > 0) {
            TalkListEntity talkListEntity = list.get(rd.nextInt(list.size()));


            TalkListEntity temp = TalkListEntity.builder()
                    .sender_name(talkListEntity.getSender_name())
                    .receiver_name(talkListEntity.getReceiver_name())
                    .receiver_phone(talkListEntity.getReceiver_phone())
                    .message(talkListEntity.getMessage())
                    .commission(talkListEntity.getCommission())
                    .price(talkListEntity.getPrice())
                    .build();


            log.debug(">> create random talk list: [{}]", temp.toString());
            talkListRepository.save(temp);
        }
    }
}
