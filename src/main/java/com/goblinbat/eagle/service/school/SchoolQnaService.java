package com.goblinbat.eagle.service.school;


import com.goblinbat.eagle.entity.school.SchoolQnaEntity;
import com.goblinbat.eagle.repo.school.SchoolQnaRepository;
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
 * fileName : SchoolQnaService
 * author : goodhyoju
 * date : 2022/05/26 10:19 AM
 * description :
 */

@Slf4j
@Service
public class SchoolQnaService {

    @Autowired
    private SchoolQnaRepository schoolQnaRepository;

    /**
     *
     * @param schoolQnaEntity
     * @return
     */
    public SchoolQnaEntity saveQna(SchoolQnaEntity schoolQnaEntity){
        SchoolQnaEntity result = null;
        try {
            result = schoolQnaRepository.save(schoolQnaEntity);
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
    public int delteQna(Long idx){
        int result = 0;
        try {
            SchoolQnaEntity schoolQnaEntity = SchoolQnaEntity.builder().idx(idx).build();
            schoolQnaRepository.delete(schoolQnaEntity);
        }catch (Exception e){
            log.error(e.getMessage());
            return 1;
        }
        return result;
    }

    /**
     *
     * @param qneshotQnaEntity
     * @return
     */
    public int updateQna(SchoolQnaEntity qneshotQnaEntity){
        int result = 0;
        try {
            schoolQnaRepository.updateSchoolQnaByAnswer(qneshotQnaEntity.getIdx(),qneshotQnaEntity.getAnswer());
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

        List<SchoolQnaEntity> list = new ArrayList<>();


        try {
            int total = (int) schoolQnaRepository.count();
            int iTotalDisplayRecords = 0;
            if(sSearch == null ||  sSearch.length() < 1){
                list = schoolQnaRepository.findSchoolQnaAll(Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = total;
            }else{
                list = schoolQnaRepository.findSchoolQnaBySearch(sSearch,Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = schoolQnaRepository.findSchoolQnaBySearchCount(sSearch);
            }


            for(SchoolQnaEntity data: list){
                JSONArray ja = new JSONArray();
                String setStatus="0";
                if(data.getAnswer()!=null &&data.getAnswer().length() > 1){
                    setStatus="1";
                }

                ja.put(data.getIdx());
                ja.put(data.getType());
                ja.put(setStatus);
                ja.put(data.getTitle());
                ja.put(data.getWriter());
                ja.put(data.getTime());
                ja.put(data.getComment());
                ja.put(data.getAnswer());
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
