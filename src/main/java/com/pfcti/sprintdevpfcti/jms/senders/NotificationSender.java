package com.pfcti.sprintdevpfcti.jms.senders;

import com.pfcti.sprintdevpfcti.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class NotificationSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMail(NotificationDto notificationDto) {
        jmsTemplate.convertAndSend("smsReceiverJms", notificationDto);
    }

}
