package com.ely.rssreader.displayFeeds;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.ely.rssreader.R;
import com.ely.rssreader.RssItem;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DisplayFeedAdapter extends RecyclerView.Adapter<DisplayFeedAdapter.ViewHolder> {
    private  List<RssItem> rssItemList;
    final private OnListItemClickListener onListItemClickListener;
    private Context context;

    public DisplayFeedAdapter(List<RssItem> rssItemList,OnListItemClickListener onListItemClickListener) {
        this.rssItemList = rssItemList;
        this.onListItemClickListener = onListItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image)
        SimpleDraweeView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.rss_feed_item_Linear_layout)
        LinearLayout linearLayout;
        @BindView(R.id.expandText)
        TextView expandText;
        @BindView(R.id.description)
        TextView description;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            image.setOnClickListener(this);
            expandText.setOnClickListener(this);
        }

        public void onClick(View v) {
            if(v==image) {
                int clickedIndex = getAdapterPosition();
                onListItemClickListener.onListItemClick(clickedIndex);
            }

            if(v==expandText){
                    if(description.getVisibility() == View.GONE) {
                        description.setVisibility(View.VISIBLE);
                    }else {
                        description.setVisibility(View.GONE);
                    }
            }
        }
    }

    public interface OnListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.rss_feed_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.image.setImageURI(rssItemList.get(position).getRssImage().getUrl());
        holder.title.setText(rssItemList.get(position).getTitle());
        holder.date.setText(rssItemList.get(position).getDate());
        holder.description.setText(rssItemList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {

        return rssItemList.size();

    }

}
