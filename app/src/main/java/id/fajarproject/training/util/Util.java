package id.fajarproject.training.util;

import android.content.Context;
import android.util.DisplayMetrics;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import id.fajarproject.training.App;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by Fajar Adi Prasetyo on 28/06/2020.
 */
public class Util {
    public static OkHttpClient getOkHttp(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(30,  TimeUnit.SECONDS)
                .readTimeout(30,  TimeUnit.SECONDS)
                .writeTimeout(30,  TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
    }

    public static Retrofit getDefaultRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(App.BASE_URL)
                .client(Util.getOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);
    }

    public static String convertDate(String date,String oldFormat,String newFormat){
        SimpleDateFormat newDateFormat   = new SimpleDateFormat(newFormat, Locale.getDefault());
        SimpleDateFormat oldDateFormat   = new SimpleDateFormat(oldFormat,Locale.getDefault());
        try {
            return newDateFormat.format(Objects.requireNonNull(oldDateFormat.parse(date)));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
