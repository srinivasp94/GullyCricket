package sample.ramya.com.exampleapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sample.ramya.com.exampleapp.networkdata.RetrofitClient;
import sample.ramya.com.exampleapp.networkdata.RetrofitObjectAPI;
import sample.ramya.com.exampleapp.pojoexample.Enemy;
import sample.ramya.com.exampleapp.pojoexample.Example;
import sample.ramya.com.exampleapp.pojoexample.PojoResponse;

public class RetrofitMainActivity extends AppCompatActivity {
    TextView textView;
    ListView listView;
    ImageView img;
    RetrofitClient client;
    String map, string1;
    //    private static final String BASE_URL = "https://api.androidhive.info";
    private static final String BASE_URL = "https://api.github.com";
    ArrayList<Enemy> enemies = null;
    ArrayAdapter<String> adapter;
    HashMap<String, String> mHashmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_main);
        textView = (TextView) findViewById(R.id.jsondata);
        img = (ImageView) findViewById(R.id.img);
        listView = (ListView) findViewById(R.id.lv_list_item);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, map);*/
//        calljsonData();
        callExample();
    }

    private void callExample() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();
        RetrofitObjectAPI mObjectAPIExample = restAdapter.create(RetrofitObjectAPI.class);
        Callback callbackExp = new Callback() {
            @Override
            public void success(Object o, Response response) {
                ProgressDialog pDialog = new ProgressDialog(RetrofitMainActivity.this);
                Example example = (Example) o;
                if (response.getBody() != null) {
                    pDialog.dismiss();
                    Log.d("ExampleData##", response.getBody().toString());
                    Log.d("ExampleData##", example.toString());
                    String d = example.getLogin() + "\n" + example.getId() + "\n" + example.getAvatarUrl() + "\n" +
                            example.getUrl() + "\n" + example.getName() + "\n" + example.getBlog() + "\n" + example.getFollowers();
                    String imgurl = example.getAvatarUrl();
                    textView.setText(d);
                    Picasso.with(RetrofitMainActivity.this).load(imgurl).error(R.drawable.cricket_image_background).into(img);
                } else {
                    pDialog.show();

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("ERROR", String.valueOf(error));
            }
        };
        mObjectAPIExample.getlogindata(callbackExp);

    }

    private void calljsonData() {
        mHashmap = new HashMap<String, String>();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();
        RetrofitObjectAPI mObjectAPI = restAdapter.create(RetrofitObjectAPI.class);
        Callback callback = new Callback() {
            @Override
            public void success(Object o, Response response) {
                PojoResponse mPojoResponse = (PojoResponse) o;
                Log.d("^^^", response.getBody().toString());
                Log.d("(((", response.getBody().toString());

                enemies = mPojoResponse.getEnemies();
                int size = enemies.size();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    map = enemies.get(i).getName();

                    string1 = enemies.get(i).getAvatarAlive();
                    Log.d("$$$$", map);
                    textView.setText("\n" + builder.append(enemies.get(i).getName()));

                    mHashmap.put("name", map);
                    mHashmap.put("avatar_alive", string1);
                    Log.d("$$$$", String.valueOf(mHashmap.size()) + " list data" + mHashmap.toString());
//                    listView.setAdapter(adapter);
                }
//                textView.setText(mPojoResponse.getLocation().toString());

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("TAG", "" + error);
            }
        };
//        mObjectAPI.getResponse(callback);
    }
}
