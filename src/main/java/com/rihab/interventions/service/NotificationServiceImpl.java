package com.rihab.interventions.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rihab.interventions.entities.Notification;
import com.rihab.interventions.entities.User;
import com.rihab.interventions.repos.NotificationRepository;
import com.rihab.interventions.repos.UserRepository;

@Service
public class NotificationServiceImpl implements NotificationService{
	@Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;
    
    
    
    
    @Override
    public Notification createNotification(String message, Long idUser) {
    	 User user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
         Notification notification = new Notification();
         notification.setUser(user);
         notification.setMessage(message);
         notification.setCreatedAt(new Date());
         return notificationRepository.save(notification);
     }

	@Override
	public List<Notification> getNotificationsForUser(Optional<User> user) {
		  return notificationRepository.findByUserAndSeenFalse(user);
	}

	@Override
	public Notification save(Notification notification) {
		 return notificationRepository.save(notification);
		
	}

	@Override
	public Notification findById(Long id) {
		return notificationRepository.findById(id).get();
	}

	@Override
	public void sendNotification(Long userId, String message) {
		// TODO Auto-generated method stub
		
	}

	

	
}