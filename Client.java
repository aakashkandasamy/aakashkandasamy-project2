import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private PrintWriter out;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public Socket getSocket() {
        return socket;
    }

    public void handshake() {
        out.println("12345");  // Send handshake passcode
    }

    public String request(String number) {
        return null;  // Not implemented yet
    }

    public void disconnect() throws IOException {
        socket.close();
    }
}