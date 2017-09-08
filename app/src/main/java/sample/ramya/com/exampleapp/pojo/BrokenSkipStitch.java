package sample.ramya.com.exampleapp.pojo;

/**
 * Created by elancer on 6/14/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class BrokenSkipStitch {

    @SerializedName("tops")
    @Expose
    public List<Top> tops = null;
    @SerializedName("bottoms")
    @Expose
    public List<Bottom> bottoms = null;

    public List<Bottom> getBottoms() {
        return bottoms;
    }

    public List<Top> getTops() {

        return tops;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
