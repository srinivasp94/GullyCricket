package sample.ramya.com.exampleapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import java.io.Serializable;
import java.util.HashMap;

import sample.ramya.com.exampleapp.commonutills.Common;
import sample.ramya.com.exampleapp.constants.Constants;
import sample.ramya.com.exampleapp.constants.ServiceUrls;
import sample.ramya.com.exampleapp.network.RetrofitRequester;
import sample.ramya.com.exampleapp.network.RetrofitResponseListener;
import sample.ramya.com.exampleapp.pojo.CommonResponsePojo;
import sample.ramya.com.exampleapp.pojo.LoginResultPojo;


public class LoginScreenActivity extends AppCompatActivity implements RetrofitResponseListener {
    Button login;
    EditText userTxt, passwordTxt;
    ImageView showHidePwd;
    String User, Password;
    LoginResultPojo loginResultPojo;
    public static boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        userTxt = (EditText) findViewById(R.id.edt_username);
        passwordTxt = (EditText) findViewById(R.id.edt_password);
        showHidePwd = (ImageView) findViewById(R.id.showandhide_password);

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User = userTxt.getText().toString();
                Password = passwordTxt.getText().toString();
                if (!User.isEmpty() && !Password.isEmpty()) {
//                    callLoginApi("coordinator@gokaldas.com","coordinator@gokaldas.com");
                    callLoginApi(User, Password);
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreenActivity.this);
                    builder.setTitle("Message");
                    builder.setCancelable(false);
                    builder.setMessage("Enter Valid User Name and password");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    final AlertDialog alert = builder.create();
                    alert.show();
                }


            }
        });
        showHidePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("1@@@@" , String.valueOf(isClicked));
                if (!isClicked) {
                    Log.v("2@@@@" , String.valueOf(isClicked));
                    showHidePwd.setColorFilter(ContextCompat.getColor(LoginScreenActivity.this, R.color.darklightblack), android.graphics.PorterDuff.Mode.MULTIPLY);
                    isClicked=true;
                    passwordTxt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordTxt.setSelection(passwordTxt.length());
                }
                else {
                    Log.v("3@@@@" , String.valueOf(isClicked));
                    showHidePwd.setColorFilter(ContextCompat.getColor(LoginScreenActivity.this, R.color.textcolor), android.graphics.PorterDuff.Mode.MULTIPLY);
                    isClicked=false;
                    passwordTxt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordTxt.setSelection(passwordTxt.length());
                }
            }
        });
    }

    void callLoginApi(String username, String password) {
        HashMap<String, String> params = new HashMap();
        params.put(ServiceUrls.RequestParams.USERNAME, username);
        params.put(ServiceUrls.RequestParams.PASSWORD, password);

        new RetrofitRequester(this).sendStringRequest(ServiceUrls.RequestNames.LOGIN_REQUEST, params, true);


    }

    @Override
    public void onResponseSuccess(CommonResponsePojo commonResponsePojo, HashMap<String, String> requestParams, int requestId) {
        if (!commonResponsePojo.code.equalsIgnoreCase(Constants.SUCCESS)) {

            // Common.customToast(this, commonResponsePojo.message);
            Log.d("Data from szerver", commonResponsePojo.toString());
            return;

        }

        LoginResultPojo loginRequestVo = Common.getSpecificDataObject(commonResponsePojo.result, LoginResultPojo.class);
        Common.customToast(this, loginRequestVo.userName);
        String user_name = loginRequestVo.userName;

        Log.d("-----username ", user_name);
        String role = loginRequestVo.role;

        Intent intent = new Intent(LoginScreenActivity.this, EndLineQualityCheck.class);
        /*intent.putExtra("nav_list", (Serializable) loginRequestVo.leftnav);
        intent.putExtra("User_name",user_name);
        intent.putExtra("role",role);*/
        startActivity(intent);
    }

}
