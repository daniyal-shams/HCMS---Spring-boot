package com.healthcare.appointment_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class AppointmentEventProducer {

    private static final String TOPIC = "appointment-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendNurseCreatedEvent(Long appointmentId, String name , String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "appointment-created");
        payload.put("timestamp", Instant.now().toString());
        payload.put("appointmentId", appointmentId);
        payload.put("name", name);
        payload.put("email", email);

        try {
            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(TOPIC, message);
            System.out.println("📤 Appointment Created Event Sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Failed to serialize Appointment event: " + e.getMessage());
        }
    }

    public void sendNurseUpdatedEvent(Long appointmentId, String name , String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "appointment-updated");
        payload.put("timestamp", Instant.now().toString());
        payload.put("appointmentId", appointmentId);
        payload.put("name", name);
        payload.put("email", email);

        try {
            String message = objectMapper.writeValueAsString(payload);
            kafkaTemplate.send(TOPIC, message);
            System.out.println("📤 Appointment Updated Event Sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Failed to serialize Appointment update event: " + e.getMessage());
        }
    }
}
