package de.alex.money_server;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class HWID {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static byte[] generateHWID() {
        try {
            MessageDigest hash = MessageDigest.getInstance("MD5");

            String s = System.getProperty("os.name") +
//                    System.getProperty("os.arch") +
//                    System.getProperty("os.version") +
//                    Runtime.getRuntime().availableProcessors() +
                    System.getenv("PROCESSOR_IDENTIFIER") +
                    System.getenv("PROCESSOR_ARCHITECTURE") +
                    System.getenv("NUMBER_OF_PROCESSORS");
            System.out.println(s);
            return hash.digest(s.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new Error("Algorithm wasn't found.", e);
        }
    }
    public static byte[] generateHWID_super() {
        try {
            MessageDigest hash = MessageDigest.getInstance("MD5");
            String s = "";
            for (Map.Entry<String, String> s_2:System.getenv().entrySet()) {
                if(s_2.getKey().equals("Path"))continue;
                s+=s_2;
//                System.out.println(s_2);
//
            }

//            System.out.println(s);
            return hash.digest(s.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new Error("Algorithm wasn't found.", e);
        }
    }


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
