package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    switch (in.readLine()) {
                        case ("GET /?msg=Exit HTTP/1.1") -> server.close();
                        case ("GET /?msg=Hello HTTP/1.1") -> out.write("Hello, dear friend.".getBytes());
                        default -> out.write("What".getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}