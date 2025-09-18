package com.ldb.truck.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class TokenGenerator {

    public static String generateToken(String username) {
        try {
            // เอา username + random UUID มาทำให้ unique
            String input = username + UUID.randomUUID().toString();

            // hash ด้วย SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());

            // แปลง byte[] -> Hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString(); // ได้ token ยาว 64 ตัว
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
