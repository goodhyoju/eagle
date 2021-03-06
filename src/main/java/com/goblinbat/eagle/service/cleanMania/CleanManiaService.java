package com.goblinbat.eagle.service.cleanMania;

import com.goblinbat.eagle.data.cleanMania.RecevierData;
import com.goblinbat.eagle.data.cleanMania.CleanManiaTalkData;
import com.goblinbat.eagle.entity.cleanMania.TalkConfigEntity;
import com.goblinbat.eagle.entity.cleanMania.TalkListEntity;
import com.goblinbat.eagle.repo.cleanMania.TalkConfigRepository;
import com.goblinbat.eagle.repo.cleanMania.TalkListRepository;
import com.goblinbat.eagle.utils.KakaoTalkUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

/**
 * packageName : com.goblinbat.eagle.service.cleanMania
 * fileName : CleanManiaService
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
     * @return
     */
    public TalkConfigEntity loadingTalkConfig() {
        TalkConfigEntity talkConfig = talkConfigRepository.findTalkConfigEntitiesByUsed(1);
        return talkConfig;
    }

    /**
     *
     * @return
     */
    public TalkConfigEntity talkSelectconfig(){
        TalkConfigEntity talkConfig=null;
        try {
            talkConfig = talkConfigRepository.findTalkConfigEntitiesByUsedAndMtype(1,"select");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return talkConfig;
    }

    /**
     *
     * @param customerName
     * @param customerPhone
     * @return
     */
    public TalkListEntity talkSelectListCheck(String customerName,String customerPhone){
        TalkListEntity talkListEntity=null;

        try {
            talkListEntity = talkListRepository.findTalkListByCustomer_nameAndCustomer_phone(customerName,customerPhone);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return talkListEntity;
    }

    /**
     *
     * @param talkConfig
     * @return
     */
    @Transactional
    public TalkConfigEntity talkConfigSave( TalkConfigEntity talkConfig) {
        TalkConfigEntity result = null;
        try {
            int getCommition = talkConfig.getCommission();
            if(getCommition < 1){
                talkConfig.setCommission(4000);
            }
            result = talkConfigRepository.saveAndFlush(talkConfig);
            talkConfigRepository.setUsed(1,0,result.getIdx());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return result;
    }

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

        // ??????????????? config?????? ??????
        if (cleanManiaTalkData.getType().equals("select")) {
            recevierDataArrayList.add(RecevierData.builder().name(cleanManiaTalkData.getSelectName()).phone(cleanManiaTalkData.getSelectPhone()).build());
        }
        // ??????????????? ?????????
        else if (getType.equals("sigle")) {
            recevierDataArrayList.add(RecevierData.builder().name(talkConfig.getNames()).phone(talkConfig.getPhones()).build());
        }
        // 2??? ???????????? ?????????
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
        // ?????? ?????????
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
                    // talk ?????????
                    sendTemp.setReceivernum(recevierData.getPhone().replaceAll("-", ""));
                    sendTemp.setSendernum(cleanManiaTalkData.getSendernum().replaceAll("-", ""));

                    sendTemp.setTemplate(talkConfig.getTemplate());
                    kakaoTalkUtils.sendOneAta(sendTemp);
                }catch (Exception e){
                    log.error(e.getMessage());
                }
                // talk list ??????
                StringBuffer temp = new StringBuffer();
                temp.append(talkConfig.getTemplate()).append("\n\n");
                temp.append("????????????: ").append(cleanManiaTalkData.getName()).append("\n");
                temp.append("?????????: ").append(cleanManiaTalkData.getPhone()).append("\n");
                temp.append("????????????: ").append(cleanManiaTalkData.getMovedate()).append("\n");
                temp.append("?????????: ").append(cleanManiaTalkData.getStart()).append("\n");
                temp.append("?????????: ").append(cleanManiaTalkData.getEnd());


                talkListEntityArrayList.add(
                    TalkListEntity.builder()
                            .sender_name(sendTemp.getSender())
                            .receiver_name(recevierData.getName())
                            .receiver_phone(recevierData.getPhone())
                            .customer_name(cleanManiaTalkData.getName())
                            .customer_phone(cleanManiaTalkData.getPhone())
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
