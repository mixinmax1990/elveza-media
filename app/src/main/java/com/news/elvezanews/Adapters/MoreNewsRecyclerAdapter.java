package com.news.elvezanews.Adapters;

import android.content.Context;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.news.elvezanews.Interfaces.RecyclerViewClickListener;
import com.news.elvezanews.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoreNewsRecyclerAdapter extends RecyclerView.Adapter<MoreNewsRecyclerAdapter.ViewHolder> {

    private Context context;
    JSONArray nextPosts;
    JSONObject post;
    RecyclerViewClickListener listener;
    int fullSize;
    int a,b,c,d,e = 0;
    boolean random = false;

    public MoreNewsRecyclerAdapter(Context context, JSONArray nextPosts, RecyclerViewClickListener listener) {
    this.context = context;
    this.nextPosts = nextPosts;
    this.listener = listener;

    fullSize = nextPosts.length();
    if(fullSize > 5) {
        //generateRandoms();
        random = true;
    }

    }
    private void generateRandoms(){

        a = findUnique();
        b = findUnique();
        c = findUnique();
        d = findUnique();
        e = findUnique();

    }

    private int findUnique(){
        Random r = new Random();
        boolean found = false;
        int val = 0;
        while(!found) {
            val = r.nextInt(1 - fullSize) + fullSize;
            if(val != a || val != b || val != c || val != d || val != e ){
                found = true;
            }
        }
        return val;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.morenew_item, parent,false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        try {

            String YoutubeURL = post.getJSONObject("meta_box").getJSONArray("prefix-video").getString(0);
            String YoutubeID = getYoutubeId(YoutubeURL);

            holder.moreNewsTitle.setText(Html.fromHtml(post.getJSONObject("title").getString("rendered")));

            //Loading Feature Image

            String featuredImage = post.getString("jetpack_featured_media_url");
            JSONArray videos = post.getJSONObject("meta_box").getJSONArray("prefix-video");

            if(!featuredImage.isEmpty()){

/*
                ConstraintLayout.LayoutParams LP = (ConstraintLayout.LayoutParams) holder.moreNewsCover.getLayoutParams();
                LP.height = (int)convertDpToPixel(300);
                holder.moreNewsCover.setLayoutParams(LP);
*/
                Picasso pic = Picasso.get();
                pic.load(featuredImage).into(holder.moreNewsCover);
            }
            else{
                Log.i("NO VIDEO", "SEE "+ videos);

                if(videos != null && videos.length() > 0 ){

                    Picasso pic = Picasso.get();
                    pic.load("https://img.youtube.com/vi/"+YoutubeID+"/0.jpg").into(holder.moreNewsCover);
                }
                else{
 /*
                    ConstraintLayout.LayoutParams LP = (ConstraintLayout.LayoutParams) holder.moreNewsCover.getLayoutParams();
                    LP.height = (int)convertDpToPixel(87);
                    holder.moreNewsCover.setLayoutParams(LP);
*/
                }

            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemViewType(int position) {
        try {
            post = nextPosts.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return fullSize;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        ImageView moreNewsCover;
        TextView moreNewsTitle;
        public ViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            moreNewsCover = itemView.findViewById(R.id.morenews_cover);
            moreNewsTitle = itemView.findViewById(R.id.morenews_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            listener.onClick(view, getAdapterPosition());
            Log.i("ID **", ""+getAdapterPosition());

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

    public float convertDpToPixel(float dp){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
