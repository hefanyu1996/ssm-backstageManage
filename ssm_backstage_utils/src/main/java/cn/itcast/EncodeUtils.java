package cn.itcast;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class EncodeUtils {

    //BCryptPasswordEncoder加密
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    //MD5加密

    public static String getBCryptPasswordEncode(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static String getMD5PasswordEncode(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        String pswd = "hefanyu";

        String encodePassword = getBCryptPasswordEncode(pswd);
        System.out.println(encodePassword);

    }
}
