package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;

    public static void main(String[] args) {
        System.out.println("Server started!");

        while (true) {
            try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS));
                Socket socket = server.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
                String request = input.readUTF();
                output.writeUTF(run(request));
                if (request.contains("exit")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String run(String request) {
        String[] input = request.split(":");

        int index = 0;

        if (input.length > 1) {
            index = Integer.parseInt(input[1]);
        }

        String text = "";

        if (input.length > 2) {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < input.length; i++) {
                sb.append(input[i]).append(" ");
            }
            text = sb.toString();
        }

        switch (input[0]) {
            case "get":
                return Data.get(index);
            case "set":
                return Data.set(index, text);
            case "delete":
                return Data.delete(index);
            case "exit":
                return "OK";
            default:
                return "ERROR";
        }
    }
}
