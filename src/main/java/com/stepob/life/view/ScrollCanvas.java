package com.stepob.life.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScrollCanvas extends JPanel implements MouseListener {

    private BufferedImage img = null;

    private int imgX = 0;
    private int imgY = 0;

    public ScrollCanvas(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        setPreferredSize(new Dimension(800, 600));

        addMouseListener(this);

        loadImage();

        revalidate();
        repaint();
    }

    private void loadImage() {

        ClassLoader classLoader = getClass().getClassLoader();
        File imageFile = new File(classLoader.getResource("sand-background.jpg").getFile());


        try {
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        setBackground(Color.BLACK);

        ((Graphics2D) g).drawImage(img,null, imgX, imgY );

        // disegno immagine

    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked - X: " + e.getX() + ", Y: " + e.getY());
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse Released");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse Entered");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Exited");
    }

    public void imgUp() {
        imgY--;
        revalidate();
        repaint();
    }

    public void imgDown() {
        imgY++;
        revalidate();
        repaint();
    }

    public void imgLeft() {
        imgX--;
        revalidate();
        repaint();
    }

    public void imgRight() {
        imgX++;
        revalidate();
        repaint();
    }

}
