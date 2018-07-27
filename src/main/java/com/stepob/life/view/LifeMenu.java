package com.stepob.life.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class LifeMenu extends JMenuBar {

    LifeMenu() {

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu help = new JMenu("Help");
        file.setMnemonic(KeyEvent.VK_H);

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Programma life");
            }
        });



        file.add(exit);
        help.add(about);
        add(file);
        add(help);

    }
}
