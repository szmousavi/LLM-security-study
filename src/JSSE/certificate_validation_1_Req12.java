import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class certificate_validation_1_Req12 {
  public static void main(String[] args) {
    new certificate_validation_1_Req12().testCertificate();
  }

  private void testCertificate() {
    try {
      URL url = new URL("https://www.yourserver.com");
      URLConnection urlConnection = url.openConnection();
      if(urlConnection instanceof HttpsURLConnection) {
        HttpsURLConnection conn = (HttpsURLConnection) urlConnection;
        conn.connect();
        Certificate[] certs = conn.getServerCertificates();
        for(Certificate cert : certs){
          System.out.println("Certificate is: " + cert);
          if(cert instanceof X509Certificate){
            try {
              ((X509Certificate) cert).checkValidity();
              System.out.println("Certificate is valid");
            } catch (Exception e) {
                System.out.println("Certificate not valid");
            }
          }
        }
      } else {
        System.out.println("This is not a secure connection");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}