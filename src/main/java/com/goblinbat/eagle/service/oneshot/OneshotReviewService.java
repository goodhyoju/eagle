package com.goblinbat.eagle.service.oneshot;

import com.goblinbat.eagle.entity.oneshot.OneshotReviewEntity;
import com.goblinbat.eagle.repo.oneshot.OneshotReviewRepository;
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
 * fileName : OneshotReviewService
 * author : goodhyoju
 * date : 2022/04/16 1:19 PM
 * description :
 */

@Slf4j
@Service
public class OneshotReviewService {

    @Autowired
    private OneshotReviewRepository oneshotReviewRepository;

    /**
     *
     * @param oneshotReviewEntity
     * @return
     */
    public OneshotReviewEntity saveQna(OneshotReviewEntity oneshotReviewEntity){
        OneshotReviewEntity result = null;
        try {
            result = oneshotReviewRepository.save(oneshotReviewEntity);
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
    public int delteReview(Long idx){
        int result = 0;
        try {
            OneshotReviewEntity oneshotReviewEntity = OneshotReviewEntity.builder().idx(idx).build();
            oneshotReviewRepository.delete(oneshotReviewEntity);
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
    public String getDataTableResponse(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();

        String sStart = StringUtils.defaultIfEmpty(request.getParameter("iDisplayStart"),"0");
        String sAmount = StringUtils.defaultIfEmpty(request.getParameter("iDisplayLength"),"0");
        //String sSearch = StringUtils.defaultIfEmpty(request.getParameter("sSearch"),"");
        String sEcho = StringUtils.defaultIfEmpty(request.getParameter("sEcho"),"0");

        List<OneshotReviewEntity> list = new ArrayList<>();


        try {
            int total = (int) oneshotReviewRepository.count();
            int iTotalDisplayRecords = 0;
            list = oneshotReviewRepository.findOneshotReviewAll(Integer.parseInt(sStart),Integer.parseInt(sAmount));
            iTotalDisplayRecords = total;


            for(OneshotReviewEntity data: list){
                JSONArray ja = new JSONArray();
                ja.put(data.getIdx());
                ja.put(data.getType());
                ja.put(data.getScope());
                ja.put(data.getComment());
                ja.put(data.getWriter());
                ja.put(data.getTime());
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
