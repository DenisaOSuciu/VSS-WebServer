package gui;
import com.sun.tools.javac.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import server.ServerConfig;

public class UserInterface  implements ActionListener {
    private  JFrame frame;
    private  JPanel window;
    private JButton btn_start;
    private JButton btn_maintain;
    private JButton btn_stop;
    private JLabel text;
    private String url="http://127.0.0.1:44055/";
    private ServerConfig config=new ServerConfig(44055, "index.html", "Running");
    public UserInterface() throws IOException {
        frame = new JFrame();
        window = new JPanel();
        window.add(btn_start);
        window.add(btn_maintain);
        window.add(btn_stop);
        window.add(text);
        btn_start = new JButton("Start Server");
        btn_maintain = new JButton("Maintain State");
        btn_stop = new JButton("Stop Server");
        text = new JLabel("");


        btn_start.addActionListener(this);
        btn_maintain.addActionListener(this);
        btn_stop.addActionListener(this);

        window.setBorder(BorderFactory.createEmptyBorder(150,150,150,150));

        frame.add(window, BorderLayout.CENTER);
        frame.setSize(200,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_start){
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            text.setText("SERVER STARTED ");
        }
        if(e.getSource()==btn_maintain){
            text.setText("Server in maintenance");
        }
        if(e.getSource()==btn_stop){
            System.exit(0);

        }
    }
}
