package com.healthcare.notification_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.notification_service.dto.NotificationDto;

@Service
public class NotificationEvent {

    @Autowired
    private JavaMailSender mailSender;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "notification-events", groupId = "notification-group")
    public void consume(String messageJson) {
        try {
            NotificationDto event = objectMapper.readValue(messageJson, NotificationDto.class);

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(event.getRecipient());
            mail.setSubject(event.getSubject());
            mail.setText(event.getMessage());
            mailSender.send(mail);

            System.out.println("📧 Email sent to: " + event.getRecipient());
        } catch (Exception e) {
            System.err.println("❌ Failed to process notification: " + e.getMessage());
        }
    }
}
