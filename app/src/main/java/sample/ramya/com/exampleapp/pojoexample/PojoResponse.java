package sample.ramya.com.exampleapp.pojoexample;

/**
 * Created by elancer on 7/31/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class PojoResponse {

    @SerializedName("game_stat")
    @Expose
    private GameStat gameStat;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("enemies")
    @Expose
    private ArrayList<Enemy> enemies = null;

    public GameStat getGameStat() {
        return gameStat;
    }

    public void setGameStat(GameStat gameStat) {
        this.gameStat = gameStat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public String toString() {
        return "POJO "+enemies+" LOCATION : "+location+" GAME : "+gameStat+" . ";
//        ToStringBuilder.reflectionToString(this)
    }

}
