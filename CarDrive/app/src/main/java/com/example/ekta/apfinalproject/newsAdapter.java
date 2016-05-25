package com.example.ekta.apfinalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ekta.apfinalproject.model.Doc;
import com.squareup.picasso.Picasso;

import java.util.List;



public class newsAdapter extends RecyclerView.Adapter<newsAdapter.ViewHolder> {

    private List<Doc> data;
    private Context context;
    Doc data1;
    OnItemClickListener mItemClickListener;

    public newsAdapter(Context myContext, List<Doc> dataSet) {
        context = myContext;
        data = dataSet;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_cardlist, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        data1 = data.get(position);
        if (data1.getMultimedia().size() != 0) {
            String url = "http://nytimes.com/" + (data1.getMultimedia().get(1).getUrl());
            Picasso.with(context).load(url).into(holder.imageView);
        }
        holder.txtTitle.setText(data1.getHeadline().getMain());
        holder.txtPublish.setText(data1.getSource());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle, txtPublish;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.imageview);
            txtTitle = (TextView) v.findViewById(R.id.texttitle);
            txtPublish = (TextView) v.findViewById(R.id.textpublish);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public List<Doc> getData() {
        return data;
    }
}
