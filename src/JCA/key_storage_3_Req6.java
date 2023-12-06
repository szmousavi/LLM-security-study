import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class key_storage_3_Req6 {

    // Setting path of the certificate and the keystore as constants
    private static final String CERTIFICATE_PATH = "/path/to/your/certificate.crt"; // Put your certificate path here
    private static final String KEYSTORE_PATH = "/path/to/your/keystore.jks"; // Put your keystore path here

    // Setting Keystore password and alias as constants
    private static final String KEYSTORE_PASSWORD = "yourKeystorePassword"; // Put your keystore password here
    private static final String CERTIFICATE_ALIAS = "yourCertificateAlias"; // Put your certificate alias here

    public static void main(String[] args) {
        try {
            // Create a certificate factory
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

            // Establish a certificate from file input stream
            FileInputStream certInput = new FileInputStream(CERTIFICATE_PATH);
            Certificate cert = certFactory.generateCertificate(certInput);

            // Establish a keystore from file input stream with the specified keystore password
            FileInputStream keyStoreInput = new FileInputStream(KEYSTORE_PATH);
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(keyStoreInput, KEYSTORE_PASSWORD.toCharArray());

            // Add the certificate to the KeyStore
            keyStore.setCertificateEntry(CERTIFICATE_ALIAS, cert);

            // Save the KeyStore
            FileOutputStream keyStoreOutput = new FileOutputStream(KEYSTORE_PATH);
            keyStore.store(keyStoreOutput, KEYSTORE_PASSWORD.toCharArray());

            // Properly close all file streams
            certInput.close();
            keyStoreInput.close();
            keyStoreOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}