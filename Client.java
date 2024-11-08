import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
    }

    public Socket getSocket() {
        return socket;
    }

    public void handshake() {
        // Not implemented yet
    }

    public String request(String number) {
        return null;  // Not implemented yet
    }

    public void disconnect() throws IOException {
        socket.close();
    }
}