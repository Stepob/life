package com.stepob.life.view;

import javax.swing.*;
import java.awt.*;

public class LifeFrame extends JFrame {

    public LifeFrame(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label1 = new JLabel("Hello World");
        JLabel label2 = new JLabel("Ciao Mondo");
        getContentPane().add(label1);
        getContentPane().add(label2);


        setJMenuBar(new LifeMenu());

        //Display the window.
        pack();
        setVisible(true);
    }
}
