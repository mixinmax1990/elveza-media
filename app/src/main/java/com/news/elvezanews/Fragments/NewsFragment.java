package com.news.elvezanews.Fragments;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.news.elvezanews.Adapters.NewsRecyclerAdapter;
import com.news.elvezanews.Data.LoadTempNews;
import com.news.elvezanews.Interfaces.RecyclerViewClickListener;
import com.news.elvezanews.MainActivity;
import com.news.elvezanews.Models.NewsModelList;
import com.news.elvezanews.NewsActivity;
import com.news.elvezanews.R;

import java.util.List;

public class NewsFragment extends Fragment {


    private RecyclerView newsRecycler;
    private List<NewsModelList> allNews;
    private LoadTempNews tempNews;
    private Context context;
    private static View root;
    MainActivity activity;
    private LinearLayout menu_toggler;
    boolean menuToggled = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        activity = (MainActivity) getActivity();
        this.root = root;
        this.context = getContext();
        newsRecycler = root.findViewById(R.id.newslist_recycler);
        menu_toggler = activity.findViewById(R.id.menu_toggler);
        loadNewsRecyclerAdapter();


        newsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int currentscrollposition;
            View child;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {


                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                currentscrollposition += dy;
                //Log.i("Scroll", ""+currentscrollposition);
                Log.i("Scroll", ""+dy);
                if(dy < 0){

                    if(!menuToggled){
                        toggleMenu(true);
                    }
                }
                else{

                    if(menuToggled){
                        toggleMenu(false);
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
                if(child != newsRecycler.getChildAt(0)) {

                    child = newsRecycler.getChildAt(0);
                    animateScale(1, child);
                    animateScale(.9f, newsRecycler.getChildAt(1));

                }

            }
        });


        return root;
    }

    private void loadNewsRecyclerAdapter(){

        tempNews  = new LoadTempNews();
        allNews = tempNews.getNews();



        final RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                NewsModelList news = allNews.get(position);
                activity.transitionNewsActivity(position);
            }
        };


        NewsRecyclerAdapter adapter = new NewsRecyclerAdapter(getContext(), allNews, listener);

        newsRecycler.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setStackFromEnd(false);
        newsRecycler.setLayoutManager(llm);
    }




    ValueAnimator vaMenExp;
    public void toggleMenu(boolean show){




        if(show){
            vaMenExp = ValueAnimator.ofInt(0, (int)convertDpToPixel(87));
            menuToggled = true;
        }
        else{
            menuToggled = false;
            vaMenExp = ValueAnimator.ofInt((int)convertDpToPixel(87), 0);
        }


        int duration = 300;

        final ConstraintLayout.LayoutParams LP = (ConstraintLayout.LayoutParams) menu_toggler.getLayoutParams();



        vaMenExp.setDuration(duration);
        vaMenExp.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int animValue = Math.round((int) animation.getAnimatedValue());
                try {
                    LP.height = animValue;
                    menu_toggler.setLayoutParams(LP);
                }catch(Exception e){}
            }
        });

        vaMenExp.start();

    }

    ValueAnimator vaItemExp;
    public void animateScale(float factor,final View v){


        float scaleX = v.getScaleX();

        if(factor != .9f){
            vaItemExp = ValueAnimator.ofFloat(scaleX, 1f);
        }
        else{

            vaItemExp = ValueAnimator.ofFloat(scaleX, .9f);
        }

        int duration = 300;


        vaItemExp.setDuration(duration);
        vaItemExp.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float animValue = (float)animation.getAnimatedValue();
                try {

                  v.setScaleX(animValue);
                  v.setScaleY(animValue);

                }catch(Exception e){}
            }
        });

        vaItemExp.start();

    }

    public static float convertDpToPixel(float dp){
        return dp * ((float) root.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
