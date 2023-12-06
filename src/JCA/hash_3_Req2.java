import java.security.MessageDigest;
import java.util.Arrays;

public class hash_3_Req2 {

    public static void main(String[] args) {
        //The input message
        String message = "Hello, World!";

        try {
            //Create a MessageDigest instance for SHA-256
            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

            //Feed the message into the digest
            byte[] hash = sha256Digest.digest(message.getBytes("UTF-8"));
            
            //Print the hashed message
            System.out.println("Hashed message: " + bytesToHex(hash));

        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Function to convert byte array to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}