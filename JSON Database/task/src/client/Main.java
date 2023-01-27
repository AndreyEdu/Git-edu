package client;

import com.beust.jcommander.JCommander;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
                output.writeUTF(arguments.getRequest() + ":" + arguments.getIndex() + ":" + arguments.getValueToSave());
                System.out.println("Sent: " + arguments.getRequest() + " " + arguments.getIndex() + " " + arguments.getValueToSave());
            } else if (arguments.getIndex() == null) {
                output.writeUTF(arguments.getRequest());
                System.out.println("Sent: " + arguments.getRequest());
            } else {
                output.writeUTF(arguments.getRequest() + ":" + arguments.getIndex());
                System.out.println("Sent: " + arguments.getRequest() + " " + arguments.getIndex());
            }
            System.out.print("Received: " + input.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
    }
}