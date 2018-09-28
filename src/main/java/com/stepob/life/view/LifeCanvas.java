package com.stepob.life.view;

import com.stepob.life.world.LifeMatrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LifeCanvas extends JPanel implements Runnable, MouseListener {

    private boolean isRunning = true;

    private LifeMatrix m = LifeMatrix.getInstance();

    public LifeCanvas(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        setPreferredSize(new Dimension(800, 600));

        addMouseListener(this);

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

        isRunning = true;

        while (isRunning) {

            m.nextGen2();
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

    public void stop(){
        isRunning = false;
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked - X: " + e.getX() + ", Y: " + e.getY());
        LifeMatrix m = LifeMatrix.getInstance();

        int stato = m.toggleCell(e.getY() / m.getCellSize(), e.getX() / m.getCellSize());

        System.out.println("La cella e' ora " + ((stato == 1) ? ("Viva") : ("Morta")));
        revalidate();
        repaint();
    }

    public void mousePressed(MouseEvent e) {
        //System.out.println("Mouse Pressed");
    }

    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse Released");
    }

    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse Entered");
    }

    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse Exited");
    }
}
