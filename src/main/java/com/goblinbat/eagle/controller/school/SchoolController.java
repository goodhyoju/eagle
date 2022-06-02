package com.goblinbat.eagle.controller.school;

import com.goblinbat.eagle.entity.school.*;

import com.goblinbat.eagle.service.school.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * packageName : com.goblinbat.eagle.controller.school
 * fileName : SchoolController
 * author : goodhyoju
 * date : 2022/05/26 10:34 AM
 * description :
 */

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolNotisService schoolNotisService;

    @Autowired
    private SchoolQnaService schoolQnaService;

    @Autowired
    private SchoolReviewService schoolReviewService;

    @Autowired
    private SchoolScheduleService schoolScheduleService;

    @Autowired
    private SchoolConsultationService schoolConsultationService;

    /**
     *
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadNotisList")
    public String loadNotisList(HttpServletResponse response, HttpServletRequest request) {
        String jsonResponse = schoolNotisService.getDataTableResponse(request);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        log.debug(jsonResponse.toString());
        return jsonResponse;
    }

    /**
     *
     * @param idx
     * @return
     */
    @PostMapping("/updateNotisCount")
    public int updateCount(@RequestParam Long idx){
        log.debug("idx: {}",idx);
        return schoolNotisService.updateNotisCountIncrement(idx);
    }

    /**
     *
     * @param idx
     * @return
     */
    @PostMapping("/deleteNotis")
    public int deleteNotis(@RequestParam Long idx){
        log.debug("idx: {}",idx);
        return schoolNotisService.delteNotis(idx);
    }

    /**
     *
     * @param schoolNotisEntity
     * @return
     */
    @PostMapping("saveNotis")
    @ResponseBody
    public SchoolNotisEntity saveNotis(@RequestBody SchoolNotisEntity schoolNotisEntity) {
        return schoolNotisService.saveNotis(schoolNotisEntity);
    }

    /**
     *
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadQnaList")
    public String loadQnaList(HttpServletResponse response, HttpServletRequest request) {
        String jsonResponse = schoolQnaService.getDataTableResponse(request);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        log.debug(jsonResponse.toString());
        return jsonResponse;
    }

    /**
     *
     * @param schoolQnaEntity
     * @return
     */
    @PostMapping("updateQna")
    @ResponseBody
    public int updateQna(@RequestBody SchoolQnaEntity schoolQnaEntity) {
        return schoolQnaService.updateQna(schoolQnaEntity);
    }

    /**
     *
     * @param idx
     * @return
     */
    @PostMapping("/deleteQna")
    public int deleteQna(@RequestParam Long idx){
        log.debug("idx: {}",idx);
        return schoolQnaService.delteQna(idx);
    }

    /**
     *
     * @param schoolQnaEntity
     * @return
     */
    @PostMapping("saveQna")
    @ResponseBody
    public SchoolQnaEntity saveQna(@RequestBody SchoolQnaEntity schoolQnaEntity) {
        return schoolQnaService.saveQna(schoolQnaEntity);
    }

    /**
     *
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadConsultationList")
    public String loadConsultationList(HttpServletResponse response, HttpServletRequest request) {
        String jsonResponse = schoolConsultationService.getDataTableResponse(request);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        log.debug(jsonResponse.toString());
        return jsonResponse;
    }

    /**
     *
     * @param schoolConsultationEntity
     * @return
     */
    @PostMapping("updateConsultation")
    @ResponseBody
    public int updateConsultation(@RequestBody SchoolConsultationEntity schoolConsultationEntity) {
        return schoolConsultationService.updateConsultation(schoolConsultationEntity);
    }

    /**
     *
     * @param schoolConsultationEntity
     * @return
     */
    @PostMapping("saveConsultation")
    @ResponseBody
    public SchoolConsultationEntity saveConsultation(@RequestBody SchoolConsultationEntity schoolConsultationEntity) {
        return schoolConsultationService.saveConsultation(schoolConsultationEntity);
    }

    /**
     *
     * @param schoolReviewEntity
     * @return
     */
    @PostMapping("saveReview")
    @ResponseBody
    public SchoolReviewEntity saveReview(@RequestBody SchoolReviewEntity schoolReviewEntity) {
        return schoolReviewService.saveQna(schoolReviewEntity);
    }

    /**
     *
     * @param idx
     * @return
     */
    @PostMapping("/deleteReview")
    public int deleteReview(@RequestParam Long idx){
        log.debug("idx: {}",idx);
        return schoolReviewService.delteReview(idx);
    }

    /**
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/loadReviewList")
    public String loadReviewList(HttpServletResponse response, HttpServletRequest request) {
        String jsonResponse = schoolReviewService.getDataTableResponse(request);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        log.debug(jsonResponse);
        return jsonResponse;
    }

    /**
     *
     * @param schoolScheduleEntity
     * @return
     */
    @PostMapping("saveSchedule")
    @ResponseBody
    public SchoolScheduleEntity saveSchedule(@RequestBody SchoolScheduleEntity schoolScheduleEntity) {
        return schoolScheduleService.saveFree(schoolScheduleEntity);
    }

    /**
     *
     * @param schoolScheduleEntity
     * @return
     */
    @PostMapping("/deleteSchedule")
    @ResponseBody
    public int deleteSchedule(@RequestBody SchoolScheduleEntity schoolScheduleEntity){
        return schoolScheduleService.delteFree(schoolScheduleEntity);
    }

    /**
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/loadSchedule")
    public String loadSchedule(HttpServletResponse response, HttpServletRequest request) {
        String jsonResponse = schoolScheduleService.getDataTableResponse();
        log.debug(jsonResponse);
        return jsonResponse;
    }


}

