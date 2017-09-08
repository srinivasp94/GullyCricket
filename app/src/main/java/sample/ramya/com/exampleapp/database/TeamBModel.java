package sample.ramya.com.exampleapp.database;

import java.io.Serializable;

/**
 * Created by elancer on 7/26/2017.
 */

public class TeamBModel implements Serializable{
    private int idB;
    private String player_nameB;
    private String player_scoreB;
    private String player_teamB;
    private int isOut;
    private int isChecked;
    private String imagebytes;

    public TeamBModel() {
    }

    public TeamBModel(int idB, String player_nameB, String player_scoreB, String player_teamB, int isOut, int isChecked, String imagebytes) {
        this.idB = idB;
        this.player_nameB = player_nameB;
        this.player_scoreB = player_scoreB;
        this.player_teamB = player_teamB;
        this.isOut = isOut;
        this.isChecked = isChecked;
        this.imagebytes = imagebytes;
    }

    public int getIsOut() {
        return isOut;
    }

    public void setIsOut(int isOut) {
        this.isOut = isOut;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public String getImagebytes() {
        return imagebytes;
    }

    public void setImagebytes(String imagebytes) {
        this.imagebytes = imagebytes;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) {
        this.idB = idB;
    }

    public String getPlayer_nameB() {
        return player_nameB;
    }

    public void setPlayer_nameB(String player_nameB) {
        this.player_nameB = player_nameB;
    }

    public String getPlayer_scoreB() {
        return player_scoreB;
    }

    public void setPlayer_scoreB(String player_scoreB) {
        this.player_scoreB = player_scoreB;
    }

    public String getPlayer_teamB() {
        return player_teamB;
    }

    public void setPlayer_teamB(String player_teamB) {
        this.player_teamB = player_teamB;
    }
}
