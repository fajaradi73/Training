package id.fajarproject.training.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.fajarproject.training.App;
import id.fajarproject.training.R;
import id.fajarproject.training.model.MovieItem;
import id.fajarproject.training.util.Constant;
import id.fajarproject.training.util.Util;

/**
 * Create by Fajar Adi Prasetyo on 28/06/2020.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.AdapterHolder> {

    List<MovieItem> list;
    Activity activity;
    private boolean isLoadingAdded = false;

    public MovieAdapter(Activity activity, List<MovieItem> list){
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Constant.VIEW_TYPE_ITEM){
            return new AdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie,parent,false));
        }else {
            return new AdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_loading,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        if (holder.getItemViewType() == Constant.VIEW_TYPE_ITEM){
            MovieItem data = list.get(position);
            holder.titleMovie.setText(data.getTitle());
            holder.dateMovie.setText(Util.convertDate(data.getReleaseDate(),"yyyy-MM-dd","dd MMMM yyyy"));
            holder.rattingMovie.setText(String.valueOf(data.getVoteAverage()));
            Glide.with(activity).load(App.BASE_URL_IMAGE + data.getPosterPath()).into(holder.imageMovie);
        }else {
            Log.d("Loading",".....");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<MovieItem> getMovieList(){
        return this.list;
    }

    public void addData(List<MovieItem> movieItems){
        this.list.addAll(movieItems);
        notifyDataSetChanged();
    }

    public MovieItem getMovie(int position){
        return list.get(position);
    }

    public void addLoadingFooter(){
        isLoadingAdded = true;
        list.add(new MovieItem());
        notifyItemInserted(list.size() - 1);
    }

    public void removeLoadingFooter(){
        isLoadingAdded = false;
        int position = list.size() - 1;
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size() - 1 && isLoadingAdded)
            return Constant.VIEW_TYPE_LOADING ;
        else
            return Constant.VIEW_TYPE_ITEM;
    }

    public static class AdapterHolder extends RecyclerView.ViewHolder {
        ImageView imageMovie;
        TextView titleMovie,dateMovie,rattingMovie;
        ProgressBar loading;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            imageMovie      = itemView.findViewById(R.id.ivMovie);
            titleMovie      = itemView.findViewById(R.id.tvTitle);
            dateMovie       = itemView.findViewById(R.id.tvDate);
            rattingMovie    = itemView.findViewById(R.id.tvRatting);
            loading         = itemView.findViewById(R.id.progressbar);
        }
    }
}
