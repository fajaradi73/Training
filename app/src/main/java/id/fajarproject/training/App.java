package id.fajarproject.training;

import android.app.Application;

/**
 * Create by Fajar Adi Prasetyo on 28/06/2020.
 */

public class App extends Application {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500";

    public App(){

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
