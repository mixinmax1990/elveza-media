package com.news.elvezanews.Adapters;

        import android.content.Context;
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

        import java.util.List;

public class NewsRecyclerAdapterBackUp extends RecyclerView.Adapter<NewsRecyclerAdapterBackUp.ViewHolder> {

    private Context context;
    List<NewsModelList> allNews;
    NewsModelList newsItem;
    RecyclerViewClickListener mListener;
    int count = 0;

    public NewsRecyclerAdapterBackUp(Context context, List<NewsModelList> allNews, RecyclerViewClickListener listener) {

        this.allNews = allNews;
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
        holder.Headline.setText(newsItem.Headline);
        holder.body.setText(newsItem.BodyText);
        Picasso pic = Picasso.get();
        pic.load(newsItem.MainImage).into(holder.mainImg);

        float scalingFactor = 0.9f; // scale down to half the size


        holder.contentView.setScaleX(scalingFactor);
        holder.contentView.setScaleY(scalingFactor);

    }

    @Override
    public int getItemViewType(int position) {
        newsItem = allNews.get(position);

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return allNews.size();
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


}
