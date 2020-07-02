package me.apd.push;

import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@ConditionalOnExpression("'${notificacion}'=='log'")
public class LogServicePush implements PushNotificacionService {

    private final String account;
    private final String token;
    private final String from;

    public LogServicePush(@Value("${twilio.account}") String account, @Value("${twilio.key}") String token, @Value("${twilio.from}") String from) {
        this.account = account;
        this.token = token;
        this.from = from;
    }

    @PostConstruct
    public void init() {
        Twilio.init(account, token);
    }

    @Override
    public void send(String to, String subject, String body) {
        log.info("enviando un mensaje to {} con texto {}", to, body);
    }
}
