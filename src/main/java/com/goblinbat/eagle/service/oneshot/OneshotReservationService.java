package com.goblinbat.eagle.service.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotReservationEntity;
import com.goblinbat.eagle.repo.oneshot.OneshotReservationRepository;
import com.goblinbat.eagle.utils.DateUtils;
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

    @Autowired
    private DateUtils dateUtils;

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
    public int deleteReservation(OneshotReservationEntity oneshotReservation){
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
     * @param oneshotReservation
     * @return
     */
    public int updateReservation(OneshotReservationEntity oneshotReservation){
        int result = 0;
        try {
            oneshotReservationRepository.updateOneshotReservationByIdAndConsultant(oneshotReservation.getStatus(),oneshotReservation.getConsultant(), oneshotReservation.getIdx());
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

            StringBuffer setAddr = new StringBuffer();
            for(OneshotReservationEntity data: list){
                JSONArray ja = new JSONArray();
                ja.put(data.getIdx());
                ja.put(data.getService());
                ja.put(data.getName());
                ja.put(data.getPhone());
                String changeAplyData = data.getAply_date();
                try {
                    changeAplyData = dateUtils.changeFormat(data.getAply_date(),"yyyy-MM-dd hh:mm:ss","yyyy-MM-dd hh:mm");
                }catch (Exception e){
                    log.error(e.getMessage());
                }

                setAddr.append("<i class='fa fa-calendar'></i> 신청일: ").append(changeAplyData);
                setAddr.append("<br/><i class='fa fa-home'></i> 실거주: ").append(data.getHome_addr());
                if(!data.getStart_addr().isEmpty()){
                    setAddr.append("<br/><i class='fa fa-arrow-right'></i> 출발지: ").append(data.getStart_addr());
                }
                if(!data.getEnd_addr().isEmpty()){
                    setAddr.append("<br/><i class='fa fa-arrow-left'></i> 목적지: ").append(data.getEnd_addr());
                }
                ja.put(setAddr.toString());
                ja.put(data.getHome_size());

                ja.put(data.getTime());
                ja.put(data.getStatus());
                ja.put(data.getConsultant());
                ja.put("");
                array.put(ja);
                setAddr.setLength(0);
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
