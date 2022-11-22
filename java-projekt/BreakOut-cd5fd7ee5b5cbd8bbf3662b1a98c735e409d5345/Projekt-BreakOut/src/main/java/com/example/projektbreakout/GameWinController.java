package com.example.projektbreakout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameWinController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button returnback;

    @FXML
    void returnBtnClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("levels-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
