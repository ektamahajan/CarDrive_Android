package com.example.ekta.apfinalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ekta.apfinalproject.model.ActivityAboutUs;
import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    android.support.v7.widget.Toolbar mtoolbar;
    DrawerLayout drawerLayout;
    final Firebase ref = new Firebase("https://apfinalproject.firebaseio.com/cardata/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtoolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.rec_toolbar);
        setSupportActionBar(mtoolbar);


        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, mtoolbar, R.string.open_drawer, R.string.close_drawer) {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                    }
                };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        Button sellCar = (Button) findViewById(R.id.sellCarButton);
        Button usedCar = (Button) findViewById(R.id.usedCarButton);
        Button video = (Button) findViewById(R.id.videosButton);
        Button news = (Button) findViewById(R.id.newsButton);
        ImageButton sound = (ImageButton) findViewById(R.id.soundbutton);
        sellCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);


                Intent intent = new Intent(MainActivity.this, com.example.ekta.apfinalproject.LoginActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(MainActivity.this, v.findViewById(R.id.sellCarButton), "testAnimation");
                    MainActivity.this.startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });


        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(MainActivity.this, com.example.ekta.apfinalproject.NewsActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(MainActivity.this, v.findViewById(R.id.newsButton), "testAnimation1");
                    MainActivity.this.startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        usedCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), UsedCarActivity.class);
//                startActivity(intent);


                Intent intent = new Intent(MainActivity.this, com.example.ekta.apfinalproject.UsedCarActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(MainActivity.this, view.findViewById(R.id.usedCarButton), "testAnimation2");
                    MainActivity.this.startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
//                startActivity(intent);


                Intent intent = new Intent(MainActivity.this, com.example.ekta.apfinalproject.YoutubeActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(MainActivity.this, view.findViewById(R.id.videosButton), "testAnimation3");
                    MainActivity.this.startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.carstarting);
                mp.start();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.homeItem1:
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.usedCarItem3:
                Intent intentUsed = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.UsedCarActivity.class);
                startActivity(intentUsed);
                break;
            case R.id.sellCarItem3:
                Intent myIntent3 = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.LoginActivity.class);
                startActivity(myIntent3);
                break;
            case R.id.newsItem3:
                Intent myIntent4 = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.NewsActivity.class);
                startActivity(myIntent4);
                break;
            case R.id.videosItem3:
                Intent myIntent5 = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.YoutubeActivity.class);
                startActivity(myIntent5);
                break;
            case R.id.feedbackItem3:
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create(); //Read Update
                alertDialog.setTitle("Feedback");
                alertDialog.setMessage("Want to give us a feedback? Click Continue!");

                alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"ektamahajan.cse@gmail.com", "ekta.dhawan32@gmail.com"});
                        email.putExtra(Intent.EXTRA_SUBJECT, "Feedback for CarDrive!");
                        email.setType("message/rfc822");
                        startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    }
                });

                alertDialog.show();
                break;
            case R.id.aboutUsItem3:
                Intent aboutIntent = new Intent(getApplicationContext(), ActivityAboutUs.class);
                startActivity(aboutIntent);
                break;
            case R.id.myAccountItem2:
                Intent myIntent1 = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.LoginActivity.class);
                startActivity(myIntent1);
                break;
            case R.id.settingsItem3:
                Intent myIntent2 = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.SettingsActivity.class);
                startActivity(myIntent2);
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
