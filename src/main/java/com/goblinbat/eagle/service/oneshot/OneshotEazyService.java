package com.goblinbat.eagle.service.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotEazyEntity;
import com.goblinbat.eagle.repo.oneshot.OneshotEazyRepository;
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
 * packageName : com.goblinbat.eagle.service.oneshot
 * fileName : OneshotEazyService
 * author : goodhyoju
 * date : 2022/04/20 1:30 PM
 * description :
 */
@Slf4j
@Service
public class OneshotEazyService {

    @Autowired
    private OneshotEazyRepository oneshotEazyRepository;

    /**
     *
     * @param oneshotEazyEntity
     * @return
     */
    public OneshotEazyEntity saveEazy(OneshotEazyEntity oneshotEazyEntity){
        OneshotEazyEntity result = null;
        try {
            result = oneshotEazyRepository.save(oneshotEazyEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  result;
    }

    /**
     *
     * @param oneshotEazyEntity
     * @return
     */
    public List<OneshotEazyEntity> findEazy(OneshotEazyEntity oneshotEazyEntity){
        List<OneshotEazyEntity> result = null;
        try {
            result = oneshotEazyRepository.findAllByNameEqualsAndPhoneEquals(oneshotEazyEntity.getName(),oneshotEazyEntity.getPhone());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  result;
    }

    /**
     *
     * @param oneshotEazyEntity
     * @return
     */
    public int delteEazy(OneshotEazyEntity oneshotEazyEntity){
        int result = 0;
        try {
            oneshotEazyRepository.deleteOneshotEazyById(oneshotEazyEntity.getIdx());
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

        List<OneshotEazyEntity> list = new ArrayList<>();

        try {
            int total = (int) oneshotEazyRepository.count();
            int iTotalDisplayRecords = 0;
            if(sSearch == null ||  sSearch.length() < 1){
                list = oneshotEazyRepository.findOneshotEazyAll(Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = total;
            }else{
                list = oneshotEazyRepository.findOneshotEazyBySearch(sSearch,Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = oneshotEazyRepository.findOneshotEazyBySearchCount(sSearch);
            }


            for(OneshotEazyEntity data: list){
                JSONArray ja = new JSONArray();
                ja.put(data.getIdx());
                ja.put(data.getTime());
                ja.put(data.getService());
                ja.put(data.getName());
                ja.put(data.getPhone());
                ja.put(data.getStatus());
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
