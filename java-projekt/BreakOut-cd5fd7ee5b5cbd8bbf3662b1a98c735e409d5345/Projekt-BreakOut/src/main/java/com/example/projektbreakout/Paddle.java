package com.example.projektbreakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle {

    private Point2D position;
    private int width;
    private int height;
    Player player;

    public Paddle(Point2D position, int width, int height, Player player) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.player = player;
    }

    public void draw(GraphicsContext gc, float pos){

        if (!(this.position.getX()  + this.width + (double)pos > this.player.getGame().getWidth() - 10) && !(this.position.getX() + (double)pos  < 10.0)) {

            this.position = new Point2D(this.position.getX()+(double)pos, this.position.getY());

            Point2D paddleCanvas = this.player.getGame().getCanvasPoint(new Point2D(this.position.getX(), this.position.getY()));

            gc.setFill(Color.BLACK);
            gc.fillRect(paddleCanvas.getX(), paddleCanvas.getY(), width, height);
        }
    }

    public Point2D getPosition(){
        return this.position;
    }

    public int getWidthPaddle(){
        return this.width;
    }

    public int getHeightPaddle(){
        return this.height;
    }
}
