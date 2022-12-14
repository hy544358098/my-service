package com.x.common.config;

import com.x.business.independent.model.entity.TDictionariesDetailed;
import com.x.common.enums.SupervisionStatusEnum;
import com.x.common.utils.mail.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/11/4 16:20
 * @Version 1.0
 */
@Async
@Component
@Slf4j
public class AsyncConfig {

    @Autowired private EmailService emailService;

    private static final String TOPIC = "";
    private static final String BODY = "";
    private static final String BODYMODEL = "";
    private static final String BODYREMIND = "";
    private static final String BODYREMINDMODEL = "";

    public void sendEmail(TDictionariesDetailed tDictionariesDetailed,List<String> emailList, String type){
        // 线程等待500毫秒，邮箱服务器性能较差，防止并发发邮件
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        if (StringUtils.equals(SupervisionStatusEnum.STATUS_TRANSFER.getCode(), type)) {
            emailService.sendEmail(
                    emailList,
                    null,
                    TOPIC,
                    BODY + tDictionariesDetailed.getTypeName() + BODYMODEL);
        } else {
            emailService.sendEmail(
                    emailList,
                    null,
                    TOPIC,
                    BODYREMIND + tDictionariesDetailed.getTypeName() + BODYREMINDMODEL);
        }
    }
}
