package com.dbr.rent.Utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IDGenerator {
    private static final Random RANDOM = new Random();

    public static String generateRandomId(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < length; i++) {
            id.append(characters.charAt(RANDOM.nextInt(characters.length())));
        }
        return id.toString();
    }
}
