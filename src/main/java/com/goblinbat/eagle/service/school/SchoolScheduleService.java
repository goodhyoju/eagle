package com.goblinbat.eagle.service.school;

import com.goblinbat.eagle.data.school.SchoolScheduleCalendarData;
import com.goblinbat.eagle.entity.school.SchoolScheduleEntity;
import com.goblinbat.eagle.repo.school.SchoolScheduleRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.goblinbat.eagle.service.school
 * fileName : SchoolScheduleService
 * author : goodhyoju
 * date : 2022/04/19 1:19 PM
 * description :
 */

@Slf4j
@Service
public class SchoolScheduleService {

    @Autowired
    private SchoolScheduleRepository schoolScheduleRepository;

    /**
     *
     * @param schoolScheduleEntity
     * @return
     */
    public SchoolScheduleEntity saveFree(SchoolScheduleEntity schoolScheduleEntity){
        SchoolScheduleEntity result = null;
        try {
            result = schoolScheduleRepository.save(schoolScheduleEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  result;
    }

    /**
     *
     * @param schoolScheduleEntity
     * @return
     */
    public int delteFree(SchoolScheduleEntity schoolScheduleEntity){
        int result = 0;
        try {
            schoolScheduleRepository.deleteSchoolSchedulefreeById(schoolScheduleEntity.getIdx());
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
        List<SchoolScheduleEntity> list = schoolScheduleRepository.findAll();
        List<SchoolScheduleCalendarData> cList = new ArrayList<>();

        if(list.size() > 0){
            for(SchoolScheduleEntity data : list){
                SchoolScheduleCalendarData temp = SchoolScheduleCalendarData.builder().id(data.getIdx()).start(data.getStart()).end(data.getEnd()).title(data.getTitle()).color(data.getColor()).build();
                cList.add(temp);
            }
        }
        return gson.toJson(cList);
    }

}
