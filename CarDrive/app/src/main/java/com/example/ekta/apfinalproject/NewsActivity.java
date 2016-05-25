package com.example.ekta.apfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.ekta.apfinalproject.UsedCarActivity;
import com.example.ekta.apfinalproject.YoutubeActivity;
import com.example.ekta.apfinalproject.model.DetailContainerFragment;
import com.example.ekta.apfinalproject.newsFragment;
import com.example.ekta.apfinalproject.model.Doc;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.List;


public class NewsActivity extends AppCompatActivity implements newsFragment.OnListItemSelectedListener {

    private boolean serviceWillBeDismissed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        serviceWillBeDismissed = false;

//        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(findViewById(R.id.main_container), "Heythere!", Snackbar.LENGTH_LONG).show();
//            }
//        });


        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
// repeat many times:
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(getResources().getDrawable(R.drawable.homeblack));

        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageDrawable(getResources().getDrawable(R.drawable.usedcarblack));

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageDrawable(getResources().getDrawable(R.drawable.videoblack));

        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon1).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon2).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(fab)
                .build();

        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {

            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                if (serviceWillBeDismissed) {
                    serviceWillBeDismissed = true;
                }
            }
        });

        itemIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UsedCarActivity.class);
                startActivity(intent);


            }
        });

        itemIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                startActivity(intent);

            }
        });
        itemIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.MainActivity.class);
                startActivity(homeIntent);
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new newsFragment())
                .commit();
    }

    public void OnListItemSelected(int position, List<Doc> data) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, DetailContainerFragment.newInstance(data, position))
                .addToBackStack(null)
                .commit();

    }

}


