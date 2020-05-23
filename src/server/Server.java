package server;

import entity.Storage;
import holder.ResumeHolder;
import holder.TractorHolder;
import holder.UserHolder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;
    private Storage serverStorage = new Storage();
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void sendStorageToUser(Socket client) throws IOException {
        oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject(Storage.getUsers());
        oos.writeObject(Storage.getTractors());
        oos.writeObject(Storage.getResumes());
        System.out.println("Данные отправлены");
    }

    public void getStorageFromClient(Socket client) throws IOException, ClassNotFoundException {
        ois = new ObjectInputStream(client.getInputStream());
        Storage.setUsers((UserHolder) ois.readObject());
        Storage.setTractors((TractorHolder) ois.readObject());
        Storage.setResumes((ResumeHolder) ois.readObject());
        System.out.println("Данные приняты");
    }

    private void clientContact(Socket client) {
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(bf.readLine());
            sendStorageToUser(client);
            getStorageFromClient(client);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (bf != null) {
                    bf.close();
                }
            } catch (IOException e) {
                System.out.println("Не удается закрыть потоки данных");
                e.printStackTrace();
            }
        }
    }

    public void start() throws IOException {
        System.out.println("Сервер запущен");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            serverStorage.readAllFromFile();
            new Thread(() -> {
                clientContact(clientSocket);
                serverStorage.writeAllToFile();
            }).start();
        }
    }

}
