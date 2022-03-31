package com.goblinbat.eagle.controller;

import com.goblinbat.eagle.data.CleanManiaTalkData;
import com.goblinbat.eagle.entity.TalkConfigEntity;
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
@RequestMapping("/cleanmania")
public class CleanManiaController {

    @Autowired
    private TalkConfigRepository talkConfigRepository;

    @Autowired
    private CleanManiaService cleanManiaService;
    /**
     * @return
     */
    @GetMapping("talkconfig")
    public TalkConfigEntity loadingTalkConfig() {
        TalkConfigEntity talkConfig = talkConfigRepository.findTalkConfigEntitiesByUsed(1);
        return talkConfig;
    }

    /**
     * @param talkConfig
     * @return
     */
    @PostMapping("/talkConfigSave")
    @ResponseBody
    @Transactional
    public TalkConfigEntity talkConfigSave(@RequestBody TalkConfigEntity talkConfig) {
        TalkConfigEntity result = null;
        try {
            result = talkConfigRepository.saveAndFlush(talkConfig);
            talkConfigRepository.setUsed(1,0,result.getIdx());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     *
     * @param talkConfig
     * @return
     */
    @PostMapping("/sendMoveOutTalk")
    @ResponseBody
    public int cleanManiaSendTalk(@RequestBody CleanManiaTalkData talkConfig) {
        int result = cleanManiaService.sendMoveOutTalk(talkConfig);
        return result;
    }
}