package sample.ramya.com.exampleapp.database;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import sample.ramya.com.exampleapp.pojo.Model_Result;

/**
 * Created by elancer on 6/30/2017.
 */

public class ModelPlayer implements Serializable {

    private int id;
    private String player_name;
    private String player_score;
    private String player_team;
    private String imagebytes;
    private int isOut;
    private int isChecked;

    public ModelPlayer() {
    }

    public ModelPlayer(int id, String player_name, String player_score, String player_team, int isOut, int isChecked, String imagebytes) {
        this.id = id;
        this.player_name = player_name;
        this.player_score = player_score;
        this.player_team = player_team;
        this.imagebytes = imagebytes;
        this.isChecked = isChecked;
        this.isOut = isOut;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }


    public String getPlayer_score() {
        return player_score;
    }

    public void setPlayer_score(String player_score) {
        this.player_score = player_score;
    }

    public String getPlayer_team() {
        return player_team;
    }

    public void setPlayer_team(String player_team) {
        this.player_team = player_team;
    }



    public ModelPlayer(Parcel in) {
        super();
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        String[] result = new String[5];
        in.readStringArray(result);

        this.id = Integer.parseInt(result[0]);
        this.player_name = result[1];
        this.player_score = result[2];
        this.player_team = result[3];
        this.isOut = Integer.parseInt(result[4]);
        this.isChecked = Integer.parseInt(result[5]);
        this.imagebytes = result[6];
    }


    public static final Parcelable.Creator<ModelPlayer> CREATOR = new Parcelable.Creator<ModelPlayer>() {
        public ModelPlayer createFromParcel(Parcel in) {
            return new ModelPlayer(in);
        }

        public ModelPlayer[] newArray(int size) {

            return new ModelPlayer[size];
        }
    };

}
