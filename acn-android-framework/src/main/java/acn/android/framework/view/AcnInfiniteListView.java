package acn.android.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import acn.android.framework.util.InfiniteListAdapter;

/**
 * Created by ugurcan.yildirim on 11.03.2016.
 */
public class AcnInfiniteListView<T> extends ListView {

    private boolean loading = false;
    private View loadingView;

    private InfiniteListAdapter infiniteListAdapter;

    public AcnInfiniteListView(Context context) {
        super(context);
    }

    public AcnInfiniteListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AcnInfiniteListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init(InfiniteListAdapter<T> infiniteListAdapter, final int maxNumOfItems, final View loadingView){

        this.infiniteListAdapter = infiniteListAdapter;
        this.setAdapter(infiniteListAdapter);

        this.loadingView = loadingView;

        this.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(AcnInfiniteListView.this.getCount() >= maxNumOfItems)
                    return;

                int lastVisibleItem = visibleItemCount + firstVisibleItem;
                if (lastVisibleItem >= totalItemCount && !loading) {

                    AcnInfiniteListView.this.infiniteListAdapter.onNewLoadRequired();

                }
            }
        });

    }

    public void addNewItem(T newItem){
        infiniteListAdapter.addNewItem(newItem);
    }

    public void startLoading(){
        loading = true;
        this.addFooterView(loadingView);
    }

    public void stopLoading(){
        this.removeFooterView(loadingView);
        loading = false;
    }

}
