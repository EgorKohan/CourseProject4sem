package client;

import entity.Storage;
import holder.ResumeHolder;
import holder.TractorHolder;
import holder.UserHolder;
import menu.MainMenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    private Socket clientSocket;

    public void sendGreetingsMessage(String greetings) throws IOException {
        PrintStream ps = new PrintStream(clientSocket.getOutputStream());
        ps.println(greetings);
        ps.flush();
    }

    public void start(String host, int port) throws IOException {
        try {
            clientSocket = new Socket(host, port);
            sendGreetingsMessage("Hello, i'm " + this);
            serverContact();
            MainMenu mainMenu = new MainMenu();
            mainMenu.launch();
            sendStorageToServer();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Прервана работа клиента");
        }
        closeConnection();
    }

    public void serverContact() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        ois = new ObjectInputStream(clientSocket.getInputStream());
        Storage.setUsers((UserHolder) ois.readObject());
        Storage.setTractors((TractorHolder) ois.readObject());
        Storage.setResumes((ResumeHolder) ois.readObject());
    }

    public void sendStorageToServer() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        oos.writeObject(Storage.getUsers());
        oos.writeObject(Storage.getTractors());
        oos.writeObject(Storage.getResumes());
    }

    public void closeConnection() {
        try {
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
