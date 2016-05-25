package com.example.ekta.apfinalproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ekta.apfinalproject.newsDetailView;
import com.example.ekta.apfinalproject.model.Doc;

import java.util.List;


public class newsDetailsViewAdapter extends FragmentStatePagerAdapter {

    private List<Doc> data;
    int position;

    //  Doc data1;
    public newsDetailsViewAdapter(FragmentManager fm, List<Doc> dataSet, int position1) {
        super(fm);
        data = dataSet;
        position = position1;

    }

    @Override
    public Fragment getItem(int position) {

        return newsDetailView.newInstance(data.get(position));
    }

    @Override
    public int getCount() {
        return data.size();
    }

}