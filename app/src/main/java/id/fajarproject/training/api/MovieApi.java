package id.fajarproject.training.api;

import id.fajarproject.training.model.Movie;
import id.fajarproject.training.model.MovieItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Create by Fajar Adi Prasetyo on 28/06/2020.
 */
public interface MovieApi {

    @GET("movie/now_playing")
    Call<Movie> getMovieList(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<MovieItem> getMovieDetail(@Path ("movie_id") int movieId, @Query("api_key") String apiKey);

}
