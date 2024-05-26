package com.rihab.interventions.restControllers;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rihab.interventions.entities.Notification;
import com.rihab.interventions.entities.User;
import com.rihab.interventions.repos.UserRepository;
import com.rihab.interventions.service.NotificationService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class NotificationRestController {

	@Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userService;
    
    @GetMapping("/notifications")
    public List<Notification> getNotifications(Authentication authentication) {
        Optional<User> user = userService.findByUsername(authentication.getName());
        return notificationService.getNotificationsForUser(user);
    }

    @PostMapping("/notificationsReaded/{id}/markAsRead")
    public void markNotificationAsRead(@PathVariable Long id) {
        Notification notification = notificationService.findById(id);
        if (notification != null) {
            notification.setSeen(true);
            notificationService.save(notification);
        }
    }
    /*
    @MessageMapping("/notify")
    @SendTo("/queue/notifications")
    public void sendNotification(NotificationMessage message) {
        realTimeNotificationService.sendNotification(message.getUserId(), message.getMessage());
    }
    */
    @PostMapping("/send")
    public void sendNotification(@RequestParam Long userId, @RequestParam String message) {
        notificationService.sendNotification(userId, message);
    }
    
    
}
