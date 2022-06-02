package com.goblinbat.eagle.service.school;

import com.goblinbat.eagle.entity.oneshot.OneshotNotisEntity;
import com.goblinbat.eagle.entity.school.SchoolNotisEntity;
import com.goblinbat.eagle.repo.school.SchoolNotisRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.goblinbat.eagle.service.school
 * fileName : SchoolNotisService
 * author : goodhyoju
 * date : 2022/05/26 10:19 AM
 * description :
 */

@Slf4j
@Service
public class SchoolNotisService {

    @Autowired
    private SchoolNotisRepository schoolNotisRepository;

    /**
     *
     * @param schoolNotisEntity
     * @return
     */
    public SchoolNotisEntity saveNotis(SchoolNotisEntity schoolNotisEntity){
        SchoolNotisEntity result = null;
        try {
            result = schoolNotisRepository.save(schoolNotisEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  result;
    }

    /**
     *
     * @param idx
     * @return
     */
    public int delteNotis(Long idx){
        int result = 0;
        try {
            SchoolNotisEntity schoolNotisEntity = SchoolNotisEntity.builder().idx(idx).build();
            schoolNotisRepository.delete(schoolNotisEntity);
        }catch (Exception e){
            log.error(e.getMessage());
            return 1;
        }
        return result;
    }

    /**
     *
     * @param idx
     * @return
     */
    public int updateNotisCountIncrement(Long idx){
        int result = 0;
        try {
            schoolNotisRepository.updateSchoolNotisByCount(idx);
        }catch (Exception e){
            log.error(e.getMessage());
            return 1;
        }
        return result;
    }

    /**
     *
     * @param request
     * @return
     */
    public String getDataTableResponse(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();

        String sStart = StringUtils.defaultIfEmpty(request.getParameter("iDisplayStart"),"0");
        String sAmount = StringUtils.defaultIfEmpty(request.getParameter("iDisplayLength"),"0");
        String sSearch = StringUtils.defaultIfEmpty(request.getParameter("sSearch"),"");
        String sEcho = StringUtils.defaultIfEmpty(request.getParameter("sEcho"),"0");

        List<SchoolNotisEntity> list = new ArrayList<>();

        try {
            int total = (int) schoolNotisRepository.count();
            int iTotalDisplayRecords = 0;
            if(sSearch == null ||  sSearch.length() < 1){
                list = schoolNotisRepository.findSchoolNotisAll(Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = total;
            }else{
                list = schoolNotisRepository.findSchoolNotisBySearch(sSearch,Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = schoolNotisRepository.findSchoolNotisBySearchCount(sSearch);
            }


            for(SchoolNotisEntity data: list){
                JSONArray ja = new JSONArray();
                ja.put(data.getIdx());
                ja.put(data.getType());
                ja.put(data.getTitle());
                ja.put(data.getComment());
                ja.put(data.getTime());
                ja.put(data.getWriter());
                ja.put(data.getRcount());
                ja.put("");
                array.put(ja);
            }

            result.put("iTotalRecords", total);
            result.put("iTotalDisplayRecords", iTotalDisplayRecords);
            result.put("aaData", array);
            result.put("sEcho", Integer.valueOf(sEcho));

        }catch (Exception e){
            log.error(e.getMessage());
        }
        return result.toString();
    }

}
