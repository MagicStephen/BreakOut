package com.example.projektbreakout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LevelsController {

    ArrayList<Level> levels;
    List<String>[] listofLevels;
    ArrayList<String> parsedLevel;
    String line;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button level1;
    @FXML
    private Button level2;
    @FXML
    private Button level3;

    public void initialize(){

            this.listofLevels = new List[3];
            this.parsedLevel = new ArrayList<>();
            this.levels = new ArrayList<>();

            int countLevels = 0;

        InputStreamReader reader =  new InputStreamReader(this.getClass().getResourceAsStream("/Levels/levels.txt"));

        try (BufferedReader br = new BufferedReader(reader)) {

                while((this.line = br.readLine()) != null){
                    if(this.line.isEmpty()){
                        this.listofLevels[countLevels] = this.parsedLevel;
                        countLevels++;
                        this.parsedLevel = new ArrayList<>();
                        continue;
                    }
                    this.parsedLevel.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int i=0; i < listofLevels.length;i++){
                levels.add(new Level(listofLevels[i]));
            }
    }

    @FXML
    void level1btnclick(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("game-view.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        root = loader.load();

        GameController controller = loader.getController();
        controller.setLevel(levels.get(0),levels.get(1),levels.get(2));

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void level2btnclick(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("game-view.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        root = loader.load();

        GameController controller = loader.getController();
        controller.setLevel(levels.get(1),levels.get(2),null);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void level3btnclick(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("game-view.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        root = loader.load();

        GameController controller = loader.getController();
        controller.setLevel(levels.get(2),null,null);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
