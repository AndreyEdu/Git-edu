package server;

import client.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

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
                JSON json = new Gson().fromJson(input.readUTF(), JSON.class);
                output.writeUTF(new Gson().toJson(run(json)));
                if (json.getType().contains("exit")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static JSONResponse run(JSON json) {

        JSONResponse jsonResponse = new JSONResponse();

        switch (json.getType()) {
            case "get":
                Data.get(json.getKey(), jsonResponse);
                return jsonResponse;
            case "set":
                Data.set(json.getKey(), json.getValue(), jsonResponse);
                return jsonResponse;
            case "delete":
                Data.delete(json.getKey(), jsonResponse);
                return jsonResponse;
            case "exit":
                jsonResponse.setResponse("OK");
                return jsonResponse;
            default:
                jsonResponse.setResponse("ERROR");
                return jsonResponse;
        }
    }
}
