package com.news.elvezanews;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
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
    private RequestQueue mQueue;

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private View youtube_view_container;
    private View socialBar;
    private ConstraintLayout videoSection;
    private YouTubePlayer.OnInitializedListener youtube_listener;
    private ConstraintLayout news_container;


    public NewsActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        setWhiteSysBar();
        setStatusbarspace();

        tempNews  = new LoadTempNews();
        news_container = findViewById(R.id.news_container);
        allNews = tempNews.getNews();
        position = Integer.parseInt(getIntent().getStringExtra("position"));
        socialBar = findViewById(R.id.social_bar);
        videoSection = findViewById(R.id.video_section);
        Log.i("Post ID", ""+position);

        String wp_geturl = "http://13.244.88.197/wp-json/wp/v2/posts?include[]="+position;
        youtube_listener = this;
        youtube_view_container = findViewById(R.id.youtube_view_container);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);


       // news = allNews.get(1);

        mainImg = findViewById(R.id.mainIMG);
        headline = findViewById(R.id.headline);
        newsBody = findViewById(R.id.newsbody);
        backbutton = findViewById(R.id.backbutton);

        mQueue = Volley.newRequestQueue(this);
        jsonParse(wp_geturl);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Youtube Link Load


    }
    View BottomView;
    private void setContent(JSONObject post, boolean featured_first_video){
         BottomView = newsBody;

        JSONArray videos = null;
        JSONArray videos_desc = null;

        try {
            videos = post.getJSONObject("meta_box").getJSONArray("prefix-video");
            videos_desc = post.getJSONObject("meta_box").getJSONArray("prefix-description");

            for(int x = 0;x < videos.length(); x++){
                //Load the video and add Description. If video is at top position load to the top. The second video will be added to the bottom of current pos
                if (x == 0) {
                    if(featured_first_video){
                        // ignore this video

                    }
                    else{
                        // this is the first video
                        // dynamically create a YOutubeVIdeo View
                        createVideoPlayer(getYoutubeId(videos.getString(x)));
                        createDescTextView(videos_desc.getString(x));

                    }

                }
                else{
                        createVideoPlayer(getYoutubeId(videos.getString(x)));
                        createDescTextView(videos_desc.getString(x));
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private void createDescTextView(final String desc){
        final TextView description = new TextView(this);
        description.setText(Html.fromHtml(desc));
        ConstraintLayout.LayoutParams LPdesc = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margins = (int)convertDpToPixel(20);
        description.setTextColor(Color.WHITE);
        description.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        LPdesc.setMargins(margins, margins, margins, margins);
        description.setLayoutParams(LPdesc);
        description.setPadding(0,0,0, margins);
        description.setAlpha(0.8f);
        description.setId(View.generateViewId());
        videoSection.addView(description);

        ConstraintSet set = new ConstraintSet();
        set.clone(videoSection);
        set.connect(description.getId(), ConstraintSet.TOP, BottomView.getId(), ConstraintSet.BOTTOM);
        set.applyTo(videoSection);

        BottomView = description;


    }

    private void createVideoPlayer(final String video){

        // Fake Video Player
        final FrameLayout videothumb_cont = new FrameLayout(this);
        ConstraintLayout.LayoutParams LP = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margins = (int)convertDpToPixel(10);
        LP.setMargins(margins, margins, margins, margins);
        videothumb_cont.setLayoutParams(LP);
        videothumb_cont.setId(View.generateViewId());
        videoSection.addView(videothumb_cont);

        ConstraintSet set = new ConstraintSet();
        set.clone(videoSection);
        set.connect(videothumb_cont.getId(), ConstraintSet.TOP, BottomView.getId(), ConstraintSet.BOTTOM);
        set.applyTo(videoSection);

        //Create an Imageview that holds the thumbnail
        final ImageView videothumb = new ImageView(this);
        videothumb.setScaleType(ImageView.ScaleType.CENTER_CROP);
        FrameLayout.LayoutParams VTLP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        VTLP.height = (int)convertDpToPixel(200);
        videothumb.setLayoutParams(VTLP);

        //PlayButton
        ImageButton play = new ImageButton(this);
        FrameLayout.LayoutParams VTPB = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        VTPB.gravity = Gravity.CENTER;
        play.setScaleType(ImageView.ScaleType.CENTER_CROP);
        play.setMinimumHeight((int)convertDpToPixel(60));
        play.setMinimumWidth((int)convertDpToPixel(60));
        play.setBackgroundColor(Color.TRANSPARENT);
        play.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        play.setLayoutParams(VTPB);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check how to send youtube id
                removeYTPlayer();
                Log.i("VideoCode", ""+video);

                YouTubePlayerView newplayer = new YouTubePlayerView(view.getContext());

                ConstraintLayout.LayoutParams LPV = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                newplayer.setLayoutParams(LPV);

                newplayer.setId(View.generateViewId());

                youTubeView = newplayer;
                videothumb_cont.addView(youTubeView);

                launchYoutubePlayer(video, newplayer);


            }
        });




        Picasso pic = Picasso.get();
        pic.load("https://img.youtube.com/vi/"+video+"/0.jpg").into(videothumb);

        videothumb_cont.addView(videothumb);
        videothumb_cont.addView(play);


        BottomView = videothumb_cont;




    }
    private void removeYTPlayer(){
        if(youTubeView == null){
            //No player is set
        }
        else{
            ((ViewManager)youTubeView.getParent()).removeView(youTubeView);
        }
    }
    int player_id = 0;
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

                    String featuredImage = post.getString("jetpack_featured_media_url");

                    if(!featuredImage.isEmpty()){
                        // set the featured image
                        setFeaturedImage(featuredImage);

                        //check for content

                        //check for videos
                        if(videos.isNull(0)){

                        }else{
                            videoSection.setVisibility(View.VISIBLE);
                            setContent(post, false);

                        }

                    }
                    else{

                        //there is no feature image so feature a video

                        if(videos.isNull(0)){
                            //there are no videos ... nothing to feature
                        }
                        else{
                            //feature the first video
                            youTubeView.setVisibility(View.VISIBLE);
                            youtube_view_container.setVisibility(View.VISIBLE);
                            launchYoutubePlayer(getYoutubeId(videos.getString(0)), youTubeView);

                            if(videos.length() > 1){
                                videoSection.setVisibility(View.VISIBLE);
                                setContent(post, false);
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
    }

    private void launchYoutubePlayer(String YoutubeID, YouTubePlayerView player){

        //player.isInEditMode();
        VideoCode = YoutubeID;
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
            //player.cueVideo(VideoCode);
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


    public float convertDpToPixel(float dp){
        return dp * ((float) getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
