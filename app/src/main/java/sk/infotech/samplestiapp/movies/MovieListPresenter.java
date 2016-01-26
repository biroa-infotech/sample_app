package sk.infotech.samplestiapp.movies;

import java.util.ArrayList;

import sk.infotech.samplestiapp.entities.Movie;
import sk.infotech.samplestiapp.entities.MovieData;

/**
 * Created by MacMini on 26/01/16.
 */
public class MovieListPresenter {
    private IMoviesView  mView;

    public MovieListPresenter(IMoviesView view) {
        this.mView = view;
    }

    public Movie getMovieFromPosition(int position) {
        ArrayList<Movie> movies = new MovieData().populateList();
        return movies.get(position);
    }
}
