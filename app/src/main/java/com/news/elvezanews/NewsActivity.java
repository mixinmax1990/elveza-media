package com.news.elvezanews;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.news.elvezanews.Data.LoadTempNews;
import com.news.elvezanews.Models.NewsModelList;
import com.squareup.picasso.Picasso;

import java.util.List;


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
        news = allNews.get(position);

        mainImg = findViewById(R.id.mainIMG);
        headline = findViewById(R.id.headline);
        newsBody = findViewById(R.id.newsbody);
        backbutton = findViewById(R.id.backbutton);

        Picasso pic = Picasso.get();
        pic.load(news.MainImage).into(mainImg);

        headline.setText(news.Headline);
        newsBody.setText(news.BodyText);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        //Youtube Link Load
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        if(!news.MainVideo.isEmpty()){
            VideoCode = news.MainVideo;
            mainImg.setVisibility(View.GONE);
            youTubeView.setVisibility(View.VISIBLE);
            youTubeView.isInEditMode();
            youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        }
        else{
            mainImg.setVisibility(View.VISIBLE);
            youTubeView.setVisibility(View.GONE);
        }



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
}
