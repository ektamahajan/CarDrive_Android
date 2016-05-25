package com.example.ekta.apfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ekta.apfinalproject.SplashScreen;

import java.util.Timer;
import java.util.TimerTask;

public class SplaashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splaash);
        final ImageView img = (ImageView) findViewById(R.id.imageView);
        final TextView text = (TextView) findViewById(R.id.textCar);
        final Animation ani = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        img.setAnimation(ani);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(getBaseContext(), SplashScreen.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
//                finish();
//                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}