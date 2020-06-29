package id.fajarproject.training.feature.movie;

import java.util.List;

import id.fajarproject.training.model.MovieItem;

/**
 * Create by Fajar Adi Prasetyo on 28/06/2020.
 */
public interface MovieView {
    void setToolbar();
    void setRecycleView();
    void showLoading();
    void hideLoading();
    void setScrollRecycleView();
    void getDataSuccess(List<MovieItem> list);
    void getDataFailed(String message);
    void checkLastData();
    void checkData();
    void showData(boolean isShow);
}
