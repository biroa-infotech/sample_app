package sk.infotech.samplestiapp.entities;

import java.util.ArrayList;

/**
 * Created by MacMini on 26/01/16.
 */
public class MovieData {
   private  String urls[] = {"http://illusion.scene360.com/wp-content/uploads/2014/08/apocalyptic-movies-03.jpg", // Avengers 2
                            "http://images.christianpost.com/full/88193/batman-vs-superman.jpg",                 // Dawn of Justice
                            "http://cdn.idigitaltimes.com/sites/idigitaltimes.com/files/2015/12/14/new-deadpool-promo-images-offer-hints-movie-s-unconventional-tone-492440.jpg", // Deadpool
                            "http://www.thevipconcierge.com/Content/Uploads/images/UploadedEventPhotos/Jurassic%20World-2.jpg", // jurassic world
                            "https://i.ytimg.com/vi/yISKeT6sDOg/maxresdefault.jpg",};

    private String movieNames[] = { "Avengers 2",
            "Dawn of Justice",
            "Deadpool",
            "Jurassic World",
            "Fast and Furious 7",
    };

    public ArrayList<Movie> populateList() {
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < urls.length; i++) {
            Movie movie = new Movie();
            movie.setName(movieNames[i]);
            movie.setUrl(urls[i]);
            if (i == 2 || i == 5) {
               movie.setIsFav(true);
            }

            movies.add(movie);
        }

        return movies;
    }

}
