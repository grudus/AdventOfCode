import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {
    final private static char[] hexArray = "0123456789abcdef".toCharArray();
    private static String bytesToHex(byte[] bytes) {

        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public String generateM5(String s) {
        MessageDigest md = null;
        byte[] messageBytes = null;
            messageBytes = s.getBytes();
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] end = md.digest(messageBytes);

        return bytesToHex(end);
    }

    public int wynik1_2(String s, int numberOfZeros) {
        int wynik = 0;
        MessageDigest md = null;
        String newMsg = "";
        String zeros = "";
        for (int i = 0; i < numberOfZeros; i++) zeros += "0";
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        while (++wynik < Integer.MAX_VALUE/2) {
            newMsg = s + String.valueOf(wynik);
            if (bytesToHex(md.digest(newMsg.getBytes())).startsWith(zeros)) break;
        }
        System.out.println(newMsg);
        return wynik;
    }


}
