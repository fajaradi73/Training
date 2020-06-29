package id.fajarproject.training.feature.movie;

import android.app.Activity;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import id.fajarproject.training.R;
import id.fajarproject.training.model.Movie;
import id.fajarproject.training.model.MovieItem;
import id.fajarproject.training.repository.MovieRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Create by Fajar Adi Prasetyo on 28/06/2020.
 */
public class MoviePresenter {
    Activity activity;
    MovieView view;
    MovieRepository movieRepository;

    public MoviePresenter(Activity activity,MovieView movieView){
        this.activity   = activity;
        this.view       = movieView;
        movieRepository = new MovieRepository();
    }

    public void getMovieList(final int page){
        if (page == 1){
            view.showLoading();
        }
        movieRepository.getMovieList(activity.getString(R.string.apiKey),page).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call,@NonNull  Response<Movie> response) {
                view.hideLoading();
                if (response.code() == 200){
                    if (response.body() != null && response.body().getMovieItems().size() > 0){
                        view.getDataSuccess(response.body().getMovieItems());
                    }else {
                        view.getDataSuccess(new ArrayList<MovieItem>());
                    }
                }else {
                    view.getDataSuccess(new ArrayList<MovieItem>());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call,@NonNull  Throwable t) {
                view.hideLoading();
                t.getMessage();
                if (page == 1){
                    view.getDataFailed(t.getMessage());
                }
            }
        });
    }
}
