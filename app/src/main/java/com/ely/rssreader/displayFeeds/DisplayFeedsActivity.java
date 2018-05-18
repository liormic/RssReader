package com.ely.rssreader.displayFeeds;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ely.rssreader.R;
import com.ely.rssreader.RssItem;
import com.ely.rssreader.displayInBrowser.DisplayInBrowserActivity;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayFeedsActivity extends AppCompatActivity implements DisplayFeedsView, DisplayFeedAdapter.OnListItemClickListener {

    private static boolean isNetworkBusy = true;
    private List<RssItem> rssItemList = new ArrayList<>();
    private DisplayFeedAdapter displayFeedAdapter;
    private DisplayFeedsPresenterImpl presenter;
    @BindView(R.id.rssItemsRv)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_display_feeds);
        ButterKnife.bind(this);
        //Setting up empty Adapter to avoid "No Adapter Attached" error
        DisplayFeedAdapter displayFeedAdapter = new DisplayFeedAdapter(rssItemList, this);
        presenter = new DisplayFeedsPresenterImpl();
        presenter.setView(this);

        if (savedInstanceState == null) {
            presenter.executeCall();
        } else {
            rssItemList = savedInstanceState.getParcelableArrayList(getString(R.string.NEWS_FEED_LIST));
            initRecyclerView(savedInstanceState);
        }
    }

    @Override
    public void getRssItems(List<RssItem> rssItemList) {
        this.rssItemList = rssItemList;
        initRecyclerView(null);
    }

    private void initRecyclerView(Bundle bundle) {
        displayFeedAdapter = new DisplayFeedAdapter(rssItemList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(displayFeedAdapter);

        if (bundle != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(bundle);
        }
        isNetworkBusy = false;
    }

    @Override
    public void showNetworkFailureToast() {
        Toast.makeText(this, R.string.request_failure_toast, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent displayInBrowserIntent = new Intent(this, DisplayInBrowserActivity.class);
        displayInBrowserIntent.putExtra(getString(R.string.ARTICLE_URL_EXTRA), rssItemList.get(clickedItemIndex).getLinkToArticle());
        startActivity(displayInBrowserIntent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!isNetworkBusy) {
            outState.putParcelableArrayList(getString(R.string.NEWS_FEED_LIST), (ArrayList<? extends Parcelable>) rssItemList);
            outState.putParcelable(getString(R.string.RV_STATE), recyclerView.getLayoutManager().onSaveInstanceState());
        }
    }
}
