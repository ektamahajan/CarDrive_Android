package com.example.ekta.apfinalproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.example.ekta.apfinalproject.model.Doc;


import java.util.List;


public class DetailContainerFragment extends Fragment {

    ViewPager pager;
    newsDetailsViewAdapter adapter;
    static List<Doc> datalist;
    static int position1;

    public static DetailContainerFragment newInstance(List<Doc> data, int position) {
        datalist = data;
        position1 = position;
        Bundle args = new Bundle();
        DetailContainerFragment fragment = new DetailContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DetailContainerFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.news_details_container, container, false);

        pager = (ViewPager) rootView.findViewById(R.id.viewpager);
        adapter = new newsDetailsViewAdapter(getChildFragmentManager(), datalist, position1);
        pager.setAdapter(adapter);
        pager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
        pager.setCurrentItem(position1);


        return rootView;


    }
}
