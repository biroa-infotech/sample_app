package sk.infotech.samplestiapp.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import sk.infotech.samplestiapp.R;
import sk.infotech.samplestiapp.entities.Movie;
import sk.infotech.samplestiapp.movies.IMoviesView;
import sk.infotech.samplestiapp.movies.MovieListPresenter;
import sk.infotech.samplestiapp.movies.MovieRecyclerAdapter;
import sk.infotech.samplestiapp.movies.OnItemClickListener;
import sk.infotech.samplestiapp.ui.activities.MovieDetailActivity;
import sk.infotech.samplestiapp.ui.activities.ScrollingActivity;


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
        Intent intent = new Intent(getActivity(), ScrollingActivity.class);
        intent.putExtra(ScrollingActivity.EXTRA_MOVIE, movie);

        ImageView movieImage = (ImageView) rowView.findViewById(R.id.movie_image);
        LinearLayout movieNameHolder = (LinearLayout) rowView.findViewById(R.id.movie_name_holder);

        View statusBar = getActivity().findViewById(android.R.id.statusBarBackground);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        Pair<View, String> imagePair = Pair.create((View) movieImage, "image");
        //Pair<View, String> holderPair = Pair.create((View) movieNameHolder, "nameHolder");
       // Pair<View, String> statusPair = Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME);
       // Pair<View, String> toolbarPair = Pair.create((View)toolbar, "toolbar");

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), imagePair);


        getActivity().startActivity(intent, options.toBundle());
    }
}
