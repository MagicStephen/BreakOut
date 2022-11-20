package com.example.projektbreakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BrickMap {

    private int brickMap[][];
    int brickWidth = 30;
    int brickHeight = 30;

    Point2D brickCanvasPosition;
    Game game;

    public BrickMap(Level level, Game game) {
        this.game = game;

        brickMap = new int[level.getRows()][level.getColumns()];
        String[] rows = level.getLevel().split(",");

        for (int j = 0; j < rows.length; j++) {
            String currentLine = rows[j];

            for (int i = 0; i < currentLine.length(); i++) {

                brickMap[j][i] =  Character.getNumericValue(currentLine.charAt(i));
            }
        }

        this.brickCanvasPosition = game.getCanvasPoint(new Point2D(brickWidth + 10, game.getHeight()-brickHeight-10));
    }

    public void draw(GraphicsContext g)
    {
        g.save();

        for(int i = 0; i<brickMap.length; i++)
        {
            for(int j =0; j<brickMap[i].length; j++)
            {
                if(brickMap[i][j] > 0)
                {
                    if(brickMap[i][j] == 1) {
                        g.setFill(Color.BLUE);
                    }
                    if(brickMap[i][j] == 2) {
                        g.setFill(Color.GREEN);
                    }
                    if(brickMap[i][j] == 3) {
                        g.setFill(Color.YELLOW);
                    }
                    if(brickMap[i][j] == 4) {
                        g.setFill(Color.ORANGE);
                    }
                    if(brickMap[i][j] == 5) {
                        g.setFill(Color.RED);
                    }
                    g.fillRect(j * brickCanvasPosition.getX() + 20, i * brickCanvasPosition.getY() + 20, brickWidth, brickHeight);
                }
            }
        }
        g.restore();
    }

    public void setBrickValue(int value, int row, int col)
    {
        brickMap[row][col] = brickMap[row][col] + value;
    }

    public int[][] getBrickMap() {
        return brickMap;
    }

    public int getBrickHeight() {
        return brickHeight;
    }

    public int getBrickWidth() {
        return brickWidth;
    }

    public Point2D getBrickCanvasPosition() {
        return brickCanvasPosition;
    }
}
