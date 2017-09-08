package sample.ramya.com.exampleapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Home on 30-01-2017.
 */

public class RegistrationKeyVo {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("device_token")
    @Expose
    public String deviceToken;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
