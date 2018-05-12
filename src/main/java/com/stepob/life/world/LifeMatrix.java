package com.stepob.life.world;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class LifeMatrix {
    private int cellRows = 60;
    private int cellColumns = 80;
    private int cellSize = 10;

    private int x = 0;
    private int y = 0;

    private int[][] matrix = new int[cellRows][cellColumns];

    public LifeMatrix() {
        simpleInitMatrix(x, y);
    }

    private void initMatrix() {
        Random r = new Random();
        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                matrix[i][j] = r.nextInt(2);
            }
        }
    }

    private void simpleInitMatrix(int x, int y) {
        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                matrix[i][j] = 0;
            }
        }
        matrix[x][y] = 1;
    }

    public void nextGen() {
        x = x + 1;
        y = y + 1;
        simpleInitMatrix(x, y);
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
