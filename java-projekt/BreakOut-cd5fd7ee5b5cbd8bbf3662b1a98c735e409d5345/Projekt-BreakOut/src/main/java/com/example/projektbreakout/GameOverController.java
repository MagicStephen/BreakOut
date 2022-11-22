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
    private Button end;
    @FXML
    private Text finalScore1;
    private int score;

    @FXML
    void endbtnClick(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("levels-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    void setScore(int score){
        this.finalScore1.setText(this.finalScore1.getText() + " " + String.valueOf(score));
    }
}
