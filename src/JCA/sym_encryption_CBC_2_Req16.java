import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_CBC_2_Req16 {

    private static final String KEY = "aesEncryptionKey";
    private static final String INIT_VECTOR = "encryptionIntVec";

    public static void main(String[] args) {
        String originalString = "Text to be encrypted";
        System.out.println("Original String to encrypt - " + originalString);
        String encryptedString = encrypt(originalString);
        System.out.println("Encrypted String - " + encryptedString);
    }

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}