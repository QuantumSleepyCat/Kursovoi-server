package com.company;
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress; 
import java.net.ServerSocket; 
import java.net.Socket;
/**
 * Created by QuantumCat on 26.10.2016.
 */
public class ServerThread  extends Thread{
    private PrintStream os; // передача
    private BufferedReader is; // прием
    private InetAddress addr; // адрес клиента
    public ServerThread(Socket s) throws IOException {
        os = new PrintStream(s.getOutputStream());
        is = new BufferedReader(new InputStreamReader(s.getInputStream()));
        addr = s.getInetAddress(); }
    public void run() {
        int i = 0;
        String str;
        try {
           if ((str = is.readLine()) != null)
            {
                //if ("PING".equals(str)) {
                    //os.println("PONG " + ++i);
                //}
                System.out.println(str);
            }
        }
        catch (IOException e) { // если клиент не отвечает, соединение с ним разрывается
             System.err.println("Disconnect!");
        }
        finally { disconnect(); // уничтожение потока
             }
        }
    public void disconnect() {
        try {
            if (os != null)
            {
                os.close();
            }
            if (is != null)
            {
                is.close();
            }
            System.out.println(addr.getHostName() + " disconnecting");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally { 
            this.interrupt();
        }
    }
}
