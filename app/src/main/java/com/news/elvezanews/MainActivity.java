package com.news.elvezanews;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import com.news.elvezanews.Models.NewsModelList;
import com.news.elvezanews.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        setWhiteSysBar();
        setStatusbarspace();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(, menu);
        return true;
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


    public void transitionNewsActivity(int position){

        Intent sharedIntent = new Intent(MainActivity.this, NewsActivity.class);
        sharedIntent.putExtra("position", ""+position);
        startActivity(sharedIntent);
    }
}