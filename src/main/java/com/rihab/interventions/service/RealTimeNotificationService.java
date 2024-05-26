package com.rihab.interventions.service;


import com.rihab.interventions.entities.Notification;
import com.rihab.interventions.entities.User;
import com.rihab.interventions.repos.NotificationRepository;
import com.rihab.interventions.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RealTimeNotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendNotification(Long userId, String message) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Notification notification = Notification.builder()
                .message(message)
                .createdAt(new Date())
                .seen(false)
                .user(user)
                .build();
            notificationRepository.save(notification);

            // Envoie la notification via WebSocket pour l'intégration avec le frontend
            messagingTemplate.convertAndSendToUser(
                user.getUsername(),
                "/queue/notifications",
                notification.getMessage()
            );
        }
    }
}
