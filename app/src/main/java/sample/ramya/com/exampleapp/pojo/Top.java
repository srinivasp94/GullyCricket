package sample.ramya.com.exampleapp.pojo;

/**
 * Created by elancer on 6/14/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Top {

    @SerializedName("defect_label")
    @Expose
    public String defectLabel;
    @SerializedName("defect_type")
    @Expose
    public String defectType;
    @SerializedName("defect_input_type")
    @Expose
    public String defectInputType;

    public String getDefectLabel() {
        return defectLabel;
    }

    public String getDefectType() {
        return defectType;
    }

    public String getDefectInputType() {
        return defectInputType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
