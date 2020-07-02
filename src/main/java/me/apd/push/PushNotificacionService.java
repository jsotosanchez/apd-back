package me.apd.push;

public interface PushNotificacionService {
    void send(String to, String subject, String body);
}
