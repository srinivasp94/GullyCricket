package sample.ramya.com.exampleapp.pojoexample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by elancer on 8/9/2017.
 */

public class Moviepojo {

    @SerializedName("movies")
    @Expose
    private List<Movie> movies = null;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}


