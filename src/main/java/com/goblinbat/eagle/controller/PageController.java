package com.goblinbat.eagle.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * packageName : com.goblinbat.eagle.controller
 * fileName : DataController
 * author : Jang Hyo Ju
 * date : 2022/02/25
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022/02/25 3:31 PM Jang Hyo Ju 최초 생성
 */
@Slf4j
@CrossOrigin(origins = "*")
@Controller

public class PageController {

    /**
     * page load to index page
     * @return
     */
    @RequestMapping(value = { "/", "/index" }, method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    /**
     * login to db user info
     * @return
     */
    @RequestMapping(value = "/login" , method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("login");
        return view;
    }
}
