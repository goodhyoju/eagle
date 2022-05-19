package com.goblinbat.eagle.controller.oneshot;

import com.goblinbat.eagle.entity.oneshot.*;
import com.goblinbat.eagle.service.oneshot.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * packageName : com.goblinbat.eagle.controller.oneshot
 * fileName : OneshotController
 * author : goodhyoju
 * date : 2022/04/16 1:34 PM
 * description :
 */

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/oneshot")
public class OneshotController {
    @Autowired
    private OneshotNotisService oneshotNotisService;

    @Autowired
    private OneshotQnaService qneshotQnaService;

    @Autowired
    private OneshotReviewService oneshotReviewService;

    @Autowired
    private OneshotFreeService oneshotFreeService;

    @Autowired
    private OneshotEazyService oneshotEazyService;

    @Autowired
    private OneshotReservationService oneshotReservationService;

    @Autowired
    private OneshotCallcountService oneshotCallcountService;
    /**
     *
     * @param response
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadNotisList")
    public String loadNotisList(HttpServletResponse response, HttpServletRequest request) {
        String jsonResponse = oneshotNotisService.getDataTableResponse(request);
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
        return oneshotNotisService.updateNotisCountIncrement(idx);
    }

    /**
     *
     * @param idx
     * @return
     */
    @PostMapping("/deleteNotis")
    public int deleteNotis(@RequestParam Long idx){
        log.debug("idx: {}",idx);
        return oneshotNotisService.delteNotis(idx);
    }

    /**
     *
     * @param eneshotNotisEntity
     * @return
     */
    @PostMapping("saveNotis")
    @ResponseBody
    public OneshotNotisEntity saveNotis(@RequestBody OneshotNotisEntity eneshotNotisEntity) {
        return oneshotNotisService.saveNotis(eneshotNotisEntity);
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
        String jsonResponse = qneshotQnaService.getDataTableResponse(request);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        log.debug(jsonResponse.toString());
        return jsonResponse;
    }

    /**
     *
     * @param oneshotQnaEntity
     * @return
     */
    @PostMapping("updateQna")
    @ResponseBody
    public int updateQna(@RequestBody OneshotQnaEntity oneshotQnaEntity) {
        return qneshotQnaService.updateQna(oneshotQnaEntity);
    }

    /**
     *
     * @param idx
     * @return
     */
    @PostMapping("/deleteQna")
    public int deleteQna(@RequestParam Long idx){
        log.debug("idx: {}",idx);
        return qneshotQnaService.delteQna(idx);
    }

    /**
     *
     * @param oneshotQnaEntity
     * @return
     */
    @PostMapping("saveQna")
    @ResponseBody
    public OneshotQnaEntity saveQna(@RequestBody OneshotQnaEntity oneshotQnaEntity) {
        return qneshotQnaService.saveQna(oneshotQnaEntity);
    }

    /**
     *
     * @param oneshotReviewEntity
     * @return
     */
    @PostMapping("saveReview")
    @ResponseBody
    public OneshotReviewEntity saveReview(@RequestBody OneshotReviewEntity oneshotReviewEntity) {
        return oneshotReviewService.saveQna(oneshotReviewEntity);
    }

    /**
     *
     * @param idx
     * @return
     */
    @PostMapping("/deleteReview")
    public int deleteReview(@RequestParam Long idx){
        log.debug("idx: {}",idx);
        return oneshotReviewService.delteReview(idx);
    }

    /**
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/loadReviewList")
    public String loadReviewList(HttpServletResponse response, HttpServletRequest request) {
        String jsonResponse = oneshotReviewService.getDataTableResponse(request);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        log.debug(jsonResponse);
        return jsonResponse;
    }

    /**
     *
     * @param oneshotFreeEntity
     * @return
     */
    @PostMapping("saveFree")
    @ResponseBody
    public OneshotFreeEntity saveFree(@RequestBody OneshotFreeEntity oneshotFreeEntity) {
        return oneshotFreeService.saveFree(oneshotFreeEntity);
    }

    /**
     *
     * @param oneshotFreeEntity
     * @return
     */
    @PostMapping("/deleteFree")
    @ResponseBody
    public int deleteFree(@RequestBody OneshotFreeEntity oneshotFreeEntity){
        return oneshotFreeService.delteFree(oneshotFreeEntity);
    }

