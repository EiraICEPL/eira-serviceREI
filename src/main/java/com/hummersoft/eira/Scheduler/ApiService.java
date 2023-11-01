package com.hummersoft.eira.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private final JavaMailSender javaMailSender;

    @Autowired
    public ApiService(RestTemplate restTemplate, JavaMailSender javaMailSender) {
        this.restTemplate = restTemplate;
        this.javaMailSender = javaMailSender;
    }

   // @Scheduled(cron = "0 */30 * * * *") 
    public void sendHttpRequestAndEmail() {
        String apiUrl = "http://localhost:8081/eira-analyticsService/sendMail";

        // Create the request body as a JSON object
        String requestBody = "{\n" +
                "  \"recipient\": \"sujithajothi118@gmail.com\",\n" +
                "  \"subject\": \"Mail sent  \",\n" +
                "  \"msgBody\": \"Testing Successfully Executed\"\n" +
                "}";

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity with request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the POST request
        String response = restTemplate.postForObject(apiUrl, requestEntity, String.class);

        // Handle the response if needed
        System.out.println("HTTP Request sent to: " + apiUrl);
        System.out.println("Response: " + response);

        // Send email to multiple recipients
        String[] recipients = {
                "sujithajothi118@gmail.com",
                "support@eira.io",
                "malini@eira.io"
        };

        for (String recipient : recipients) {
            sendEmail(recipient, "Group mail", "Scheduler Successfully Executed");
        }
    }

    private void sendEmail(String recipient, String subject, String msgBody) {
        // Create a SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sujithajothi118@gmail.com"); // Set your email address
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(msgBody);

        // Send the email
        javaMailSender.send(message);
    }
}
