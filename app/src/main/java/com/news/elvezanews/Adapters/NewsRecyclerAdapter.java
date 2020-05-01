package com.news.elvezanews.Adapters;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.news.elvezanews.Fragments.NewsFragment;
import com.news.elvezanews.Interfaces.RecyclerViewClickListener;
import com.news.elvezanews.Models.NewsModelList;
import com.news.elvezanews.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {

    private Context context;
    JSONArray allPosts;
    JSONObject post;
    NewsModelList newsItem;
    RecyclerViewClickListener mListener;
    int count = 0;

    public NewsRecyclerAdapter(Context context, JSONArray allPosts, RecyclerViewClickListener listener) {

        this.allPosts = allPosts;
        this.context = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        count++;

        if(count == 2){
            count = 0;
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.newslist_item, parent,false), mListener);
        }
        else{
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.newslist_item, parent,false), mListener);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.Headline.setText(post.getJSONObject("title").getString("rendered"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            holder.body.setText(Html.fromHtml(post.getJSONObject("content").getString("rendered")));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            String YoutubeURL = post.getJSONObject("meta_box").getJSONArray("prefix-video").getString(0);
            Log.i("Youtube URL", YoutubeURL);
            String YoutubeID = getYoutubeId(YoutubeURL);
            Log.i("Youtube ID", ""+YoutubeID);

            Picasso pic = Picasso.get();
            pic.load("https://img.youtube.com/vi/"+YoutubeID+"/0.jpg").into(holder.mainImg);
        } catch (JSONException e) {
            e.printStackTrace();
        }






        float scalingFactor = 0.9f; // scale down to half the size


        holder.contentView.setScaleX(scalingFactor);
        holder.contentView.setScaleY(scalingFactor);

    }

    @Override
    public int getItemViewType(int position) {
        try {
            post = allPosts.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return allPosts.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView Headline;
        TextView body;
        ImageView mainImg;
        View contentView;
        public ViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            Headline = itemView.findViewById(R.id.headline);
            mainImg = itemView.findViewById(R.id.mainIMG);
            body = itemView.findViewById(R.id.newsbody);
            contentView = itemView;


            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            mListener.onClick(view, getAdapterPosition());
        }
    }

    public static String getYoutubeId(String url) {
        String pattern = "https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*";

        Pattern compiledPattern = Pattern.compile(pattern,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }/*from w  w  w.  j a  va  2 s .c om*/
        return null;
    }




}