    /**
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/loadFree")
    public String loadFree(HttpServletResponse response, HttpServletRequest request) {
        String jsonResponse = oneshotFreeService.getDataTableResponse();
        log.debug(jsonResponse);
        return jsonResponse;
    }

    /**
     *
     * @param oneshotEazyEntity
     * @return
     */
    @PostMapping("saveEazy")
    @ResponseBody
    public OneshotEazyEntity saveEazy(@RequestBody OneshotEazyEntity oneshotEazyEntity) {
        return oneshotEazyService.saveEazy(oneshotEazyEntity);
    }

    /**
     *
     * @param oneshotEazyEntity
     * @return
     */
    @PostMapping("findEazy")
    @ResponseBody
    public List<OneshotEazyEntity> findEazy(@RequestBody OneshotEazyEntity oneshotEazyEntity) {
        return oneshotEazyService.findEazy(oneshotEazyEntity);
    }

    /**
     *
     * @param oneshotEazyEntity
     * @return
     */
    @PostMapping("/deleteEazy")
    @ResponseBody
    public int deleteEazy(@RequestBody OneshotEazyEntity oneshotEazyEntity){
        return oneshotEazyService.delteEazy(oneshotEazyEntity);
    }

    /**
     *
     * @param oneshotEazyEntity
     * @return
     */
    @PostMapping("/updateEazy")
    @ResponseBody
    public int updateEazy(@RequestBody OneshotEazyEntity oneshotEazyEntity){
        return oneshotEazyService.updateEazy(oneshotEazyEntity);
    }

    /**
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/loadEazyList")
    public String loadEazyList(HttpServletResponse response, HttpServletRequest request) {
        String jsonResponse = oneshotEazyService.getDataTableResponse(request);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        log.debug(jsonResponse);
        return jsonResponse;
    }

    /**
     *
     * @param oneshotReservationEntity
     * @return
     */
    @PostMapping("saveReservation")
    @ResponseBody
    public OneshotReservationEntity saveReservation(@RequestBody OneshotReservationEntity oneshotReservationEntity) {
        return oneshotReservationService.saveReservation(oneshotReservationEntity);
    }

    /**
     *
     * @param oneshotReservationEntity
     * @return
     */
    @PostMapping("findAllByPhoneAndMonthAgo")
    @ResponseBody
    public List<OneshotReservationEntity> findAllByPhoneAndMonthAgo(@RequestBody OneshotReservationEntity oneshotReservationEntity) {
        return oneshotReservationService.findAllByPhoneAndMonthAgo(oneshotReservationEntity);
    }

    /**
     *
     * @param oneshotReservationEntity
     * @return
     */
    @PostMapping("findReservationByNamePassword")
    @ResponseBody
    public List<OneshotReservationEntity> findReservationByNamePassword(@RequestBody OneshotReservationEntity oneshotReservationEntity) {
        return oneshotReservationService.findReservationByNamePassword(oneshotReservationEntity);
    }
    /**
     *
     * @param oneshotReservationEntity
     * @return
     */
    @PostMapping("/deleteReservation")
    @ResponseBody
    public int deleteReservation(@RequestBody OneshotReservationEntity oneshotReservationEntity){
        return oneshotReservationService.deleteReservation(oneshotReservationEntity);
    }

    /**
     *
     * @param oneshotReservationEntity
     * @return
     */
    @PostMapping("/updateReservation")
    @ResponseBody
    public int updateReservation(@RequestBody OneshotReservationEntity oneshotReservationEntity){
        return oneshotReservationService.updateReservation(oneshotReservationEntity);
    }

    /**
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/loadReservationList")
    public String loadReservationList(HttpServletResponse response, HttpServletRequest request) {
        String jsonResponse = oneshotReservationService.getDataTableResponse(request);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        log.debug(jsonResponse);
        return jsonResponse;
    }

    /**
     *
     * @param oneshotCallcountEntity
     * @return
     */
    @PostMapping("updateCallcount")
    @ResponseBody
    public int saveNotis(@RequestBody OneshotCallcountEntity oneshotCallcountEntity) {
        return oneshotCallcountService.updateCallcount(oneshotCallcountEntity);
    }
}

