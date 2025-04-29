package com.healthcare.doctor_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class DoctorEventProducer {

    private static final String TOPIC = "doctor-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendDoctorCreatedEvent(Long doctorId, String name, String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "doctor-created");
        payload.put("timestamp", Instant.now().toString());
        payload.put("doctorId", doctorId); 
        payload.put("name", name);
        payload.put("email", email);

        try {
            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(TOPIC, message);
            System.out.println("📤 Doctor Created Event Sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Failed to serialize doctor event: " + e.getMessage());
        }
    }

    public void sendDoctorUpdatedEvent(Long doctorId, String name, String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "doctor-updated");
        payload.put("timestamp", Instant.now().toString());
        payload.put("doctorId", doctorId);
        payload.put("name", name);
        payload.put("email", email);

        try {
            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(TOPIC, message);
            System.out.println("📤 Doctor Updated Event Sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Failed to serialize doctor update event: " + e.getMessage());
        }
    }
}
