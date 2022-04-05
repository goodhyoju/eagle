package com.goblinbat.eagle.controller;

import com.goblinbat.eagle.data.CleanManiaTalkData;
import com.goblinbat.eagle.entity.TalkConfigEntity;
import com.goblinbat.eagle.entity.TalkListEntity;
import com.goblinbat.eagle.repo.TalkConfigRepository;
import com.goblinbat.eagle.service.CleanManiaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * packageName : com.goblinbat.eagle.controller
 * fileName : TestController
 * author : goodhyoju
 * date : 2022/03/21 10:26 PM
 * description :
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/cleanmania")
public class CleanManiaController {

    @Autowired
    private CleanManiaService cleanManiaService;
    /**
     * @return
     */
    @GetMapping("talkconfig")
    public TalkConfigEntity loadingTalkConfig() {
        return cleanManiaService.loadingTalkConfig();
    }

    /**
     *
     * @return
     */
    @GetMapping("talkSelectconfig")
    public TalkConfigEntity talkSelectconfig() {
        return cleanManiaService.talkSelectconfig();
    }

    /**
     *
     * @param talkConfig
     * @return
     */
    @PostMapping("talkSelectListCheck")
    @ResponseBody
    public TalkListEntity talkSelectListCheck(@RequestBody CleanManiaTalkData talkConfig) {
        return cleanManiaService.talkSelectListCheck(talkConfig.getName(),talkConfig.getPhone());
    }

    /**
     * @param talkConfig
     * @return
     */
    @PostMapping("/talkConfigSave")
    @ResponseBody
    @Transactional
    public TalkConfigEntity talkConfigSave(@RequestBody TalkConfigEntity talkConfig) {
        return cleanManiaService.talkConfigSave(talkConfig);
    }

    /**
     *
     * @param talkConfig
     * @return
     */
    @PostMapping("/sendMoveOutTalk")
    @ResponseBody
    public int cleanManiaSendTalk(@RequestBody CleanManiaTalkData talkConfig) {
        return cleanManiaService.sendMoveOutTalk(talkConfig);
    }
}