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
    private Canvas gameCanvas;
    @FXML
    private Button startGame;
    private long lastTime;

    private Level level;

    private Level nextLevel;
    private Level lastLevel;
    Game game;

    @FXML
    void startBtnClick(ActionEvent event) throws IOException {
        startGame.setDisable(true);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        this.game = new Game((int)gameCanvas.getWidth(),(int)gameCanvas.getHeight(),this.level);

        Scene s1 = (Scene) gameCanvas.getScene();

        AnimationTimer animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {

                    if (lastTime > 0 && game.getBrickCount() > 0 && game.getPlayer().getLives() > 0) {
                            double deltaT = (now - lastTime) / 1e9;
                            game.simulate(deltaT);
                    }
                    else if (level.getMaxScore() == game.getPlayerScore()){
                        this.stop();
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("gameWin-view.fxml"));

                            root = loader.load();

                            GameWinController controller = loader.getController();
                            controller.setScore(game.getPlayerScore());
                            controller.setLevel(nextLevel,lastLevel);

                            if(nextLevel == null && lastLevel == null){
                                controller.disableNextLevel();
                            }

                            scene = new Scene((Parent)root);
                            stage.setScene(scene);
                            stage.show();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else if(game.getPlayer().getLives() == 0){
                        this.stop();

                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("gameOver-view.fxml"));

                            root = loader.load();

                            GameOverController controller = loader.getController();
                            controller.setScore(game.getPlayerScore());
                            controller.setLevel(level,nextLevel,lastLevel);

                            scene = new Scene((Parent)root);
                            stage.setScene(scene);
                            stage.show();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
    void setLevel(Level level,Level nextLevel,Level lastLevel){
        this.level = level;
        this.nextLevel = nextLevel;
        this.lastLevel = lastLevel;
    }
}


