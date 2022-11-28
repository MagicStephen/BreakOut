package com.example.projektbreakout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button changeLevel;
    @FXML
    private Text finalScore1;
    @FXML
    private Button retry;
    @FXML
    private Button returnMain;
    private Level level;
    private Level nextLevel;
    private Level lastLevel;

    @FXML
    private Text score;
    @FXML
    void changebtnClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("levels-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void retrybtnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        root = loader.load();

        GameController controller = loader.getController();
        controller.setLevel(this.level,this.nextLevel,this.lastLevel);

        scene = new Scene((Parent)root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void returnMainbtnclick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    void setScore(int score){
        this.score.setText(String.valueOf(score));
    }
    void setLevel(Level level,Level nextLevel,Level lastLevel){
        this.level = level;
        this.nextLevel = nextLevel;
        this.lastLevel = lastLevel;
    }
}
