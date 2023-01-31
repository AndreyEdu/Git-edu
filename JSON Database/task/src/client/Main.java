package client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Main {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;

    public static void main(String[] args) throws IOException {
        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
        System.out.println("Client started!");
        try (DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            if (arguments.getValueToSave() != null) {
                output.writeUTF(new Gson().toJson(new JSON(arguments.getRequest(), arguments.getKey(), arguments.getValueToSave())));
                System.out.println("Sent: " + new Gson().toJson(new JSON(arguments.getRequest(), arguments.getKey(), arguments.getValueToSave())));
            } else if (arguments.getKey() == null) {
                output.writeUTF(new Gson().toJson(new JSON(arguments.getRequest())));
                System.out.println("Sent: " + new Gson().toJson(new JSON(arguments.getRequest())));
            } else {
                output.writeUTF(new Gson().toJson(new JSON(arguments.getRequest(), arguments.getKey())));
                System.out.println("Sent: " + new Gson().toJson(new JSON(arguments.getRequest(), arguments.getKey())));
            }
            System.out.print("Received: " + input.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
    }
}