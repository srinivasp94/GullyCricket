package sample.ramya.com.exampleapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Home on 30-01-2017.
 */
public class RegistrationResponseVo {
    @SerializedName("code")
    @Expose
    public long code;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("description")
    @Expose
    public String description;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
