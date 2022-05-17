package com.goblinbat.eagle.service.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotCallcountEntity;
import com.goblinbat.eagle.repo.oneshot.OneshotCallcountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName : com.goblinbat.eagle.service.oneshot
 * fileName : OneshotCallcountService
 * author : goodhyoju
 * date : 2022/05/17 10:45 PM
 * description :
 */

@Slf4j
@Service
public class OneshotCallcountService {

    @Autowired
    private OneshotCallcountRepository oneshotCallcountRepository;


    /***
     *
     * @param oneshotCallcountEntity
     * @return
     */
    public int updateCallcount(OneshotCallcountEntity oneshotCallcountEntity){
        int result = 0;
        try {
            oneshotCallcountRepository.save(oneshotCallcountEntity);
        }catch (Exception e){
            result  =1;
            oneshotCallcountRepository.updateCallCount(oneshotCallcountEntity.getIpaddress());
          //  log.error(e.getMessage());
        }
        return  result;
    }
}
