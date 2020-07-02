package me.apd.push;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

@Service
@ConditionalOnExpression("'${notificacion}'=='email'")
public class EmailServicePush implements PushNotificacionService {
    private static final String API_URL = "https://api:{API_KEY}@api.mailgun.net/v2/{domain}/messages";
    private final String apiKey;
    private final String domain;
    private final String from;
    private final String server;
    private final RestTemplate restTemplate;

    public EmailServicePush(@Value("mail.apiKey") String apiKey, @Value("mail.domain") String domain, @Value("mail.from") String from, @Value("mail.server") String server, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.domain = domain;
        this.from = from;
        this.server = server;
        this.restTemplate = restTemplate;
    }

    @Override
    public void send(String to, String subject, String body) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance().host(server).path(API_URL)
                .fragment(apiKey).fragment(domain).build();
        URI myUri = uriComponents.toUri();

        var request = new HashMap<String, String>();

        request.put("from", from);
        request.put("subject", subject);
        request.put("text", body);
        request.put("to", to);

        ResponseEntity<String> response = restTemplate.postForEntity(myUri, request, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return;
        }
        throw new MailException(response.getStatusCode().toString());
    }
}


//API_KEY = ENV['MAILGUN_API_KEY']
