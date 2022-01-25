package com.chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class gal extends JFrame implements Speak{
    public void gal(String string) {
        chatArea.append("Lady -> " + string + "\n");
    }

    private JTextArea chatArea = new JTextArea();
    private JTextField chatBox = new JTextField();

    @Override
    public void chat() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(748,502);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Conversation");
        frame.add(chatArea);
        frame.add(chatBox);

        chatArea.setSize(648, 302);
        chatArea.setLocation(2,2);

        chatBox.setSize(650,30);
        chatBox.setLocation(2,400);

        chatBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gText = chatBox.getText().toLowerCase();
                chatArea.append("You -> " + gText + "\n");
                chatBox.setText("");

                if(gText.contains("hi")) {                                                          // Greetings
                    gal("Oh, Hello");

                } else if(gText.contains("time")) {                                                 // Time
                    Date currentDate = new Date();
                    SimpleDateFormat time = new SimpleDateFormat("hh:mm");
                    gal(time.format(currentDate));

                } else if(gText.contains("coffee")) {                                               // Coffee
                    gal("The triple sweet latte, its great here");

                } else if(gText.contains("music")) {                                                // Music
                    int rand = (int) (Math.random() * 3 + 1);
                    if(rand == 1) {
                        gal("Chill Piano, helps me relax in the morning");
                    } else if(rand == 2) {
                        gal("Soft Guitar");
                    } else if(rand == 3) {
                        gal("Well, its so hard to choose ..... depends on the mood");
                    }

                } else {                                                                          // bla bla bla
                    gal("Sorry, what was that");
                }


            }
        });
    }
}
