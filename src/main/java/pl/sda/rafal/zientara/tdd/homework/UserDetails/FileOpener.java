package pl.sda.rafal.zientara.tdd.homework.UserDetails;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileOpener {
private DefaultListModel<UserDetails> userList = new DefaultListModel<>();
private JList<UserDetails> label;


    public FileOpener() {
        menu();
    }

    private void getUser() {
        userList.clear();
        File file = new File("user details.txt");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            line= buffer.readLine();
            while (line!=null) {
                String name = line.split("Name: ")[1];
                line = buffer.readLine();
                int age = Integer.parseInt(line.split("Age: ")[1]);
                line = buffer.readLine();
                String email = line.split("Email: ")[1];
                line = buffer.readLine();
                String phone = line.split("Phone: ")[1];
                UserDetails user = new UserDetails(name,age,email,phone);
                userList.addElement(user);
                line= buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        label.setModel(userList);
    }

    private void menu() {
        JFrame frame = new JFrame("get IO data");
        frame.setSize(500,300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        label = new JList<>();
        label.setBounds(0,100,500,200);
        frame.add(label);

        JButton button = new JButton("Get Data");
        button.setBounds(200,50,100,50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getUser();
            }
        });
        frame.add(button);
        frame.setVisible(true);
    }

}
