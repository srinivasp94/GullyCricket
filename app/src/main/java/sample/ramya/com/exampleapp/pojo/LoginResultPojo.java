package sample.ramya.com.exampleapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by sys on 6/14/2017.
 */

public class LoginResultPojo {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("user_email")
    @Expose
    public String userEmail;
    @SerializedName("user_mobile")
    @Expose
    public String userMobile;
    @SerializedName("user_status")
    @Expose
    public Object userStatus;
    @SerializedName("factory_id")
    @Expose
    public String factoryId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
