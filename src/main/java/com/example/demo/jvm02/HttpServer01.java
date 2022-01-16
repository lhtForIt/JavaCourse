package com.example.demo.jvm02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Leo liang
 * @Date 2022/1/16
 */
public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio1";
            pw.println("Content-Length:" + body.getBytes().length);
            pw.println();
            pw.write(body);
            pw.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
