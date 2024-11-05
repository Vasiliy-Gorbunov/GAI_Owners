package com.gai_app.gai_owners.service.kafka;

import com.gai_app.gai_owners.model.OwnerModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void getModelCreateMessageAndSend(OwnerModel ownerModel, String cause) {
        String message = "Owner with id " + ownerModel.getId() + " has been " + cause + "\n"
                + "Owner: " + ownerModel;
        sendNotification(message);
    }

    public void sendNotification(String message) {
        kafkaTemplate.send("notification-topic", message);
    }
}