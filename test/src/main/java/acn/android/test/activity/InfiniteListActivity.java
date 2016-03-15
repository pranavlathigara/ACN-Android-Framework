package acn.android.test.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.orhanobut.logger.Logger;
import com.squareup.otto.Subscribe;
import com.thefinestartist.utils.etc.PreferenceUtil;

import java.util.ArrayList;

import acn.android.framework.context.AcnActivity;
import acn.android.framework.view.AcnInfiniteListView;
import acn.android.test.R;
import acn.android.test.adapter.MyInfiniteListAdapter;
import butterknife.Bind;

/**
 * Created by ugurcan.yildirim on 11.03.2016.
 */
public class InfiniteListActivity extends AcnActivity {

    private int itemOffset = 0;
    private int itemCount = 25;

    public InfiniteListActivity(){
        super(R.layout.activity_infinitescrolling, R.id.toolbar, R.id.back_button, true, R.color.statusBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View loadingView = getLayoutInflater().inflate(R.layout.item_loading, null);

        ArrayList<String> itemList = new ArrayList<>();
        int maxNumOfItems = 500;

        MyInfiniteListAdapter adapter = new MyInfiniteListAdapter(this, R.layout.item_text, itemList);

        acn_infinitelistview.init(adapter, maxNumOfItems, loadingView);

    }

    @Subscribe
    @Override
    public void onInternetStatusChanged(ConnectivityStatus status){

        Logger.i("Internet status: " + status.name());

        PreferenceUtil.put("internetStatus", status.name());
        Logger.i("Internet status is put to shared prefs!");

    }

    //SIMULATES ITEM LOADING
    public void loadNewItems() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                acn_infinitelistview.startLoading();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Logger.e(e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void param) {
                //ADD NEW ITEMS TO LIST
                for (int i=itemOffset ; i < itemOffset + itemCount; i++) {
                    String item = "Item #" + i;
                    acn_infinitelistview.addNewItem(item);
                }
                itemOffset += itemCount;
                Logger.d(itemOffset + " " + itemCount);

                acn_infinitelistview.stopLoading();
            }
        }.execute();
    }

    @Bind(R.id.acn_infinitelistview)
    AcnInfiniteListView acn_infinitelistview;

}
