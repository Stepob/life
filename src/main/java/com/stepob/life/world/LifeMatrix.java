package com.stepob.life.world;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class LifeMatrix {
    private int cellRows = 60;
    private int cellColumns = 80;
    private int cellSize = 10;

    //partenza
    private int x = 0;
    private int y = 0;

    private int[][] matrix = new int[cellRows][cellColumns];

    /*public LifeMatrix() {
        simpleInitMatrix(x, y);
    }*/

    public LifeMatrix() {
        initMatrix();
    }

    private void initMatrix() {
        Random r = new Random();
        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                matrix[i][j] = r.nextInt(5);
            }
        }
    }

   /*  private void simpleInitMatrix(int x, int y) {
        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                matrix[i][j] = 0;
            }
        }
        matrix[x][y] = 1;
    }

    /* public void nextGen() {
        x = x + 1;
        y = y + 1;
        simpleInitMatrix(x, y);
    }*/

    public void nextGen2() {
        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {

                if (i != 0) {
                    if (i != 59) {
                        if (j != 0) {
                            if (j != 79) {


                                int k = matrix[i][(s+
                                        matrix[i][j - 1] +
                                        matrix[i + 1][(j - 1 + cellColumns) % cellColumns] +
                                        matrix[i + 1][j] +
                                        matrix[i + 1][j - 1] +
                                        matrix[fixRowIndex(i - 1)][j - 1] +
                                        matrix[i - 1][j] +
                                        matrix[i - 1][j + 1];


                                if (matrix[i][j] == 1) {


                                    matrix[i][j] = regoleCellaViva(k);


                                } else (matrix[i][j] != 1) {

                                    System.out.print(k2);
                                    if (k2 == 3) {
                                        matrix[i][j] = 1;
                                    }
                                }
                            }
                        }
                    }
                }


            }
        }
    }

    private int fixRowIndex(int index) {
        return index % cellRows;
    }


    private int regoleCellaViva(int k) {
        System.out.print(k);
        if (k < 2) {
            return 2;
        } else if (k == 2) {
            return 1;
        } else if (k == 3) {
            return 1;
        }
        return 2;
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
