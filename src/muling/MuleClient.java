package muling;

import org.dreambot.api.utilities.Logger;

import java.io.*;
import java.net.*;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.utilities.Logger;

public class MuleClient {
    private static MuleClient instance;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private MuleClient() {
        // Private constructor to prevent instantiation
    }

    public static MuleClient getInstance() {
        if (instance == null) {
            instance = new MuleClient();
        }
        return instance;
    }

    public void startConnection(String ip, int port) throws IOException {
        if (clientSocket == null || clientSocket.isClosed()) {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Logger.log("Connected to server: " + ip + ":" + port);
        }
    }

    public void sendMessage(String msg) throws IOException {
        if (out != null) {
            out.println(msg);
            Logger.log("Message sent: " + msg);
            Logger.log("Server response: " + in.readLine());
        }
    }

    public void stopConnection() throws IOException {
        if (in != null) in.close();
        if (out != null) out.close();
        if (clientSocket != null) clientSocket.close();
        clientSocket = null;
        out = null;
        in = null;
        Logger.log("Client connection closed.");
    }
}
