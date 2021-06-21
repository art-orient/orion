package com.art.orion.controller.command.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {
    static Logger logger = LogManager.getLogger();
    private static final String ALGORITHM = "SHA-256";
    private static final int POSITIVE_SIGN = 1;
    private static final int HEX_RADIX = 16;

    private PasswordEncryptor() {
    }

    public static String encryptPassword(String password) {
        byte[] bytesEncoded = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            bytesEncoded = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        }
        BigInteger bigInt = new BigInteger(POSITIVE_SIGN, bytesEncoded);
        return bigInt.toString(HEX_RADIX);
    }
}

