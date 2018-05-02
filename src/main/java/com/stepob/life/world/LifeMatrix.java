package com.stepob.life.world;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class LifeMatrix {
    private int cellRows = 60;
    private int cellColumns = 80;
    private int cellSize = 10;

    private int[][] matrix = new int[cellRows][cellColumns];

    public LifeMatrix() {
        Random r = new Random();
        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                matrix[i][j] = r.nextInt(2);
            }
        }
    }

    public void paint(Graphics2D g2) {
        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                if (matrix[i][j] == 1) {
                    g2.setPaint(Color.BLUE);
                    g2.draw(new Rectangle2D.Double(j * cellSize, i * cellSize, cellSize, cellSize));
                }
            }
        }
    }
}
