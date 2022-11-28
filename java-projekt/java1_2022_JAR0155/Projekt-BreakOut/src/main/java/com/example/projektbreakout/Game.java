package com.example.projektbreakout;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Game {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int width;
    private int height;
    private Player player;
    private Ball ball;
    private int brickCount;
    private BrickMap brickMap;
    ArrayList<Image> hearts;
    private int playerScore = 0;
    private Level level;
    GameController controller;

    public Game(int width, int height,Level level) {
        this.width = width;
        this.height = height;
        this.player = new Player(3,this);
        this.ball = new Ball(20,20,new Point2D(this.player.getPaddle().getPosition().getX() + this.player.getPaddle().getWidthPaddle()/2,this.player.getPaddle().getPosition().getY()+25),new Point2D(100,100), this);
        this.brickCount = level.getMaxScore();
        this.brickMap = new BrickMap(level,this);
        this.hearts = new ArrayList<>();
        this.level = level;

        URL url = getClass().getResource("/Images/heart.png");

        for(int i=0; i < this.player.getLives();i++){
            this.hearts.add(new Image(url.toExternalForm()));
        }
    }

    public void simulate(double deltaT){

            this.ball.move(deltaT);
            CheckPaddleBallCollision();
            CheckBrickBallCollision();
    }

    private void CheckPaddleBallCollision(){
        if(new Rectangle(this.ball.getPosition().getX(), this.ball.getPosition().getY(), this.ball.getWidth(), this.ball.getHeight()).intersects(this.player.getPaddle().getPosition().getX(), this.player.getPaddle().getPosition().getY(), this.player.getPaddle().getWidthPaddle(), this.player.getPaddle().getHeightPaddle()))
        {
            this.ball.changeDirection((int)this.ball.getSpeed().getX(), (int)this.ball.getSpeed().getY()*-1);
        }
    }

    private void CheckBrickBallCollision(){

        for(int i=0;i<this.brickMap.getBrickMap().length;i++){
            for(int j=0; j< this.brickMap.getBrickMap()[i].length;j++) {
                if (this.brickCount > 0) {
                    if (this.brickMap.getBrickMap()[i][j] > 0) {

                        Rectangle brickRect = new Rectangle((j * this.brickMap.getBrickCanvasPosition().getX() + 20), this.height - (i * this.brickMap.getBrickCanvasPosition().getY() + 20) - 30, this.brickMap.getBrickWidth(), this.brickMap.getBrickHeight());
                        Rectangle ballRect = new Rectangle(this.ball.getPosition().getX(), this.ball.getPosition().getY(), this.ball.getWidth(), this.ball.getHeight());

                        if (ballRect.intersects(brickRect.getX(), brickRect.getY(), brickRect.getWidth(), brickRect.getHeight())) {
                            this.brickMap.setBrickValue(-1, i, j);
                            brickCount--;
                            this.playerScore++;

                            //Right of brick
                            if (this.ball.getPosition().getX() + this.ball.getWidth() / 2 >= brickRect.getX() + (brickRect.getWidth())) {
                                this.ball.changeDirection((int) this.ball.getSpeed().getX() * -1, (int) this.ball.getSpeed().getY());
                            }
                            //Left of brick
                            else if (this.ball.getPosition().getX() <= brickRect.getX()) {
                                this.ball.changeDirection((int) this.ball.getSpeed().getX() * -1, (int) this.ball.getSpeed().getY());
                            }
                            // when ball hits top or bottom of brick
                            else {
                                this.ball.changeDirection((int) this.ball.getSpeed().getX(), (int) this.ball.getSpeed().getY() * -1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void draw(GraphicsContext gc, float pos){

        gc.clearRect(0,0,this.width, this.height);

        for(int i=0; i< hearts.size();i++){
            gc.drawImage(hearts.get(i),(hearts.get(i).getWidth()/22 * i) ,this.getHeight() - hearts.get(i).getHeight()/22,hearts.get(i).getHeight()/22, hearts.get(i).getWidth()/22);
        }

        this.player.getPaddle().draw(gc,pos);
        this.ball.draw(gc);
        this.brickMap.draw(gc);
    }

    public int getBrickCount() {
        return brickCount;
    }

    public ArrayList<Image> getHearts() {
        return hearts;
    }

    public Point2D getCanvasPoint(Point2D p){

        return new Point2D(p.getX(), height - p.getY());
    }

    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
