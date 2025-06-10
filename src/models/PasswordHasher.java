package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

	    public static String hashPassword(String password) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            byte[] hashedBytes = md.digest(password.getBytes());
	            return bytesToHex(hashedBytes);
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException("No existe el algoritmo de hash SHA-256", e);
	        }
	    }

	    private static String bytesToHex(byte[] bytes) {
	        StringBuilder hexString = new StringBuilder();
	        for (byte b : bytes) {
	            String hex = Integer.toHexString(0xff & b);
	            if (hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    }
	
}
