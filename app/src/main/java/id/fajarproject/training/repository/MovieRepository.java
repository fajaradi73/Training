package id.fajarproject.training.repository;

import id.fajarproject.training.api.MovieApi;
import id.fajarproject.training.model.Movie;
import id.fajarproject.training.model.MovieItem;
import id.fajarproject.training.util.Util;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Create by Fajar Adi Prasetyo on 28/06/2020.
 */
public class MovieRepository {

    public MovieRepository(){

    }


    public Call<Movie> getMovieList(String apiKey,int page){
        Retrofit retrofit = Util.getDefaultRetrofit();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        return movieApi.getMovieList(apiKey,page);
    }

    public Call<MovieItem> getMovieDetail(String apiKey, int id){
        Retrofit retrofit = Util.getDefaultRetrofit();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        return movieApi.getMovieDetail(id,apiKey);
    }
}
