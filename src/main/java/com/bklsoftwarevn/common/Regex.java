package com.bklsoftwarevn.common;

import java.util.regex.Pattern;

public class Regex {

    public static final String EMAIL = "^(.+)@(.+)$";
    public static final String PASSWORD = "^([A-Za-z0-9!@#$%^&*()\\-_=+{};:,<.>]{8,40})$";
    public static final String PHONE_NUMBER = "^([0-9]{10})$";

    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL);
        return pattern.matcher(email).matches();
    }

    public static boolean checkPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD);
        return pattern.matcher(password).matches();
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER);
        return pattern.matcher(phoneNumber).matches();
    }
}
