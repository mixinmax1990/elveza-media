package com.news.elvezanews.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.news.elvezanews.Adapters.NumbersRecyclerAdapter;
import com.news.elvezanews.Data.WordpressJson;
import com.news.elvezanews.Interfaces.RecyclerViewClickListener;
import com.news.elvezanews.Interfaces.VolleyRequestResponse;
import com.news.elvezanews.R;

import org.json.JSONArray;

public class NumbersFragment extends Fragment implements VolleyRequestResponse {

    private RecyclerView numbersRecycler;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_more, container, false);
        numbersRecycler = root.findViewById(R.id.numberslist_recycler);
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
       }
    }

    @Override
    public void onFailureJson(int responseCode, String responseMessage) {

    }
}
