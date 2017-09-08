package sample.ramya.com.exampleapp.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CommonRequestPojo {
    private String requestparameters;


    private String requestname;

    public String getRequestparameters() {
        return requestparameters;
    }

    public void setRequestparameters(String requestparameters) {
        this.requestparameters = requestparameters;
    }


    public String getRequestname() {
        return requestname;
    }

    public void setRequestname(String requestname) {
        this.requestname = requestname;
    }

    @Override
    public String toString() {
        return "ClassPojo [requestparameters = " + requestparameters + ", requestname = " + requestname + "]";
    }
}



