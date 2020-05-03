package com.news.elvezanews;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.news.elvezanews.Data.LoadTempNews;
import com.news.elvezanews.Models.NewsModelList;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NewsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private List<NewsModelList> allNews;
    private LoadTempNews tempNews;
    private NewsModelList news;
    private int position;
    ImageView mainImg;
    TextView headline;
    TextView newsBody;
    TextView imageDesc;
    FloatingActionButton backbutton;
    String VideoCode;

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private YouTubePlayer.OnInitializedListener youtube_listener;


    public NewsActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        setWhiteSysBar();
        setStatusbarspace();

        tempNews  = new LoadTempNews();
        allNews = tempNews.getNews();
        position = Integer.parseInt(getIntent().getStringExtra("position"));
        Log.i("Post ID", ""+position);

        String wp_geturl = "http://13.244.117.59/wp-json/wp/v2/posts?include[]="+position;
        youtube_listener = this;

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);


       // news = allNews.get(1);

        mainImg = findViewById(R.id.mainIMG);
        headline = findViewById(R.id.headline);
        newsBody = findViewById(R.id.newsbody);
        backbutton = findViewById(R.id.backbutton);

        jsonParse(wp_geturl);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Youtube Link Load


    }

    private RequestQueue mQueue;

    private void jsonParse(String getURL){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, getURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.i("response", ""+response);
                try {
                    JSONObject post = response.getJSONObject(0);

                    headline.setText(Html.fromHtml(post.getJSONObject("title").getString("rendered")));
                    newsBody.setText(Html.fromHtml(post.getJSONObject("content").getString("rendered")));

                    JSONArray videos = post.getJSONObject("meta_box").getJSONArray("prefix-video");
                    JSONArray videos_desc = post.getJSONObject("meta_box").getJSONArray("prefix-description");

                    boolean featured_image = false;
                    if(featured_image){
                        // set the featured image
                        setFeaturedImage("Image Response Path");

                        //check for content

                        //check for videos

                    }
                    else{

                        //there is no feature image so feature a video

                        if(videos.isNull(0)){
                            //there are no videos ... nothing to feature
                            mainImg.setVisibility(View.GONE);
                            youTubeView.setVisibility(View.GONE);
                        }
                        else{
                            //feature the first video
                            for(int x = 0;x < videos.length(); x++){
                                //Load the video and add Description. If video is at top position load to the top. The second video will be added to the bottom of current pos
                                if (x == 0) {
                                    // this is the first video
                                    mainImg.setVisibility(View.GONE);
                                    youTubeView.setVisibility(View.VISIBLE);
                                    launchYoutubePlayer(getYoutubeId(videos.getString(x)), youTubeView);

                                }


                            }
                        }

                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }
    private void setFeaturedImage(String image){
        Picasso pic = Picasso.get();
        pic.load(image).into(mainImg);
        mainImg.setVisibility(View.VISIBLE);
        youTubeView.setVisibility(View.GONE);
    }

    private void launchYoutubePlayer(String YoutubeID, YouTubePlayerView player){

        player.isInEditMode();
        player.initialize(Config.YOUTUBE_API_KEY, youtube_listener);

    }
    public void setWhiteSysBar(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = getWindow().getDecorView();
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            //flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorSecondary));

            //ContextCompat.getColor(this,R.color.white)

        }
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    FrameLayout statusbarspace;
    int statusBarHeight;
    public void setStatusbarspace(){
        statusbarspace = findViewById(R.id.statusspace);
        statusbarspace.setVisibility(View.VISIBLE);
        statusBarHeight = getStatusBarHeight();

        ConstraintLayout.LayoutParams sbsLP = (ConstraintLayout.LayoutParams) statusbarspace.getLayoutParams();
        sbsLP.height = statusBarHeight;
        statusbarspace.setLayoutParams(sbsLP);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.loadVideo(VideoCode); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
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
