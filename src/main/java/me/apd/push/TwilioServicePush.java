package me.apd.push;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@ConditionalOnExpression("'${notificacion}'=='twilio'")
public class TwilioServicePush implements PushNotificacionService {

    private final String account;
    private final String token;
    private final String from;

    public TwilioServicePush(@Value("${twilio.account}") String account, @Value("${twilio.key}") String token, @Value("${twilio.from}") String from) {
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

        log.info("enviando un mensaje");
        Message message = Message.creator(new PhoneNumber(to),
                new PhoneNumber(from),
                body).create();
        log.info("Resultado del envio de sms {}", message.getErrorMessage());
    }
}
