package br.com.yfsmsystem.notificationsystem.exception;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException() {
        super("Notification not Found");
    }
}
