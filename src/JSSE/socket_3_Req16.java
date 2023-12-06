import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class socket_3_Req16 {
    public static void main(String[] args) {
        String serverName = "Your server name";
        int port = 1234; // Replace with your server port

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(serverName, port);

            // Enable all the suites
            socket.setEnabledCipherSuites(socket.getSupportedCipherSuites());

            // Initialize the input and output stream
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // Write a test message to the server
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream));
            out.println("Hello Server");
            out.flush();

            // Read the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String message = in.readLine();
            System.out.println("Server response: " + message);
            
            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}