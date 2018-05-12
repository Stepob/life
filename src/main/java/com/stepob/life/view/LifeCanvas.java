package com.stepob.life.view;

import com.stepob.life.world.LifeMatrix;

import javax.swing.*;
import java.awt.*;

public class LifeCanvas extends JPanel implements Runnable{

    LifeMatrix m = new LifeMatrix();

    public LifeCanvas(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        setPreferredSize(new Dimension(800, 600));

        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        setBackground(Color.BLACK);

        m.paint(g2);

    }

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();


        while (true){

            m.nextGen();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 1000 - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {

            }

            beforeTime = System.currentTimeMillis();
        }
    }
}
