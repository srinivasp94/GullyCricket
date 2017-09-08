package sample.ramya.com.exampleapp.retrofitapis;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import sample.ramya.com.exampleapp.R;
import sample.ramya.com.exampleapp.pojoexample.Movie;

/**
 * Created by elancer on 8/10/2017.
 */

public class MovieGridAdapter extends ArrayAdapter<Movie> {
    List<Movie> mList;
    Context context;

    public MovieGridAdapter(Context context,int i,List<Movie> mList) {
        super(context,i,mList);
        this.mList = mList;
        this.context = context;
    }

   /* @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }*/

   /* @Override
    public long getItemId(int position) {
        return position;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        Holder holder;
        Movie movie=getItem(position);
       /*Movie movie=new Movie();*/
        if (v == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            v = inflater.inflate(R.layout.movie_grid_view, null, false);
            holder = new Holder();
            holder.img_poster = (ImageView) v.findViewById(R.id.grid_movie_img);
            holder.gridTitle = (TextView) v.findViewById(R.id.grid__movie_title);
            holder.gridYear = (TextView) v.findViewById(R.id.grid__movie_year);
            v.setTag(holder);
        } else {
            holder = (Holder) v.getTag();

            holder.gridTitle.setText(movie.getTitle());
            holder.gridYear.setText(movie.getYear().toString());
            Picasso.with(context).load(movie.getImage()).into(holder.img_poster);
        }
        return v;

    }

    public class Holder {
        ImageView img_poster;
        TextView gridTitle, gridYear;
    }
}
