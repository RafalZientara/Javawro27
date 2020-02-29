package pl.sda.rafal.zientara.programowanie2.lesson4.userDetails;

import javax.swing.*;
import java.awt.*;

public class UserMain {

    public static void main(String[] args) {

        JFrame frame = new JFrame("User Details");
        frame.setLayout(null);
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton readFile = new JButton("Read File");
        readFile.setBounds(35,50,100,50);
        frame.add(readFile);
        JTextField fileName = new JTextField("nazwa pliku");
        fileName.setBounds(145,50,200,50);
        frame.add(fileName);
        JLabel labelForDetails = new JLabel();
        labelForDetails.setBounds(70,160,250,200);
        labelForDetails.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        labelForDetails.setHorizontalTextPosition(SwingConstants.CENTER);
        frame.add(labelForDetails);
        readFile.addActionListener(e -> {
            printDetailsFromFile(fileName, labelForDetails);
        });
        frame.setVisible(true);

    }

    public static void printDetailsFromFile(JTextField fileName, JLabel label) {
        UserDetails user = new UserDetails();
        user.readFile(fileName.getText());
        String userDetails = "<html>Name:  " + user.getName()
                + "<br><br>Age:  " + user.getAge()
                +"<br><br>Email:  " + user.getEmail()
                +"<br><br>Phone:  " + user.getPhoneNumber() + "</html>";
        label.setText(userDetails);
        System.out.println(user.toString());    //can be removed
    }
}
