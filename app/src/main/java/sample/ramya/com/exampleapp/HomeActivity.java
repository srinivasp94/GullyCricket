package sample.ramya.com.exampleapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;

import sample.ramya.com.exampleapp.application.TATX;
import sample.ramya.com.exampleapp.commonutills.Common;
import sample.ramya.com.exampleapp.constants.Constants;
import sample.ramya.com.exampleapp.constants.ServiceUrls;
import sample.ramya.com.exampleapp.customviews.ValueSelector;
import sample.ramya.com.exampleapp.network.RetrofitRequester;
import sample.ramya.com.exampleapp.network.RetrofitResponseListener;
import sample.ramya.com.exampleapp.pojo.CommonResponsePojo;
import sample.ramya.com.exampleapp.pojo.LoginResultPojo;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,RetrofitResponseListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final ValueSelector valueSelector = (ValueSelector) findViewById(R.id.valueSelector);
        valueSelector.setMinValue(0);
        valueSelector.setMaxValue(100);

        callLoginApi("coordinator@gokaldas.com","coordinator@gokaldas.com");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_scorecard) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResponseSuccess(CommonResponsePojo commonResponsePojo, HashMap<String, String> requestParams, int requestId) {
        if (commonResponsePojo.code != Constants.SUCCESS) {

            Common.customToast(this, commonResponsePojo.message);
            return;

        }

        LoginResultPojo loginRequestVo =Common.getSpecificDataObject(commonResponsePojo.result,LoginResultPojo.class);
        Common.customToast(this, loginRequestVo.userName);
    }


    void  callLoginApi(String username,String password){
        HashMap<String, String> params = new HashMap();
        params.put(ServiceUrls.RequestParams.USERNAME, username);
        params.put(ServiceUrls.RequestParams.PASSWORD, password);

        new RetrofitRequester(this).sendStringRequest(ServiceUrls.RequestNames.LOGIN_REQUEST, params, true);
    }
}
