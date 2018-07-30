package com.stepob.life.view;

import com.stepob.life.world.LifeMatrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LifeFrame extends JFrame {

    private LifeCanvas lifeCanvas;

    public LifeFrame(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        lifeCanvas = new LifeCanvas(false);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Thread t = new Thread(lifeCanvas);
                        t.start();
                    }
                }
        );

        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(lifeCanvas, BorderLayout.CENTER);

        getContentPane().add(startButton, BorderLayout.SOUTH);


        setJMenuBar(new LifeMenu());

        //Display the window.
        pack();
        setVisible(true);
    }
}
