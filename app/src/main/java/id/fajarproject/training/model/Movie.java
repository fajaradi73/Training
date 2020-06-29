package id.fajarproject.training.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Movie{

	@SerializedName("dates")
	private Dates dates;

	@SerializedName("page")
	private Integer page;

	@SerializedName("total_pages")
	private Integer totalPages;

	@SerializedName("results")
	private List<MovieItem> movieItems;

	@SerializedName("total_results")
	private Integer totalResults;

	public void setDates(Dates dates){
		this.dates = dates;
	}

	public Dates getDates(){
		return dates;
	}

	public void setPage(Integer page){
		this.page = page;
	}

	public Integer getPage(){
		return page;
	}

	public void setTotalPages(Integer totalPages){
		this.totalPages = totalPages;
	}

	public Integer getTotalPages(){
		return totalPages;
	}

	public void setMovieItems(List<MovieItem> movieItems){
		this.movieItems = movieItems;
	}

	public List<MovieItem> getMovieItems(){
		return movieItems;
	}

	public void setTotalResults(Integer totalResults){
		this.totalResults = totalResults;
	}

	public Integer getTotalResults(){
		return totalResults;
	}
}