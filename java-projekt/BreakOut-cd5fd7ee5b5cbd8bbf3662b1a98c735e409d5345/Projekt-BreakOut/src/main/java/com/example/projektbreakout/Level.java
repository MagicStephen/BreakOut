package com.example.projektbreakout;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private String name;
    int rows;
    int columns;
    private String level = "";
    int maxScore ;
    public Level(List<String> level){
        this.name = level.get(0);
        this.rows = level.size()-1;
        this.columns = level.get(1).length();

        level.remove(0);

        for(String l : level){
            this.level += l + ",";
        }

        setMaxScore();
    }

    public void setMaxScore(){

        for(int i=0; i < this.level.length();i++){
            if(Character.isDigit(this.level.charAt(i))){
                this.maxScore += Integer.parseInt(String.valueOf(this.level.charAt(i)));
            }
            else
                continue;
        }
    }

    public String getLevel(){
        return this.level;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
