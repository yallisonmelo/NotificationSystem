package br.com.yfsmsystem.notificationsystem.repository;

import br.com.yfsmsystem.notificationsystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
