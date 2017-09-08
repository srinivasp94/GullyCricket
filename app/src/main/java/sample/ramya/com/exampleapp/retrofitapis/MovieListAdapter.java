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

import sample.ramya.com.exampleapp.R;
import sample.ramya.com.exampleapp.pojoexample.Movie;

/**
 * Created by elancer on 8/9/2017.
 */

public class MovieListAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private List<Movie> movieArrayList;

    public MovieListAdapter(Context context, int resourceId, List<Movie> movieArrayList) {
        super(context, resourceId, movieArrayList);
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

  /*  @Override
    public int getCount() {
        return movieArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
//        Movie movie= (Movie) getItem(position);
        Movie movie = getItem(position);
//        movieArrayList = new ArrayList<Movie>();

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.movies_list_layout, null);
            holder = new ViewHolder();
            holder.mTitle = (TextView) convertView.findViewById(R.id.movie_title);
            holder.mYear = (TextView) convertView.findViewById(R.id.movie_year);
            holder.mId = (TextView) convertView.findViewById(R.id.movie_id);
            holder.mPoster = (ImageView) convertView.findViewById(R.id.movies_image);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.mId.setText("ID :" + movie.getId().toString());
        holder.mTitle.setText("Title : " + movie.getTitle());
        holder.mYear.setText("Year : " + movie.getYear().toString());

        Picasso.with(context).load(movie.getImage()).into(holder.mPoster);

        return convertView;
    }

    public static class ViewHolder {
        TextView mTitle, mYear, mId;
        ImageView mPoster;

    }

}
