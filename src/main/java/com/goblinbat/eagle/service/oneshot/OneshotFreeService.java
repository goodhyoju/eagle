package com.goblinbat.eagle.service.oneshot;

import com.goblinbat.eagle.data.oneshot.OneshotFreeCalendarData;
import com.goblinbat.eagle.entity.oneshot.OneshotFreeEntity;
import com.goblinbat.eagle.repo.oneshot.OneshotFreeRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.goblinbat.eagle.service.oneshot
 * fileName : OneshotFreeService
 * author : goodhyoju
 * date : 2022/04/19 1:19 PM
 * description :
 */

@Slf4j
@Service
public class OneshotFreeService {

    @Autowired
    private OneshotFreeRepository oneshotFreeRepository;

    /**
     *
     * @param oneshotFreeEntity
     * @return
     */
    public OneshotFreeEntity saveFree(OneshotFreeEntity oneshotFreeEntity){
        OneshotFreeEntity result = null;
        try {
            result = oneshotFreeRepository.save(oneshotFreeEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  result;
    }

    /**
     *
     * @param oneshotFreeEntity
     * @return
     */
    public int delteFree(OneshotFreeEntity oneshotFreeEntity){
        int result = 0;
        try {
            oneshotFreeRepository.deleteOneshotfreeById(oneshotFreeEntity.getIdx());
        }catch (Exception e){
            log.error(e.getMessage());
            return 1;
        }
        return result;
    }


    /**
     *
     * @return
     */
    public String getDataTableResponse() {
        Gson gson = new Gson();
        List<OneshotFreeEntity> list = oneshotFreeRepository.findAll();
        List<OneshotFreeCalendarData> cList = new ArrayList<>();

        if(list.size() > 0){
            for(OneshotFreeEntity data : list){
                OneshotFreeCalendarData temp = OneshotFreeCalendarData.builder().id(data.getIdx()).start(data.getStart()).end(data.getEnd()).title(data.getTitle()).build();
                cList.add(temp);
            }
        }
        return gson.toJson(cList);
    }

}
