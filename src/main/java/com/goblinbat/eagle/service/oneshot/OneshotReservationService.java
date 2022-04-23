package com.goblinbat.eagle.service.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotEazyEntity;
import com.goblinbat.eagle.entity.oneshot.OneshotReservationEntity;
import com.goblinbat.eagle.repo.oneshot.OneshotEazyRepository;
import com.goblinbat.eagle.repo.oneshot.OneshotReservationRepository;
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
 * fileName : OneshotReservationService
 * author : goodhyoju
 * date : 2022/04/21 10:30 AM
 * description :
 */
@Slf4j
@Service
public class OneshotReservationService {

    @Autowired
    private OneshotReservationRepository oneshotReservationRepository;

    /**
     *
     * @param oneshotReservation
     * @return
     */
    public OneshotReservationEntity saveReservation(OneshotReservationEntity oneshotReservation){
        OneshotReservationEntity result = null;
        try {
            result = oneshotReservationRepository.save(oneshotReservation);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  result;
    }

    /**
     *
     * @param oneshotReservation
     * @return
     */
    public List<OneshotReservationEntity> findReservationByNamePhone(OneshotReservationEntity oneshotReservation){
        List<OneshotReservationEntity> result = null;
        try {
            result = oneshotReservationRepository.findAllByNameEqualsAndPhoneEquals(oneshotReservation.getName(),oneshotReservation.getPhone());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  result;
    }

    /**
     *
     * @param oneshotReservation
     * @return
     */
    public List<OneshotReservationEntity> findReservationByNamePassword(OneshotReservationEntity oneshotReservation){
        List<OneshotReservationEntity> result = null;
        try {
            result = oneshotReservationRepository.findAllByNameEqualsAndPasswordEquals(oneshotReservation.getName(),oneshotReservation.getPassword());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  result;
    }

    /**
     *
     * @param oneshotReservation
     * @return
     */
    public int delteReservation(OneshotReservationEntity oneshotReservation){
        int result = 0;
        try {
            oneshotReservationRepository.deleteOneshotReservationById(oneshotReservation.getIdx());
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

        List<OneshotReservationEntity> list = new ArrayList<>();

        try {
            int total = (int) oneshotReservationRepository.count();
            int iTotalDisplayRecords = 0;
            if(sSearch == null ||  sSearch.length() < 1){
                list = oneshotReservationRepository.findOneshotReservationAll(Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = total;
            }else{
                list = oneshotReservationRepository.findOneshotReservationBySearch(sSearch,Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = oneshotReservationRepository.findOneshotReservationBySearchCount(sSearch);
            }


            for(OneshotReservationEntity data: list){
                JSONArray ja = new JSONArray();
                ja.put(data.getIdx());
                ja.put(data.getTime());
                ja.put(data.getService());
                ja.put(data.getName());
                ja.put(data.getPhone());
                ja.put(data.getAply_date());
                ja.put(data.getApt_addr());
                ja.put(data.getApt_name());
                ja.put(data.getApt_size());
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
