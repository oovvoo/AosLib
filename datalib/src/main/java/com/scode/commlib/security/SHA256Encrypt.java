package com.scode.commlib.security;

import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Encrypt {
    public static String encrypt(String plainText) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            Log.d("SHA256Enc", "init failed");
        }
        md.update(plainText.getBytes(StandardCharsets.UTF_8));
        byte[] digest =  md.digest();
        StringBuilder sb = new StringBuilder();
        for(byte b : digest)
            sb.append(String.format("%02x",b));

        return sb.toString();

    }
}
