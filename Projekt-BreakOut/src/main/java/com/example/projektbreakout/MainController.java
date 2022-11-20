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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane background;


    private long lastTime;

    @FXML
    private Canvas gameCanvas;
    @FXML
    private ImageView image;
    @FXML
    private Button newGame;

    private ArrayList<Level> levels;

    @FXML
    void newGameBtnClick(ActionEvent event)  throws IOException {
        image.setVisible(false);
        background.setVisible(false);
        newGame.setVisible(false);

        root = FXMLLoader.load(getClass().getResource("levels-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        this.levels = (ArrayList<Level>) stage.getUserData();

        stage.setUserData(this.levels);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}