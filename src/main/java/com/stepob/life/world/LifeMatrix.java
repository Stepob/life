package com.stepob.life.world;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LifeMatrix {

    static {
        theInstance = new LifeMatrix();
    }

    private static LifeMatrix theInstance;

    private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

    public static final String GENERATION_NUMBER = "genNumber";

    private int genNumber = 0;

    private int cellRows = 60;
    private int cellColumns = 80;
    private int cellSize = 10;

    //partenza
    private int x = 0;
    private int y = 0;

    private int[][] matrix = new int[cellRows][cellColumns];

    public static LifeMatrix getInstance() {
        return theInstance;
    }

    private LifeMatrix() {
        initMatrix();
    }

    public void initMatrix() {

        genNumber = 0;

        Random r = new Random();
        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                matrix[i][j] = r.nextInt(15);
            }
        }
        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                if (matrix[i][j] != 1) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

    public void clearMatrix() {

        genNumber = 0;

        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public int toggleCell(int x, int y) {
        if (matrix[x][y] == 1) {
            matrix[x][y] = 0;
        } else {
            matrix[x][y] = 1;
        }
        return matrix[x][y];
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getGenNumber() {
        return genNumber;
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


                int k = matrix[i][fixColumnIndex(j + 1)] +
                        matrix[i][fixColumnIndex(j - 1)] +
                        matrix[fixRowIndex(i + 1)][fixColumnIndex(j - 1)] +
                        matrix[fixRowIndex(i + 1)][j] +
                        matrix[fixRowIndex(i + 1)][fixColumnIndex(j + 1)] +
                        matrix[fixRowIndex(i - 1)][fixColumnIndex(j - 1)] +
                        matrix[fixRowIndex(i - 1)][j] +
                        matrix[fixRowIndex(i - 1)][fixColumnIndex(j + 1)];


                if (matrix[i][j] == 1) {
                    matrix[i][j] = regoleCellaViva(k);
                } else {
                    matrix[i][j] = regoleCellaMorta(k);
                }
                if (k == 3) {
                    matrix[i][j] = 1;
                }
            }
        }

        notifyListeners(
                this,
                GENERATION_NUMBER,
                this.genNumber,
                this.genNumber++);
    }

    private int fixRowIndex(int index) {
        return (index + cellRows) % cellRows;
    }

    private int fixColumnIndex(int index) {
        return (index + cellColumns) % cellColumns;
    }

    private int regoleCellaViva(int k) {
        if (k < 2) {
            return 0;
        } else if (k == 2) {
            return 1;
        } else if (k == 3) {
            return 1;
        }
        return 0;
    }

    private int regoleCellaMorta(int k) {
        if (k == 3) {
            return 1;
        }
        return 0;
    }

    public void paint(Graphics2D g2) {

        for (int i = 0; i < cellRows; i++) {
            for (int j = 0; j < cellColumns; j++) {
                if (matrix[i][j] == 1) {
                    g2.setPaint(Color.BLUE);

                    g2.fill(new Rectangle2D.Float(j * cellSize, i * cellSize, cellSize, cellSize));

                    g2.setPaint(Color.black);
                    g2.draw(new Rectangle2D.Float(j * cellSize, i * cellSize, cellSize, cellSize));
                }
            }
        }
    }

    private void notifyListeners(Object object, String property, int oldValue, int newValue) {
        for (PropertyChangeListener name : listener) {
            name.propertyChange(new PropertyChangeEvent(object, property, oldValue, newValue));
        }
    }

    public void addChangeListener(PropertyChangeListener newListener) {
        listener.add(newListener);
    }

}
