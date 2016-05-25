package com.example.ekta.apfinalproject;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import java.io.IOException;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityAboutUs extends AppCompatActivity {

    @Bind(R.id.recycler_view11)
    protected RecyclerView mRecyclerView;

    @Bind(R.id.toolbar11)
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        String[] dataSet = null;
        try {
            dataSet = getAssets().list("demo-pictures");
        } catch (IOException e) {
            e.printStackTrace();
        }
        com.example.ekta.apfinalproject.PhotoAdapter adapter = new com.example.ekta.apfinalproject.PhotoAdapter(dataSet, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin);
            }
        });
        mRecyclerView.setAdapter(adapter);
        YoYo.with(Techniques.RollIn)
                .duration(1500)
                .playOn(findViewById(R.id.aboutUSTitle));
    }
}
