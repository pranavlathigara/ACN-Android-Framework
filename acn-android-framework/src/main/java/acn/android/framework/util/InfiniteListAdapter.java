package acn.android.framework.util;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public abstract class InfiniteListAdapter<T> extends ArrayAdapter<T> {

    private ArrayList<T> itemList;

    public InfiniteListAdapter(Activity activity, int layoutRes, ArrayList<T> itemList) {

        super(activity, layoutRes, itemList);

        this.itemList = itemList;

    }

    public abstract void onNewLoadRequired();

    public final void addNewItem(T newItem){
        itemList.add(newItem);
        this.notifyDataSetChanged();
    }

}