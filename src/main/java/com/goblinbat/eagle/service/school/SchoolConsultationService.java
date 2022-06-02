package com.goblinbat.eagle.service.school;

import com.goblinbat.eagle.entity.school.SchoolConsultationEntity;
import com.goblinbat.eagle.repo.school.SchoolConsultationRepository;
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
 * fileName : SchoolConsultationService
 * author : goodhyoju
 * date : 2022/05/27 9:43 AM
 * description :
 */
@Slf4j
@Service
public class SchoolConsultationService {

    @Autowired
    private SchoolConsultationRepository schoolConsultationRepository;

    /**
     *
     * @param schoolConsultationEntity
     * @return
     */
    public SchoolConsultationEntity saveConsultation(SchoolConsultationEntity schoolConsultationEntity){
        SchoolConsultationEntity result = null;
        try {
            result = schoolConsultationRepository.save(schoolConsultationEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  result;
    }

    /**
     *
     * @param schoolConsultationEntity
     * @return
     */
    public int updateConsultation(SchoolConsultationEntity schoolConsultationEntity){
        int result = 0;
        try {
            schoolConsultationRepository.updateSchoolConsultationByStatus(schoolConsultationEntity.getIdx(),schoolConsultationEntity.getStatus());
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

        List<SchoolConsultationEntity> list = new ArrayList<>();


        try {
            int total = (int) schoolConsultationRepository.count();
            int iTotalDisplayRecords = 0;
            if(sSearch == null ||  sSearch.length() < 1){
                list = schoolConsultationRepository.findSchoolConsultationAll(Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = total;
            }else{
                list = schoolConsultationRepository.findSchoolConsultationBySearch(sSearch,Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = schoolConsultationRepository.findSchoolConsultationBySearchCount(sSearch);
            }


            for(SchoolConsultationEntity data: list){
                JSONArray ja = new JSONArray();

                ja.put(data.getIdx());
                ja.put(data.getName());
                ja.put(data.getPhone());
                ja.put(data.getEmail());
                ja.put(data.getInfo());
                ja.put(data.getTime());
                ja.put(data.getStatus());
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
