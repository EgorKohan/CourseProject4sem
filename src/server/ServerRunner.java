package server;

import java.io.IOException;

public class ServerRunner {
    public static void main(String[] args) {
        try {
            Server server = new Server(2990);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
