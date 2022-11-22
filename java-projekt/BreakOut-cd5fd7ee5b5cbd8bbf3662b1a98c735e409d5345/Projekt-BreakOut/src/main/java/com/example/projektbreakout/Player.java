package com.example.projektbreakout;

import javafx.geometry.Point2D;

public class Player {

    int lives = 0;
    Paddle paddle;
    Game game;

    public Player(int lives,Game game) {
        this.lives = lives;
        this.game = game;
        this.paddle = new Paddle(new Point2D(game.getWidth()/2,30),100,20,this);
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void livesMinus(){
        this.lives  = this.lives - 1;
    }

    public Game getGame() {
        return game;
    }

    public int getLives() {
        return lives;
    }
}
