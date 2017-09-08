package sample.ramya.com.exampleapp.constants;

/**
 * Created by Home on 27-01-2017.
 */

public class Constants {

    public static final long CONNECTION_TIME_OUT = 60;
    public static final long READ_TIME_OUT = 60;
    public static final long WRITE_TIME_OUT = 60;
    public static final String INTERNET_UNABLEABLE="Not connected to the internet. Please check your connection and try again.";
    public static final String SUCCESS="200";

    public class SharedPreferencesKeys {
        public static final String USERID = "userid";
        public static final String LOGIN_STATUS = "login_status";
    }

    public interface  RequestCodes {
        int ONCREATE_REQUEST_CODE = 5000;
    }
}
