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

public class GameWinController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button nextLevel;

    @FXML
    private Button returnback;

    @FXML
    private Text winScore;
    @FXML
    private Text scorewin;

    private Level nextLv;
    private Level lastLv;

    @FXML
    void nextLevelbtnclick(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        root = loader.load();
        if(lastLv != null) {
            GameController controller = loader.getController();
            controller.setLevel(nextLv, lastLv, null);
        }
        else{
            GameController controller = loader.getController();
            controller.setLevel(nextLv, null, null);
        }
        scene = new Scene((Parent)root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void returnBtnClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    void setLevel(Level nextLevel,Level lastLevel){
        this.nextLv = nextLevel;
        this.lastLv = lastLevel;
    }

    void setScore(int score){
        this.winScore.setText(String.valueOf(score));
    }
}
