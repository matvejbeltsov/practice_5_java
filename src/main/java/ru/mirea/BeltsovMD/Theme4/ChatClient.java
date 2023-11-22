package ru.mirea.BeltsovMD.Theme4;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("services.tms-studio.ru", 27015);
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
        System.out.println("Введите ваш ник:");
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String nickName = reader.readLine();
        System.out.println("Ваш ник: " + nickName);
        out.write(nickName);
        out.newLine();
        out.flush();
        ReadWorker readWorker=new ReadWorker(server,in);
        WriteWorker writeWorker=new WriteWorker(server,out);

        Thread rThread=new Thread(readWorker);
        Thread wThread=new Thread(writeWorker);

        rThread.start();
        wThread.start();

        while (server.isConnected()) {
            Thread.onSpinWait();
        }
    }
}
