package com.news.elvezanews.Adapters;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.news.elvezanews.Interfaces.RecyclerViewClickListener;
import com.news.elvezanews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NumbersRecyclerAdapter extends RecyclerView.Adapter<NumbersRecyclerAdapter.ViewHolder> {

    private Context context;
    private RecyclerViewClickListener mListener;
    private JSONArray allNumbers;
    private JSONObject number;

    public NumbersRecyclerAdapter(Context context, JSONArray allNumbers, RecyclerViewClickListener listener) {
        this.allNumbers = allNumbers;
        this.context = context;
        this.mListener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.numbers_item, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.Name.setText(Html.fromHtml(number.getJSONObject("title").getString("rendered")));
            holder.Number.setText(Html.fromHtml(number.getJSONObject("content").getString("rendered")));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemViewType(int position) {
        try {
            number = allNumbers.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return allNumbers.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView Name;
        TextView Number;

        public ViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            Name = itemView.findViewById(R.id.numb_headline);
            Number = itemView.findViewById(R.id.numb_body);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            mListener.onClick(view, getAdapterPosition());
            Log.i("ID **", ""+getAdapterPosition());

        }
    }
}
