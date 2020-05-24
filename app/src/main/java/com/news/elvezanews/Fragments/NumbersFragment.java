package com.news.elvezanews.Fragments;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.news.elvezanews.Adapters.NumbersRecyclerAdapter;
import com.news.elvezanews.Data.WordpressJson;
import com.news.elvezanews.Interfaces.RecyclerViewClickListener;
import com.news.elvezanews.Interfaces.VolleyRequestResponse;
import com.news.elvezanews.MainActivity;
import com.news.elvezanews.R;

import org.json.JSONArray;

public class NumbersFragment extends Fragment implements VolleyRequestResponse {

    private RecyclerView numbersRecycler;
    private Context context;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_more, container, false);
        numbersRecycler = root.findViewById(R.id.numberslist_recycler);
        progressBar = root.findViewById(R.id.progressbarNumbers);
        this.context = getContext();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WordpressJson json = new WordpressJson(NumbersFragment.this,"http://13.244.88.197/wp-json/wp/v2/numbers", this.context,  "numbers");

    }
    public void loadRecyclerAdapter(final JSONArray allNumbers){

        Log.i("Response Data", ""+allNumbers);
        final RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Log.i("item was clicked", "True");

                TextView numb = view.findViewById(R.id.numb_body);
                try {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + numb.getText().toString()));
                    context.startActivity(callIntent);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                }

            }
        };

        NumbersRecyclerAdapter adapter = new NumbersRecyclerAdapter(context, allNumbers, listener);
        numbersRecycler.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setStackFromEnd(false);
        numbersRecycler.setLayoutManager(llm);

    }


    @Override
    public void onSuccessJson(JSONArray result, String type) {
       if(type == "numbers") {
           loadRecyclerAdapter(result);
           progressBar.setVisibility(View.GONE);
       }
    }

    @Override
    public void onFailureJson(int responseCode, String responseMessage) {

    }
}
