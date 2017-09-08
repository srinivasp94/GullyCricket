package sample.ramya.com.exampleapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by elancer on 6/14/2017.
 */

public class Model_Result {
    @SerializedName("other_defects")
    @Expose
    private List<OtherDefect> otherDefects = null;
    @SerializedName("other_construction_defects")
    @Expose
    private List<OtherConstructionDefect> otherConstructionDefects = null;
    @SerializedName("broken_skip_stitch")
    @Expose
    private BrokenSkipStitch brokenSkipStitch;

    public Model_Result(List<OtherDefect> otherDefects, List<OtherConstructionDefect> otherConstructionDefects, BrokenSkipStitch brokenSkipStitch) {
        this.otherDefects = otherDefects;
        this.otherConstructionDefects = otherConstructionDefects;
        this.brokenSkipStitch = brokenSkipStitch;
    }

    public Model_Result() {

    }

    public List<OtherDefect> getOtherDefects() {
        return otherDefects;
    }

    public void setOtherDefects(List<OtherDefect> otherDefects) {
        this.otherDefects = otherDefects;
    }

    public List<OtherConstructionDefect> getOtherConstructionDefects() {
        return otherConstructionDefects;
    }

    public void setOtherConstructionDefects(List<OtherConstructionDefect> otherConstructionDefects) {
        this.otherConstructionDefects = otherConstructionDefects;
    }

    public BrokenSkipStitch getBrokenSkipStitch() {
        return brokenSkipStitch;
    }

    public void setBrokenSkipStitch(BrokenSkipStitch brokenSkipStitch) {
        this.brokenSkipStitch = brokenSkipStitch;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
