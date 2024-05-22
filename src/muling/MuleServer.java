package muling;

import org.dreambot.api.utilities.Logger;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import org.dreambot.api.utilities.Logger;

public class MuleServer {
    private static MuleServer instance;
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private boolean running = true;

    private MuleServer() {
        // Private constructor to prevent instantiation
    }

    public static MuleServer getInstance() {
        if (instance == null) {
            instance = new MuleServer();
        }
        return instance;
    }

    public void start(int port) throws IOException {
        if (serverSocket == null || serverSocket.isClosed()) {
            serverSocket = new ServerSocket(port);
            executorService = Executors.newCachedThreadPool();
            running = true;
            Logger.log("Server started on port: " + port);
            new Thread(() -> {
                while (running) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        Logger.log("Client connected: " + clientSocket.getInetAddress());
                        executorService.submit(new ClientHandler(clientSocket));
                    } catch (IOException e) {
                        if (!running) {
                            Logger.log("Server stopped.");
                            break;
                        }
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void stop() {
        running = false;
        executorService.shutdown();
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverSocket = null;
        Logger.log("Server stopped.");
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String message;
                while ((message = in.readLine()) != null) {
                    Logger.log("Received: " + message);
                    out.println("Echo: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
