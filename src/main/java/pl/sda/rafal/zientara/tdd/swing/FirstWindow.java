package pl.sda.rafal.zientara.tdd.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow {
    private JButton button;
    private JLabel counter;
    private int clickCount = 0;

    public FirstWindow() {
        JFrame frame = new JFrame("Siema");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(300, 350);

        button = new JButton("wincyj!");
        button.setBounds(50, 50, 200, 100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("click!");
                clickCount++;
                String text = Integer.toString(clickCount);
                counter.setText(text);
            }
        });
        frame.add(button);

        counter = new JLabel("0", SwingConstants.CENTER);
        counter.setBounds(50, 200, 200, 100);
        frame.add(counter);

        frame.setLayout(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new FirstWindow();
    }

}
