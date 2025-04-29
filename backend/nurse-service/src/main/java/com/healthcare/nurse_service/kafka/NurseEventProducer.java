package com.healthcare.nurse_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class NurseEventProducer {

    private static final String TOPIC = "nurse-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendNurseCreatedEvent(Long nurseId, String name , String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "nurse-created");
        payload.put("timestamp", Instant.now().toString());
        payload.put("nursetId", nurseId);
        payload.put("name", name);
        payload.put("email", email);

        try {
            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(TOPIC, message);
            System.out.println("📤 Nurse Created Event Sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Failed to serialize nurse event: " + e.getMessage());
        }
    }

    public void sendNurseUpdatedEvent(Long nurseId, String name , String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "nurse-updated");
        payload.put("timestamp", Instant.now().toString());
        payload.put("nurseId", nurseId);
        payload.put("name", name);
        payload.put("email", email);

        try {
            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(TOPIC, message);
            System.out.println("📤 Nurse Updated Event Sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Failed to serialize nurse update event: " + e.getMessage());
        }
    }
}
