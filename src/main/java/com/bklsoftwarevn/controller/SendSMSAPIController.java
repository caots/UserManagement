package com.bklsoftwarevn.controller;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.twilio.exception.TwilioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;


@RestController
@RequestMapping("api/v1/public/sms")
public class SendSMSAPIController {

    @GetMapping
    public ResponseEntity<Object> sendSms(@RequestParam("phone-number") String phoneNumber) throws TwilioException, IOException, NexmoClientException {
        NexmoClient client = new NexmoClient.Builder()
                .apiKey("7300b579")
                .apiSecret("9KmzeAm0XKGF0LzC")
                .build();

        String code = authenticationCode();

        String messageText = "From Bksoftwarevn  \n Your confirmation code is: " + code;
        TextMessage message = new TextMessage(
                "Nexmo",
                phoneNumber,
                messageText);

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        StringBuilder result = new StringBuilder();
        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
            result.append(responseMessage);
            System.out.println(response);
        }
        return new ResponseEntity<>(code, HttpStatus.OK);
    }

    private String authenticationCode() {
        Random random = new Random();
        int numberOne = random.nextInt(10);
        int numberTwo = random.nextInt(10);
        int numberThree = random.nextInt(10);
        int numberFour = random.nextInt(10);
        return numberOne + "" + numberTwo + "" + numberThree + "" + numberFour;

    }


}
