package sample.ramya.com.exampleapp.constants;

import sample.ramya.com.exampleapp.enums.Environment;

/**
 * Created by Home on 27-01-2017.
 */

public class ServiceUrls {

    public static Environment CURRENT_ENVIRONMENT = Environment.DEV;


    public interface RequestParams{


        String PASSWORD = "password";
        String USERNAME = "username";
    }

    public interface RequestNames {
        String LOGIN_REQUEST = "getUserDetails";
        String GET_ENDLINEQUALITY_FORM_FIELDS = "getEndlineQualityFormFields";
    }
}
