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
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String handshake = in.readLine();
                if (!"12345".equals(handshake)) {
                    out.println("couldn't handshake");
                    clientSocket.close();
                    continue;
                }

                String number = in.readLine();
                out.println("Received number " + number);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<LocalDateTime> getConnectedTimes() {
        return new ArrayList<>();
    }

    public void disconnect() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}