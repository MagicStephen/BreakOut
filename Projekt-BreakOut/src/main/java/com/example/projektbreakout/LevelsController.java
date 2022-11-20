package com.example.projektbreakout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LevelsController {

    List<String>[] listofLevels;
    ArrayList<String> parsedLevel;
    ArrayList<Level> listofLevelsParsed;
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
        this.listofLevelsParsed = new ArrayList<>();

        int countLevels = 0;

        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/Levels/levels.txt"))) {

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

        for(List<String> level : listofLevels){
            listofLevelsParsed.add(new Level(level));
        }
    }

    @FXML
    void level1btnclick(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Level level1 = this.listofLevelsParsed.get(0);

        stage.setUserData(level1);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void level2btnclick(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Level level2 = this.listofLevelsParsed.get(1);

        stage.setUserData(level2);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void level3btnclick(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Level level3 = this.listofLevelsParsed.get(2);

        stage.setUserData(level3);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
