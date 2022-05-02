package com.goblinbat.eagle.service.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotQnaEntity;
import com.goblinbat.eagle.repo.oneshot.OneshotQnaRepository;
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
 * fileName : OneshotQnaService
 * author : goodhyoju
 * date : 2022/04/16 1:19 PM
 * description :
 */

@Slf4j
@Service
public class OneshotQnaService {

    @Autowired
    private OneshotQnaRepository oneshotQnaRepository;

    /**
     *
     * @param oneshotQnaEntity
     * @return
     */
    public OneshotQnaEntity saveQna(OneshotQnaEntity oneshotQnaEntity){
        OneshotQnaEntity result = null;
        try {
            result = oneshotQnaRepository.save(oneshotQnaEntity);
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
            OneshotQnaEntity qneshotQnaEntity = OneshotQnaEntity.builder().idx(idx).build();
            oneshotQnaRepository.delete(qneshotQnaEntity);
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
    public int updateQna(OneshotQnaEntity qneshotQnaEntity){
        int result = 0;
        try {
            oneshotQnaRepository.updateOneshotQnaByAnswer(qneshotQnaEntity.getIdx(),qneshotQnaEntity.getAnswer());
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

        List<OneshotQnaEntity> list = new ArrayList<>();


        try {
            int total = (int) oneshotQnaRepository.count();
            int iTotalDisplayRecords = 0;
            if(sSearch == null ||  sSearch.length() < 1){
                list = oneshotQnaRepository.findOneshotQnaAll(Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = total;
            }else{
                list = oneshotQnaRepository.findOneshotQnaBySearch(sSearch,Integer.parseInt(sStart),Integer.parseInt(sAmount));
                iTotalDisplayRecords = oneshotQnaRepository.findOneshotQnaBySearchCount(sSearch);
            }


            for(OneshotQnaEntity data: list){
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
