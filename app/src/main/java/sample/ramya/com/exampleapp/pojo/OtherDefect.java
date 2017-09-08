package sample.ramya.com.exampleapp.pojo;

/**
 * Created by elancer on 6/14/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OtherDefect {

    @SerializedName("defect_label")
    @Expose
    private String defectLabel;
    @SerializedName("defect_type")
    @Expose
    private String defectType;
    @SerializedName("defect_input_type")
    @Expose
    private String defectInputType;

    public OtherDefect(String defectLabel) {
        this.defectLabel = defectLabel;
    }

    public String getDefectLabel() {
        return defectLabel;
    }

    public void setDefectLabel(String defectLabel) {
        this.defectLabel = defectLabel;
    }

    public String getDefectType() {
        return defectType;
    }

    public void setDefectType(String defectType) {
        this.defectType = defectType;
    }

    public String getDefectInputType() {
        return defectInputType;
    }

    public void setDefectInputType(String defectInputType) {
        this.defectInputType = defectInputType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}