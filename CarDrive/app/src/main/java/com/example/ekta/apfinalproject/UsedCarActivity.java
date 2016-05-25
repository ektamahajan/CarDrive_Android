package com.example.ekta.apfinalproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ekta.apfinalproject.UsedCarFragment;
import com.example.ekta.apfinalproject.UsedCarPriceFragment;

import java.util.Locale;

public class UsedCarActivity extends AppCompatActivity {

    ViewPager mViewPager;
    MyFragmentPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_car);

        myPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), 2);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(myPagerAdapter);
        //mViewPager.setPageTransformer(true, new CubeOutTransformer());
        mViewPager.setCurrentItem(0);
        customizeViewPager();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void customizeViewPager() {
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalized_position = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalized_position / 2 + 0.5f);
                page.setScaleY(normalized_position / 2 + 0.5f);
            }
        });
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        int count;

        public MyFragmentPagerAdapter(FragmentManager fm, int size) {
            super(fm);
            count = size;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return UsedCarFragment.newInstance();
            else if (position == 1)
                return UsedCarPriceFragment.newInstance();
            else
                return UsedCarFragment.newInstance();
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            String name;
            if (position == 0)
                name = "Search by brand";
            else
                name = "Search by price";
            return name.toUpperCase(l);
        }
    }
}
