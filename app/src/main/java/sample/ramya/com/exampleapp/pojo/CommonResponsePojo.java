package sample.ramya.com.exampleapp.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CommonResponsePojo
{

    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("result")
    @Expose
    public Object result;
    @SerializedName("requestname")
    @Expose
    public String requestname;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}



