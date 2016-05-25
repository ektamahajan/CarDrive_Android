package com.example.ekta.apfinalproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ekta.apfinalproject.model.Doc;
import com.squareup.picasso.Picasso;


public class newsDetailView extends Fragment {

    public static Context context;


    public static newsDetailView newInstance(Doc dataItem) {

        Bundle args = new Bundle();
        args.putString("title", dataItem.getHeadline().getMain());
        args.putString("publish", dataItem.getSource());
        if (dataItem.getMultimedia().size() != 0) {
            String url = "http://nytimes.com/" + (dataItem.getMultimedia().get(1).getUrl());
            args.putString("image", url);
        }
        args.putString("content", dataItem.getLeadParagraph());
        newsDetailView fragment = new newsDetailView();
        fragment.setArguments(args);
        return fragment;
    }

    public newsDetailView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_detail_view, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageview);
        TextView txtTitle = (TextView) rootView.findViewById(R.id.texttitle);
        TextView txtPublish = (TextView) rootView.findViewById(R.id.textpublish);
        TextView txtContent = (TextView) rootView.findViewById(R.id.textcontent);

        if (getArguments().getString("image") != null) {
            Picasso.with(context).load(getArguments().getString("image")).into(imageView);
        }

        txtTitle.setText(getArguments().getString("title"));
        txtPublish.setText(getArguments().getString("publish"));
        txtContent.setText(getArguments().getString("content"));

        if (!connectInternet()) {
            Toast.makeText(getActivity(), "Verify internet access", Toast.LENGTH_SHORT).show();
            return null;
        }

        return rootView;
    }

    public boolean connectInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }

    }
}




