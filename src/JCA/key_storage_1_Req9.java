import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.io.FileOutputStream;

public class key_storage_1_Req9
{
    public static void main(String[] args) throws Exception
    {
        // Set up
        String keyStoreType = "JCEKS";
        String keyStoreFile = "my_keystore.jceks";
        String keyStorePassword = "Password123";
        String alias = "MySecretKey";
        String secretKeyPassword = "Password456";

        // Generate a secret key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, SecureRandom.getInstance("SHA1PRNG"));
        SecretKey secretKey = keyGen.generateKey();

        // convert SecretKey to SecretKeyEntry
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBE");
        PBEKeySpec keySpec = new PBEKeySpec(secretKeyPassword.toCharArray());
        SecretKey key = factory.generateSecret(keySpec);
        KeyStore.SecretKeyEntry keyEntry = new SecretKeyEntry(key);
      
        // Save the secret key into the keystore
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null); // initialize an empty key store
        keyStore.setEntry(alias, keyEntry, new KeyStore.PasswordProtection(secretKeyPassword.toCharArray()));

        // Save the keystore to a file
        try (FileOutputStream fos = new FileOutputStream(keyStoreFile)) {
            keyStore.store(fos, keyStorePassword.toCharArray());
        }

        System.out.println("The secret key was stored successfully.");
    }
}