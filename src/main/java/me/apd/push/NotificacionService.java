package me.apd.push;

public interface NotificacionService {
    void send(String to, String subject, String body);
}
