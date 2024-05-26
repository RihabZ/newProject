package com.rihab.interventions.service;

import java.util.List;
import java.util.Optional;

import com.rihab.interventions.entities.Notification;
import com.rihab.interventions.entities.User;

public interface NotificationService {

	//void sendNotification(Long userId, String message) ;
	//Notification createNotification(String message, Long userId) ;

	List<Notification> getNotificationsForUser(Optional<User> user);

	Notification save(Notification notification);

	Notification findById(Long id);



	void sendNotification(Long userId, String message);

	Notification createNotification(String message, Long idUser);

	
}
