import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

class sym_encryption_CBC_3_Req14 {
  private static String algorithm = "AES"; 
  private static String cipherTransformation = "AES/CBC/PKCS5PADDING"; 
  private static String aesEncryptionAlgorithm = "AES";
  
  public static void main(String[] args) {
    String secret = "1234567890123456"; // represent 16-byte key
    String vector = "Vector1234567890"; 
    String encryptedText = "BZGvqYGT06FhAWgajyU5Xg=="; // needs to replace that with the appropriate base64 string
   
    String decryptedText = decrypt(encryptedText, secret, vector);
    System.out.println("Decrypted Text: " + decryptedText);
  }
  
  public static String decrypt(String encryptedText, String key, String initVector){
    try {
      Cipher cipher = Cipher.getInstance(cipherTransformation);
      byte[] keyBytes= new byte[16];
      byte[] b= key.getBytes("UTF-8");
      int len= b.length; 
      if (len > keyBytes.length) len = keyBytes.length; 
      System.arraycopy(b, 0, keyBytes, 0, len);
      Key secretKey= new SecretKeySpec(keyBytes, aesEncryptionAlgorithm);
      IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector.getBytes("UTF-8"));
      byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
      
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
      byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
      return new String(decryptedBytes);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}