package com.example.projektbreakout;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private String name;
    int rows;
    int columns;
    private String level = "";
    int brickCount ;
    public Level(List<String> level){
        this.name = level.get(0);
        this.rows = level.size()-1;
        this.columns = level.get(1).length();

        this.brickCount = this.rows * this.columns;

        level.remove(0);

        for(String l : level){
            this.level += l + ",";
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

    public int getBrickCount() {
        return brickCount;
    }
}
