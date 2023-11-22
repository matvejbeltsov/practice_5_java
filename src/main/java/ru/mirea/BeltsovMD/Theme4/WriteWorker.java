package ru.mirea.BeltsovMD.Theme4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class WriteWorker implements Runnable {
    Socket server;
    BufferedWriter out;

    @Override
    public void run() {
        try {
            Scanner input=new Scanner(System.in);
            while (!server.isOutputShutdown()) {

                String outputMessage=input.nextLine();
                if (!outputMessage.isEmpty()) {
                    out.write(outputMessage);
                    out.newLine();
                    out.flush();
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public WriteWorker(Socket server, BufferedWriter out) {
        this.out=out;
        this.server = server;
    }
}
