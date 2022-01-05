package layout.server;

import javax.swing.*;

public class Server extends JFrame {

    private JTextArea taLog = new JTextArea();
    private final static int PORT = 5000;
    private ServerThread serverThread;

    public Server(){
        super("Server");
        setSize(600,480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(taLog);
        serverThread = new ServerThread(taLog);
        serverThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Server();
                frame.setVisible(true);
            }
        });
    }
}
