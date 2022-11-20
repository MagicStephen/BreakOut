package com.example.projektbreakout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    List<String>[] listofLevels;
    ArrayList<String> parsedLevel;
    ArrayList<Level> listofLevelsParsed;
    String line;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));

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

        stage.setUserData(listofLevelsParsed);

        Scene scene = new Scene(root);
        stage.setTitle("Breakout");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}