import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public Socket getSocket() {
        return socket;
    }

    public void handshake() {
        out.println("12345");
    }

    public String request(String number) throws IOException {
        out.println(number);
        return in.readLine();  // Get server response
    }

    public void disconnect() throws IOException {
        socket.close();
    }
}