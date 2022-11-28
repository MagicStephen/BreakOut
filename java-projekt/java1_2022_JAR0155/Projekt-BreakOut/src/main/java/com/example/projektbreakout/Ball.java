package com.example.projektbreakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ball{
    private int width;
    private int height;
    Point2D position;
    Point2D speed;
    Game game;

    public Ball(int width, int height, Point2D position,Point2D speed, Game game) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.speed = speed;
        this.game = game;
    }

    public void changeDirection(int x, int y) {
        speed = new Point2D(x, y);
    }

    private void wallBounce() {
        if(position.getY() - height/2 < 0) {
           game.getPlayer().livesMinus();
           game.getHearts().remove(0);
           reset();
        }
        if(position.getY() + height/2 > game.getHeight()) {
            changeDirection((int)speed.getX(), (int)speed.getY()*-1);
        }
        if(position.getX() - width/2 < 0){
            changeDirection((int)speed.getX()*-1, (int)speed.getY());
        }
        if(position.getX() + width/2 > game.getWidth()){
            changeDirection((int)speed.getX()*-1, (int)speed.getY());
        }
    }

    private void reset(){
        this.position = new Point2D(this.game.getPlayer().getPaddle().getPosition().getX() + this.game.getPlayer().getPaddle().getWidthPaddle()/2,this.game.getPlayer().getPaddle().getPosition().getY()+25);
    }

    public void move(double deltaT){
        wallBounce();

        this.position = position.add(speed.multiply(deltaT));
    }

    public void draw(GraphicsContext gc){
        gc.save();

        Point2D ballCanvas = game.getCanvasPoint(new Point2D(position.getX() - width/2, position.getY() + height/2));

        gc.setFill(Color.BLACK);
        gc.fillRect(ballCanvas.getX(),ballCanvas.getY(), width, height);

        gc.restore();
    }

    public Point2D getPosition() {
        return this.position;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Point2D getSpeed() {
        return speed;
    }
}
