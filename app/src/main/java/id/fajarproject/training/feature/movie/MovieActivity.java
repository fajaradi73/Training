package id.fajarproject.training.feature.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.fajarproject.training.R;
import id.fajarproject.training.adapter.MovieAdapter;
import id.fajarproject.training.model.MovieItem;
import id.fajarproject.training.util.Constant;
import id.fajarproject.training.util.PaginationScrollListener;
import id.fajarproject.training.util.Util;

public class MovieActivity extends AppCompatActivity implements MovieView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.loading)
    ConstraintLayout loading;
    @BindView(R.id.noData)
    ConstraintLayout noData;
    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;
    @BindView(R.id.btnBackToTop)
    FloatingActionButton btnBackToTop;

    MoviePresenter presenter;

    GridLayoutManager layoutManager;
    MovieAdapter adapter;
    boolean isLoading = false;
    boolean isLastPage = false;

    int countData = 0;
    int currentPage = 1;
    int limit = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        setToolbar();
        setRecycleView();
        presenter = new MoviePresenter(this,this);
        presenter.getMovieList(currentPage);
        btnBackToTop.hide();
        btnBackToTop.setOnClickListener(v -> {
            rvMovie.smoothScrollToPosition(0);
        });
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void setRecycleView() {
        final int mNoOfColumns = Util.calculateNoOfColumns(this);
        layoutManager = new GridLayoutManager(this, mNoOfColumns);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getItemViewType(position) == Constant.VIEW_TYPE_LOADING){
                    return mNoOfColumns;
                }else if (adapter.getItemViewType(position) == Constant.VIEW_TYPE_ITEM){
                    return 1;
                }else {
                    return mNoOfColumns;
                }
            }
        });
        rvMovie.setLayoutManager(layoutManager);
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void setScrollRecycleView() {
        rvMovie.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            public int getTotalPageCount() {
                return limit;
            }

            @Override
            public Boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public Boolean isLoading() {
                return isLoading;
            }

            @Override
            public void backToTop(boolean isShow) {
                if (isShow){
                    btnBackToTop.show();
                }else {
                    btnBackToTop.hide();
                }
            }

            @Override
            public void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                presenter.getMovieList(currentPage);
            }
        });
    }

    @Override
    public void getDataSuccess(List<MovieItem> list) {
        countData = list.size();
        if (currentPage == 1){
            adapter = new MovieAdapter(this,list);
            rvMovie.setAdapter(adapter);
            setScrollRecycleView();
        }else {
            adapter.removeLoadingFooter();
            isLoading = false;
            adapter.addData(list);
            adapter.notifyDataSetChanged();
        }
        checkLastData();
        checkData();
    }

    @Override
    public void getDataFailed(String message) {
        Log.d("ErrorMovie",message);
        showData(false);
    }

    @Override
    public void checkLastData() {
        if (countData == limit){
            adapter.addLoadingFooter();
        }else {
            isLastPage = true;
        }
    }

    @Override
    public void checkData() {
        if (adapter.getItemCount() == 0){
            showData(false);
        }else {
            showData(true);
        }
    }

    @Override
    public void showData(boolean isShow) {
        if (isShow){
            rvMovie.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
        }else {
            rvMovie.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
        }
    }
}