package com.java.project.Services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * `Library` class for hash functions.
 */
public class Hashing {

    /**
     * Implements the SHA256 hash function.
     *
     * @param base the string for which the hash code is determined.
     * @return a <code>String</code> representing the determined hash code.
     */
    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
