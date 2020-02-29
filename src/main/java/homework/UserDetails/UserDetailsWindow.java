package homework.UserDetails;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

public class UserDetailsWindow {
    private UserDetails mainUserDetails;
    private JFrame window;
    private JTextField name;
    private JTextField age;
    private JTextField email;
    private JTextField phone;
    private JLabel nameInfo;
    private JLabel ageInfo;
    private JLabel emailInfo;
    private JLabel phoneInfo;
    private JFileChooser jFileChooser = new JFileChooser();

    public UserDetailsWindow(UserDetails mainUserDetails) {
        this.mainUserDetails = mainUserDetails;
        generateWindow(mainUserDetails);
    }


    private void generateWindow(UserDetails mainUserDetails) {
        window = new JFrame("SuperUper User's Details Viewer - WOW!!");
        window.setSize(400,600);
        window.setLayout(null);

        nameInfo = new JLabel("Name");
        nameInfo.setBounds(75,50,170,50);
        name = new JTextField();
        name.setBounds(125,50,170,50);
        name.setEditable(false);

        ageInfo = new JLabel("Age");
        ageInfo.setBounds(75,150,170,50);
        age = new JTextField();
        age.setBounds(125,150,170,50);
        age.setEditable(false);

        emailInfo = new JLabel("Email");
        emailInfo.setBounds(75,250,170,50);
        email = new JTextField();
        email.setBounds(125,250,170,50);
        email.setEditable(false);

        phoneInfo = new JLabel("Phone");
        phoneInfo.setBounds(75,350,170,50);
        phone = new JTextField();
        phone.setBounds(125,350,170,50);
        phone.setEditable(false);

        JButton openButton = new JButton("OPEN");
        openButton.setBounds(75,450,220,25);
        openButtonListener(openButton);

        window.add(name);
        window.add(age);
        window.add(email);
        window.add(phone);
        window.add(nameInfo);
        window.add(ageInfo);
        window.add(emailInfo);
        window.add(phoneInfo);
        window.add(openButton);

        name.setText(mainUserDetails.getName());
        age.setText(mainUserDetails.getAge());
        email.setText(mainUserDetails.getEmail());
        phone.setText(mainUserDetails.getPhone());

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void setMainUserDetails(UserDetails mainUserDetails) {
        this.mainUserDetails = mainUserDetails;
    }

    public void openFileChooser(JFileChooser jFileChooser){
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TXT Files", "txt");
        jFileChooser.addChoosableFileFilter(filter);
        int returnVal = jFileChooser.showOpenDialog(window);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            mainUserDetails.updateUserDetails(file);
            window.dispose();
            generateWindow(mainUserDetails);
        } else {
            System.out.println(("Anulowano wybieranie pliku! NO NIE!"));
        }




    }

    public void openButtonListener(JButton jButton){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileChooser(jFileChooser);
            }
        });
    }
}
