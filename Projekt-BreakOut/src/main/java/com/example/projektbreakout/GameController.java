package com.example.projektbreakout;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;

public class GameController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Text finalScore1;
    @FXML
    private Canvas gameCanvas;
    @FXML
    private Button startGame;
    private long lastTime;
    Game game;

    @FXML
    void startBtnClick(ActionEvent event) throws IOException {
        startGame.setDisable(true);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Level level = (Level) stage.getUserData();

        this.game = new Game((int)gameCanvas.getWidth(),(int)gameCanvas.getHeight(),level);

        Scene s1 = (Scene) gameCanvas.getScene();

        AnimationTimer animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {

                    if (lastTime > 0 && game.getBrickCount() > 0 && game.getPlayer().getLives() > 0) {
                            double deltaT = (now - lastTime) / 1e9;
                            game.simulate(deltaT);
                    }
                    else if (game.getBrickCount() == 0){
                        this.stop();
                        try {
                            root = FXMLLoader.load(getClass().getResource("gameWin-view.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(game.getPlayerScore());

                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    else if(game.getPlayer().getLives() == 0){
                        this.stop();
                        try {
                            root = FXMLLoader.load(getClass().getResource("gameOver-view.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(game.getPlayerScore());

                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    s1.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent keyEvent) {
                            if (keyEvent.getCode() == RIGHT) {
                                game.draw(gameCanvas.getGraphicsContext2D(), 10);
                            }
                            if (keyEvent.getCode() == LEFT) {
                                game.draw(gameCanvas.getGraphicsContext2D(), -10);
                            }
                        }
                    });
                    game.draw(gameCanvas.getGraphicsContext2D(), 0);

                    lastTime = now;
                }
        };

            animationTimer.start();
    }

    @FXML
    private Button returnback;

    @FXML
    void returnBtnClick(ActionEvent event){
        System.out.println(game.getPlayerScore());
        System.exit(1);
    }

    @FXML
    private Button end;

    @FXML
    void endbtnClick(ActionEvent event) {
        System.exit(1);
    }
}


