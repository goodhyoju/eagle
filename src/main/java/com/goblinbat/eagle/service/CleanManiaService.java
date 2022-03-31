package com.goblinbat.eagle.service;

import com.goblinbat.eagle.data.RecevierData;
import com.goblinbat.eagle.data.CleanManiaTalkData;
import com.goblinbat.eagle.entity.TalkConfigEntity;
import com.goblinbat.eagle.entity.TalkListEntity;
import com.goblinbat.eagle.repo.TalkConfigRepository;
import com.goblinbat.eagle.repo.TalkListRepository;
import com.goblinbat.eagle.utils.KakaoTalkUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

/**
 * packageName : com.goblinbat.eagle.service
 * fileName : TalkService
 * author : goodhyoju
 * date : 2022/03/24 9:21 AM
 * description :
 */

@Slf4j
@Service
public class CleanManiaService {

    @Autowired
    private TalkConfigRepository talkConfigRepository;

    @Autowired
    private TalkListRepository talkListRepository;

    @Autowired
    private KakaoTalkUtils kakaoTalkUtils;

    /**
     *
     * @param cleanManiaTalkData
     * @return
     */
    @Transactional
    public int sendMoveOutTalk(CleanManiaTalkData cleanManiaTalkData){
        int result =0;

        // get cleanmania talk config
        TalkConfigEntity talkConfig = talkConfigRepository.findTalkConfigEntitiesByUsed(1);

        ArrayList<RecevierData> recevierDataArrayList = new ArrayList<>();

        String getType = talkConfig.getMtype();

        // 한사람에게 보내기
        if (getType.equals("sigle")) {
            recevierDataArrayList.add(RecevierData.builder().name(talkConfig.getNames()).phone(talkConfig.getPhones()).build());
        }
        // 2명 이상에게 보내기
        else if(getType.equals("all")){
            String[] phoneArr = talkConfig.getPhones().split(",");
            String[] nameArr = talkConfig.getNames().split(",");
            for(int i=0; i<phoneArr.length; i++){
                try {
                    recevierDataArrayList.add(RecevierData.builder().name(nameArr[i]).phone(phoneArr[i]).build());
                }catch (Exception e){
                    recevierDataArrayList.add(RecevierData.builder().phone(phoneArr[i]).build());
                }

            }
        }
        // 랜덤 보내기
        else{
            int randomNum = talkConfig.getRdcount();
            Random rd = new Random();

            String[] phoneArr = talkConfig.getPhones().split(",");
            String[] nameArr = talkConfig.getNames().split(",");

            if(randomNum > phoneArr.length){
                randomNum = phoneArr.length;
            }

            for(int i=0; i<randomNum; i++){
                int rdNum = rd.nextInt(phoneArr.length);
                try {
                    recevierDataArrayList.add(RecevierData.builder().name(nameArr[rdNum]).phone(phoneArr[rdNum]).build());
                }catch (Exception e){
                    recevierDataArrayList.add(RecevierData.builder().phone(phoneArr[rdNum]).build());
                }
            }
        }


        if(recevierDataArrayList.size() > 0){
            ArrayList<TalkListEntity> talkListEntityArrayList = new ArrayList<>();

            for(RecevierData recevierData : recevierDataArrayList){
                CleanManiaTalkData sendTemp = cleanManiaTalkData;

                try {
                    // talk 보내기
                    sendTemp.setReceivernum(recevierData.getPhone().replaceAll("-", ""));
                    sendTemp.setSendernum(cleanManiaTalkData.getSendernum().replaceAll("-", ""));

                    sendTemp.setTemplate(talkConfig.getTemplate());
                    kakaoTalkUtils.sendOneAta(sendTemp);
                }catch (Exception e){
                    log.error(e.getMessage());
                }
                // talk list 저장
                StringBuffer temp = new StringBuffer();
                temp.append(talkConfig.getTemplate()).append("\n\n");
                temp.append("신청자명: ").append(cleanManiaTalkData.getName()).append("\n");
                temp.append("연락처: ").append(cleanManiaTalkData.getPhone()).append("\n");
                temp.append("이사날짜: ").append(cleanManiaTalkData.getMovedate()).append("\n");
                temp.append("출발지: ").append(cleanManiaTalkData.getStart()).append("\n");
                temp.append("도착지: ").append(cleanManiaTalkData.getEnd());


                talkListEntityArrayList.add(
                    TalkListEntity.builder()
                            .sender_name(sendTemp.getSender())
                            .receiver_name(recevierData.getName())
                            .receiver_phone(recevierData.getPhone())
                            .message(temp.toString())
                            .commission(talkConfig.getCommission())
                            .price(talkConfig.getPrice())
                            .build()
                );
            }
            talkListRepository.saveAll(talkListEntityArrayList);
        }
        return result;
    }

}
