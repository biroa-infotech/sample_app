package sk.infotech.samplestiapp.entities;

import java.io.Serializable;

/**
 * Created by MacMini on 26/01/16.
 */
public class Movie implements Serializable {
    public String name;
    public String url;
    public boolean isFav;


    public Movie() {
    }

    public boolean isFav() {
        return isFav;
    }

    public void setIsFav(boolean isFav) {
        this.isFav = isFav;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
