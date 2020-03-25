package com.didispace.common.util;

import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class APIEncryption {

    public static String encode(String ak,String param,String timestamp,String sk){
        String result = null;
        String str = ak + param + timestamp + sk;
        try {
            result = DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String encodeParam(String param, String sKey){
        String result = null;
        Base64.Encoder encoder = Base64.getEncoder();
        try {
            byte[] encrypt = encrypt(param, sKey);
            result = encoder.encodeToString(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return encrypted;
    }

    public static String decodeParam(String param, String sKey){
        String result = null;
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodeByte = decoder.decode(param);
        try {
            result = decrypt(decodeByte, sKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String decrypt(byte[] sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            try {
                byte[] original = cipher.doFinal(sSrc);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

}
