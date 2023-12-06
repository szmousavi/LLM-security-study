import javax.net.ssl.*;
import java.net.URL;

public class certificate_validation_2_Req20 {
    public static void main(String[] args) {
        try {
            // Specify the host and the port we want to connect to
            String host = "your host here";
            int port = 443;  // Change the port if required

            // Create a SSLContext 
            SSLContext context = SSLContext.getDefault();

            // Create a SSL socket factory from the SSL context
            SSLSocketFactory sf = context.getSocketFactory();

            // Create a SSL socket
            SSLSocket socket = (SSLSocket) sf.createSocket(host, port);

            // Create a SSL session
            SSLSession session = socket.getSession();

            // We can validate the server certificate here
            java.security.cert.Certificate[] serverCerts = session.getPeerCertificates();

            // Display the server certificate 
            for (int i = 0; i < serverCerts.length; i++) {
                System.out.println("=== Server Certificate " + (i+1) + " ===");
                System.out.println("-Public Key-\n" + serverCerts[i].getPublicKey());
                System.out.println("-Certificate Type-\n " + serverCerts[i].getType());

                // Validate certificate
                serverCerts[i].verify(serverCerts[i].getPublicKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}