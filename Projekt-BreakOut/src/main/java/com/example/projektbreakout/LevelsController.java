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
import java.util.ArrayList;
import java.util.List;

public class LevelsController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button level1;

    @FXML
    private Button level2;

    @FXML
    private Button level3;


    @FXML
    void level1btnclick(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Level level1 = ((ArrayList<Level>) stage.getUserData()).get(0);

        stage.setUserData(level1);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void level2btnclick(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Level level2 = ((ArrayList<Level>) stage.getUserData()).get(1);

        stage.setUserData(level2);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void level3btnclick(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Level level3 = ((ArrayList<Level>) stage.getUserData()).get(2);

        stage.setUserData(level3);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
