package com.stepob.life.view;

import com.stepob.life.world.LifeMatrix;

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

        JMenu world = new JMenu("World");
        file.setMnemonic(KeyEvent.VK_W);

        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LifeMatrix.getInstance().clearMatrix();
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
        world.add(clear);
        help.add(about);
        add(file);
        add(world);
        add(help);

    }
}
