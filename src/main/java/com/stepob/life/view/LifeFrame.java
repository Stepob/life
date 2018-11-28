package com.stepob.life.view;

import com.stepob.life.world.LifeMatrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LifeFrame extends JFrame implements PropertyChangeListener {

    private LifeCanvas lifeCanvas;

    private JLabel generationLabel = new JLabel("TEST");

    public LifeFrame(String title) throws HeadlessException {
        super(title);

        LifeMatrix.getInstance().addChangeListener(this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Life", getLifePanel());
        tabbedPane.addTab("Dino", getDinoPanel());

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        setJMenuBar(new LifeMenu(lifeCanvas));

        //Display the window.
        pack();
        setVisible(true);
    }

    private JPanel getLifePanel() {

        JPanel lifePanel = new JPanel(new BorderLayout());

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

        JPanel northPanel = new JPanel(new GridLayout(1, 1));
        northPanel.add(generationLabel);
        lifePanel.add(northPanel, BorderLayout.NORTH);

        lifePanel.add(lifeCanvas, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new GridLayout(1, 5));
        southPanel.add(startButton);
        southPanel.add(stopButton);
        southPanel.add(nextGenButton);
        southPanel.add(clearButton);
        southPanel.add(newMatrixButton);

        lifePanel.add(southPanel, BorderLayout.SOUTH);

        return lifePanel;
    }

    private JPanel getDinoPanel() {
        final JPanel dinoPanel = new JPanel(new BorderLayout());

        final ScrollCanvas scrollCanvas = new ScrollCanvas(true);
        dinoPanel.add(scrollCanvas, BorderLayout.CENTER);

        JButton upButton = new JButton("U");
        JButton downButton= new JButton("D");
        JButton leftButton = new JButton("L");
        JButton rightButton = new JButton("R");

        upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollCanvas.imgUp();
            }
        });
        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollCanvas.imgDown();
            }
        });
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollCanvas.imgLeft();
            }
        });
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollCanvas.imgRight();
            }
        });


        JPanel southPanel = new JPanel(new GridLayout(3, 3));
        southPanel.add(new JPanel());
        southPanel.add(upButton);
        southPanel.add(new JPanel());
        southPanel.add(leftButton);
        southPanel.add(new JPanel());
        southPanel.add(rightButton);
        southPanel.add(new JPanel());
        southPanel.add(downButton);
        southPanel.add(new JPanel());

        dinoPanel.add(southPanel, BorderLayout.SOUTH);

        return dinoPanel;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(LifeMatrix.GENERATION_NUMBER)) {
            generationLabel.setText("Generation number: " + evt.getNewValue());
        }
    }
}
