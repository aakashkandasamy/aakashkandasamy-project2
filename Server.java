import java.io.*;
import java.net.*;
import java.time.*;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void serve(int clientCount) {
        for (int i = 0; i < clientCount; i++) {
            try {
                Socket clientSocket = serverSocket.accept();
                // No handshake or request handling implemented yet
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<LocalDateTime> getConnectedTimes() {
        return new ArrayList<>();  // Empty implementation
    }

    public void disconnect() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}