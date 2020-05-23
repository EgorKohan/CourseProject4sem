package client;

import java.io.IOException;

public class ClientRunner {
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.start("localhost", 2990);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
