package sample.ramya.com.exampleapp.pojoexample;

/**
 * Created by elancer on 7/31/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class GameStat {

    @SerializedName("now_playing")
    @Expose
    private String nowPlaying;
    @SerializedName("earned")
    @Expose
    private String earned;

    public String getNowPlaying() {
        return nowPlaying;
    }

    public void setNowPlaying(String nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

    public String getEarned() {
        return earned;
    }

    public void setEarned(String earned) {
        this.earned = earned;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}