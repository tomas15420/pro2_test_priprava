package layout.server;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    JTextArea taLog;
    ServerSocket serverSocket;

    public ServerThread(JTextArea log){
        taLog = log;
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                Socket socket = serverSocket.accept();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            String firstLine = reader.readLine();
                            taLog.read(reader,this);

                            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                            printWriter.println("HTTP/1.1 200 OK");
                            printWriter.println();
                            if(firstLine.contains("test")) {
                                printWriter.println("Test");
                                System.out.println("test");
                            }
                            printWriter.close();

                            socket.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void obsluhaPozadavku(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            taLog.append(line+"\n");
        }
        bufferedReader.close();

        writeData(socket.getOutputStream());
        socket.close();
    }

    public void writeData(OutputStream outStream) throws IOException{
        PrintWriter printWriter = new PrintWriter(outStream);

        String content = "<b>Test</b>";
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-type: text/html");
        printWriter.println("Content-length: "+content.length());
        printWriter.println();
        printWriter.println(content);
        printWriter.flush();
        printWriter.close();
    }
}
