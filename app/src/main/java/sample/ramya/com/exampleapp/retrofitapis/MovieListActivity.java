package sample.ramya.com.exampleapp.retrofitapis;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sample.ramya.com.exampleapp.R;
import sample.ramya.com.exampleapp.networkdata.RetrofitObjectAPI;
import sample.ramya.com.exampleapp.pojoexample.Movie;
import sample.ramya.com.exampleapp.pojoexample.Moviepojo;

public class MovieListActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://files.swiftsight.tech";
    ListView movieListView;
    GridView grid;
    List<Movie> movieArrayList;
    List<Movie> mList;
    MovieListAdapter listAdapter;
    MovieGridAdapter gridAdapter;

    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        movieListView = (ListView) findViewById(R.id.movies_list);
        grid=(GridView)findViewById(R.id.gridview);
//        setList();
        setGrid();
    }

    public void setGrid() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        RetrofitObjectAPI mObjectAPIExample = restAdapter.create(RetrofitObjectAPI.class);
        mDialog = new ProgressDialog(MovieListActivity.this);
        mDialog.setMessage("Please Wait...");
        mDialog.show();
        Callback callbackgrid = new Callback() {
            @Override
            public void success(Object o, Response response) {
                Moviepojo moviepojo = (Moviepojo) o;
//                Movie movie= (Movie) moviepojo.getMovies();
//                movieArrayList = new ArrayList<Movie>();
                if (mDialog.isShowing()) {
                    mDialog.dismiss();

                    mList = moviepojo.getMovies();
                    int size = mList.size();
                    gridAdapter = new MovieGridAdapter(MovieListActivity.this,R.layout.movie_grid_view, mList);
                    grid.setAdapter(gridAdapter);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        };
        mObjectAPIExample.getMovie(callbackgrid);
    }


    private void setList() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();
        RetrofitObjectAPI mObjectAPIExample = restAdapter.create(RetrofitObjectAPI.class);
        mDialog = new ProgressDialog(MovieListActivity.this);
        mDialog.setMessage("Please Wait...");
        mDialog.show();
        Callback callback = new Callback() {
            @Override
            public void success(Object o, Response response) {
                Moviepojo moviepojo = (Moviepojo) o;
//                Movie movie= (Movie) moviepojo.getMovies();
//                movieArrayList = new ArrayList<Movie>();
                if (mDialog.isShowing()) {
                    mDialog.dismiss();

                    movieArrayList = moviepojo.getMovies();
                    int size = movieArrayList.size();
               /* for (int i = 0; i < size; i++) {

                    title = movieArrayList.get(i).getTitle();
                    year = movieArrayList.get(i).getYear();
                    id = movieArrayList.get(i).getId();*/
//                    String imgurl = movieArrayList.get(i).getImage();

//                    movieArrayList.add(new Movie(movieArrayList.get(i).getId(), movieArrayList.get(i).getTitle(), movieArrayList.get(i).getYear()));
//                }
                    Log.d("MOVIEAS", moviepojo.toString() + "Size " + size);
                    listAdapter = new MovieListAdapter(MovieListActivity.this, R.layout.movies_list_layout, movieArrayList);
                    movieListView.setAdapter(listAdapter);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("MOVIEAS", String.valueOf(error));
            }
        };
        mObjectAPIExample.getMovie(callback);
    }
}
