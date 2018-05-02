package com.stepob.life.view;

import javax.swing.*;
import java.awt.*;

public class LifeFrame extends JFrame {

    public LifeFrame(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(new LifeCanvas(false), BorderLayout.CENTER);

        setJMenuBar(new LifeMenu());

        //Display the window.
        pack();
        setVisible(true);
    }
}
