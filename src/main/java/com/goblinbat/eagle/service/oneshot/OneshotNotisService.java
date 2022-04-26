package com.goblinbat.eagle.service.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotNotisEntity;
import com.goblinbat.eagle.repo.oneshot.OneshotNotisRepository;
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
 * fileName : OneshotNotisService
 * author : goodhyoju
 * date : 2022/04/16 1:19 PM
 * description :
 */

@Slf4j
@Service
public class OneshotNotisService {

    @Autowired
    private OneshotNotisRepository oneshotNotisRepository;

    /**
     *
     * @param eneshotNotisEntity
     * @return
     */
    public OneshotNotisEntity saveNotis(OneshotNotisEntity eneshotNotisEntity){
        OneshotNotisEntity result = null;
        try {
            result = oneshotNotisRepository.save(eneshotNotisEntity);
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
            OneshotNotisEntity webNotisEntity = OneshotNotisEntity.builder().idx(idx).build();
            oneshotNotisRepository.delete(webNotisEntity);
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
            oneshotNotisRepository.updateOneshotNotisByCount(idx);
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

        List<OneshotNotisEntity> list = new ArrayList<>();

        try {
            int total = (int) oneshotNotisRepository.count();
            int iTotalDisplayRecords = 0;
            if(sSearch == null ||  sSearch.length() < 1){
                list = oneshotNotisRepository.findOneshotNotisAll(Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = total;
            }else{
                list = oneshotNotisRepository.findOneshotNotisBySearch(sSearch,Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = oneshotNotisRepository.findOneshotNotisBySearchCount(sSearch);
            }


            for(OneshotNotisEntity data: list){
                JSONArray ja = new JSONArray();
              //  ja.put("");
                ja.put(data.getIdx());
                ja.put("");
                ja.put(data.getType());
                ja.put(data.getTitle());
                ja.put(data.getComment());
                ja.put(data.getTime());
                ja.put(data.getWriter());
                ja.put(data.getRcount());
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
