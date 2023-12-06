import javax.crypto.KeyGenerator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class key_storage_1_Req22 {

    public static void main(String[] args) {
        try {
            // Generate a key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            Key key = keyGen.generateKey();
            
            // Create a keystore
            KeyStore keyStore = KeyStore.getInstance("JCEKS");

            // Load the keystore
            char[] keystorePassword = "password".toCharArray();
            keyStore.load(null, keystorePassword);

            // Store the key in the keystore
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(keystorePassword);
            KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry((javax.crypto.SecretKey) key);
            keyStore.setEntry("alias", skEntry, protParam);

            // Write the keystore to a file
            try (FileOutputStream fos = new FileOutputStream("keystore.jks")) {
                keyStore.store(fos, keystorePassword);
            }

            System.out.println("The key has been stored successfully.");
            
        } catch (NoSuchAlgorithmException | KeyStoreException | CertificateException | IOException e) {
            e.printStackTrace();
        }
    }
}