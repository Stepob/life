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

        final JButton startButton = new JButton("Start");
        final JButton stopButton = new JButton("Stop");
        stopButton.setEnabled(false);

        startButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Thread t = new Thread(lifeCanvas);
                        t.start();
                        startButton.setEnabled(false);
                        stopButton.setEnabled(true);
                    }
                }
        );

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lifeCanvas.stop();
                startButton.setEnabled(true);
                stopButton.setEnabled(false);

            }
        });

        JButton nextGenButton = new JButton("Next Gen");
        nextGenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LifeMatrix.getInstance().nextGen2();
                lifeCanvas.repaint();
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LifeMatrix.getInstance().clearMatrix();
                lifeCanvas.repaint();
            }
        });

        JButton newMatrixButton = new JButton("New Matrix");
        newMatrixButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LifeMatrix.getInstance().initMatrix();
                lifeCanvas.repaint();
            }
        });

        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(lifeCanvas, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new GridLayout(1, 5));
        southPanel.add(startButton);
        southPanel.add(stopButton);
        southPanel.add(nextGenButton);
        southPanel.add(clearButton);
        southPanel.add(newMatrixButton);

        getContentPane().add(southPanel, BorderLayout.SOUTH);


        setJMenuBar(new LifeMenu(lifeCanvas));

        //Display the window.
        pack();
        setVisible(true);
    }
}
