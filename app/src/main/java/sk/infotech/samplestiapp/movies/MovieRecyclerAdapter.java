package sk.infotech.samplestiapp.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import sk.infotech.samplestiapp.R;
import sk.infotech.samplestiapp.entities.Movie;
import sk.infotech.samplestiapp.entities.MovieData;

/**
 * Created by MacMini on 26/01/16.
 */
public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.Holder> {
    private static final String TAG = MovieRecyclerAdapter.class.getName();
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private OnItemClickListener mItemClickListener;
    private ImageDownloadCallback mCallback;

    public MovieRecyclerAdapter(Context context) {
        this.mContext = context;
        this.mMovieList = new MovieData().populateList();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movies, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.movieName.setText(movie.getName());

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.e(TAG, "Bitmap Loaded");
                loadImagePalette(holder, bitmap);
                holder.movieImage.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.e(TAG, "Bitmap failed");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.e(TAG, "Bitmap prepare");
            }
        };

        // pokial si neulozime target do tagu, tak sa strati referencia nan a nezobrazi sa bitmapa
        holder.movieImage.setTag(target);

        Picasso.with(mContext)
                .load(movie.getUrl())
                .into(target);



    }

    private void loadImagePalette(final Holder holder, Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                int mutedLight = p.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                holder.movieNameHolder.setBackgroundColor(mutedLight);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public LinearLayout mainHolder;
        public LinearLayout movieNameHolder;
        public TextView movieName;
        public ImageView movieImage;

        public Holder(View itemView) {
            super(itemView);
            mainHolder = (LinearLayout) itemView.findViewById(R.id.main_holder);
            movieName = (TextView) itemView.findViewById(R.id.movie_name);
            movieNameHolder = (LinearLayout) itemView.findViewById(R.id.movie_name_holder);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface ImageDownloadCallback {

    }
}
