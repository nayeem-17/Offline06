package server.controller.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.controller.DBController;
import server.util.NetworkUtil;

public class Server {
    private static final int nThreads = 10;
    private ServerSocket serverSocket;
    private int numOfClient;
    ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening.....");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        numOfClient = 0;
    }

    public void listener(DBController dbcontroller) {
        while (true) {
            try {
                Socket cliSocket = serverSocket.accept();
                numOfClient++;
                System.out.println(numOfClient);
                NetworkUtil networkUtil = new NetworkUtil(cliSocket);
                executorService.execute(() -> {
                    Thread.currentThread().setName("Client_" + numOfClient);
                    System.out.println(Thread.currentThread().getName() + " connected ");
                    new AsyncReadData(networkUtil, dbcontroller).ReadData();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getNumOfClient() {
        return numOfClient;
    }

}
