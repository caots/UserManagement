package com.bklsoftwarevn.entities.user;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserMail {
    private String emailAddress;
    private String title;
    private String content;
}
