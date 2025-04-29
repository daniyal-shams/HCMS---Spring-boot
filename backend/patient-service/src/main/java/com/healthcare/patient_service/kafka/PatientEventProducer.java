package com.healthcare.patient_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class PatientEventProducer {

    private static final String TOPIC = "patient-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendPatientCreatedEvent(Long patientId, String firstName, String lastName,String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "patient-created");
        payload.put("timestamp", Instant.now().toString());
        payload.put("patientId", patientId);
        payload.put("full name", firstName + " " + lastName);
        payload.put("email", email);

        try {
            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(TOPIC, message);
            System.out.println("📤 Patient Created Event Sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Failed to serialize patient event: " + e.getMessage());
        }
    }

    public void sendPatientUpdatedEvent(Long patientId, String firstName, String lastName, String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "patient-updated");
        payload.put("timestamp", Instant.now().toString());
        payload.put("patientId", patientId);
        payload.put("full name", firstName + " " + lastName);
        payload.put("email", email);

        try {
            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(TOPIC, message);
            System.out.println("📤 Patient Updated Event Sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Failed to serialize patient update event: " + e.getMessage());
        }
    }
}
