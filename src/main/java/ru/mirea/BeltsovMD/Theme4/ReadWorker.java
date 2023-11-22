package ru.mirea.BeltsovMD.Theme4;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ReadWorker implements Runnable{
    private Socket server;
    private BufferedReader in;

    @Override
    public void run() {
        try {
            while (!server.isInputShutdown()) {
                String message =in.readLine();

                if (message!= null) {
                    System.out.println("Hello, " + message);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public ReadWorker(Socket server, BufferedReader in) {
        this.in=in;
        this.server=server;
    }
}
