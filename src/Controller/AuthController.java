package Controller;

import Controller.DAOS.DAOAuth;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthController {

    private DAOAuth dao;

    public AuthController(){
        dao = new DAOAuth();
    }

    public String auth(String user, String pass) {
        return dao.auth(user, hash(pass));
    }

    private static String encodeHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte aDigest : digest) {
            sb.append(Integer.toString((aDigest & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String hash(String toHash){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] binaryToHash = toHash.getBytes("UTF-8");
            digest.update(binaryToHash);
            byte[] hash = digest.digest();
            return encodeHex(hash);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return null;
        }
    }
}
