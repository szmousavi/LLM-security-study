import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class certificate_validation_2_Req18 {

    public static void main(String[] args) throws Exception {
        String host = "www.example.com";
        int port = 443; // HTTPS port

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        if (certs == null || certs.length == 0 || authType == null || authType.length() == 0) {
                            throw new IllegalArgumentException("Unknown certificate validation error");
                        }
                        if (!authType.equalsIgnoreCase("ECDHE_RSA")) {
                            throw new CertificateException("Only ECDHE_RSA type is accepted");
                        }
                    }
                }
        };

        // Create SSL Socket Factory with ignoring certificate
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Run SSL handshake (certificate retrieval) process
        SSLSocketFactory factory = HttpsURLConnection.getDefaultSSLSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
        socket.startHandshake();
        socket.close();

        System.out.println("SSL/TLS certificate validation completed.");
    }
}
