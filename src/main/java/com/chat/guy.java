package com.chat;

import com.sun.xml.internal.fastinfoset.sax.Features;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class guy extends JFrame implements Speak {

    public void guy(String string) {
        chatArea.append("Gentleman -> " + string + "\n");
    }

    private JTextArea chatArea = new JTextArea();
    private JTextField chatBox = new JTextField();

    @Override
    public void chat() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(748, 502);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Conversation");
        frame.add(chatArea);
        frame.add(chatBox);

        chatArea.setSize(648, 302);
        chatArea.setLocation(2, 2);

        chatBox.setSize(650, 30);
        chatBox.setLocation(2, 400);

        chatBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gText = chatBox.getText().toLowerCase();
                chatArea.append("You -> " + gText + "\n");
                chatBox.setText("");

                if (gText.contains("hi")) {                                                          // Greetings
                    guy("Wassup bro");

                } else if (gText.contains("time")) {                                                 // Time
                    Date currentDate = new Date();
                    SimpleDateFormat time = new SimpleDateFormat("hh:mm");
                    guy(time.format(currentDate));

                } else if (gText.contains("coffee")) {                                               // Coffee
                    guy("Just bland dark coffee no sugar, helps you through the day");

                } else if (gText.contains("music")) {                                                // Music
                    int rand = (int) (Math.random() * 3 + 1);
                    if (rand == 1) {
                        guy("Chill Piano, helps me relax in the morning");
                    } else if (rand == 2) {
                        guy("Soft Guitar");
                    } else if (rand == 3) {
                        guy("Well, its so hard to choose ..... depends on the mood");
                    }

                } else {                                                                          // bla bla bla
                    guy("What was that mate");
                }
            }
        });
    }
}
