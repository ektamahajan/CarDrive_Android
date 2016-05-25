package com.example.ekta.apfinalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;

/**
 * Created by newuser on 4/27/16.
 */
public class SplashScreen extends AppCompatActivity {
    ViewPager mViewPager;
    MyFragmentPagerAdapter myPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        myPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), 3);
        mViewPager = (ViewPager) findViewById(R.id.pager1);
        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setPageTransformer(true, new FlipHorizontalTransformer());
        mViewPager.setCurrentItem(0);
        //customizeViewPager();
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
                return com.example.ekta.apfinalproject.Splash1.newInstance();
            else if (position == 1)
                return com.example.ekta.apfinalproject.Splash2.newInstance();
            else if (position == 2)
                return com.example.ekta.apfinalproject.Splash3.newInstance();
            else
                return com.example.ekta.apfinalproject.Splash1.newInstance();
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return null;
        }
    }
}
