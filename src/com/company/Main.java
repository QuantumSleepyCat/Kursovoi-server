package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        BDUserConnect bd =new BDUserConnect();
        bd.con();
        try {
            ServerSocket server = new ServerSocket(8071);
            System.out.println("initialized");
            while (true) {
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress().getHostName() + " connected");
                /* * создание отдельного потока для обмена данными * с соединившимся клиентом */
                ServerThread thread = new ServerThread(socket); // запуск потока
                thread.start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

