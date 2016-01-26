package sk.infotech.samplestiapp.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sk.infotech.samplestiapp.R;
import sk.infotech.samplestiapp.entities.Movie;
import sk.infotech.samplestiapp.movies.IMoviesView;
import sk.infotech.samplestiapp.movies.MovieListPresenter;
import sk.infotech.samplestiapp.movies.MovieRecyclerAdapter;
import sk.infotech.samplestiapp.movies.OnItemClickListener;
import sk.infotech.samplestiapp.ui.activities.MovieDetailActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements IMoviesView {
    private RecyclerView mMovieList;
    private StaggeredGridLayoutManager mLayoutManager;
    private MovieListPresenter mPresenter;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        mPresenter = new MovieListPresenter(this);

        mMovieList = (RecyclerView) view.findViewById(R.id.movie_list);
        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        // Layout manager sluzi pre positioning jednotlivych poloziek v zozname
        // StaggeredGridLayoutManager sluzi na prepinanie medzi tym aby sa polozky zobrazovali bud v jednom stlpci
        // alebo v dvoch
        mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mMovieList.setLayoutManager(mLayoutManager);

        MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(getActivity());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                makeTransition(view, mPresenter.getMovieFromPosition(position));
            }
        });
        mMovieList.setAdapter(adapter);

    }

    private void makeTransition(View rowView, Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);

        View image = rowView.findViewById(R.id.movie_image);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), image, "tImage");

        getActivity().startActivity(intent, options.toBundle());
    }
}
