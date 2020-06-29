package id.fajarproject.training.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Create by Fajar Adi Prasetyo on 28/06/2020.
 */
public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    int visibleThreshold = 5;
    int firstVisibleItem = 0;
    int lastVisibleItem;
    RecyclerView.LayoutManager layoutManager;

    public PaginationScrollListener(LinearLayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }

    public PaginationScrollListener(GridLayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }

    public PaginationScrollListener(StaggeredGridLayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int totalItemCount = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager){
            visibleThreshold    = layoutManager.getChildCount();
            firstVisibleItem    = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            lastVisibleItem     = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }else if (layoutManager instanceof GridLayoutManager){
            visibleThreshold    = ((GridLayoutManager) layoutManager).getSpanCount();
            firstVisibleItem    = ((GridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            lastVisibleItem     = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            int[] lastVisiblePosition = ((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(null);
            int[] lastVisibleItems     = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            visibleThreshold    = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            firstVisibleItem    = getLastVisible(lastVisiblePosition);
            lastVisibleItem     = getLastVisible(lastVisibleItems);
        }
        if (!isLoading() && !isLastPage()){
            if (visibleThreshold + firstVisibleItem >= totalItemCount
                    && firstVisibleItem >= 0
                    && totalItemCount >= getTotalPageCount())
            {
                loadMoreItems();
            }
        }
        if (lastVisibleItem < totalItemCount){
            backToTop(true);
        }else {
            backToTop(false);
        }
        if (firstVisibleItem == 0){
            backToTop(false);
        }
    }

    public abstract int getTotalPageCount();
    public abstract Boolean isLastPage();
    public abstract Boolean isLoading();
    public abstract void backToTop(boolean isShow);
    public abstract void loadMoreItems();

    private int getLastVisible(int[] firstVisibleItemPositions){
        int maxSize = 0;
        for (int i = 0 ; i < firstVisibleItemPositions.length ; i ++){
            if (i == 0){
                maxSize = firstVisibleItemPositions[i];
            }else if (firstVisibleItemPositions[i] > maxSize){
                maxSize = firstVisibleItemPositions[i];
            }
        }
        return maxSize;
    }
}
