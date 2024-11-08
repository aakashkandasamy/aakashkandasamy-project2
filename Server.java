import java.io.*;
import java.net.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    private ServerSocket serverSocket;
    private List<LocalDateTime> connectedTimes;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.connectedTimes = new ArrayList<>();
    }

    public void serve(int clientCount) {
        ExecutorService executor = Executors.newFixedThreadPool(clientCount);
        for (int i = 0; i < clientCount; i++) {
            try {
                Socket clientSocket = serverSocket.accept();
                connectedTimes.add(LocalDateTime.now());
                executor.execute(() -> handleClient(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String handshake = in.readLine();
            if (!"12345".equals(handshake)) {
                out.println("couldn't handshake");
                clientSocket.close();
                return;
            }

            String numberStr = in.readLine();
            try {
                long number = Long.parseLong(numberStr);
                if (number > Integer.MAX_VALUE) {
                    out.println("There was an exception on the server");
                } else {
                    int factors = calculateFactors((int) number);
                    out.println("The number " + number + " has " + factors + " factors");
                }
            } catch (NumberFormatException e) {
                out.println("Invalid number format");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calculateFactors(int number) {
        int count = 0;
        for (int i = 1; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                count += (i == number / i) ? 1 : 2;
            }
        }
        return count;
    }

    public ArrayList<LocalDateTime> getConnectedTimes() {
        Collections.sort(connectedTimes);
        return new ArrayList<>(connectedTimes);
    }

    public void disconnect() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
