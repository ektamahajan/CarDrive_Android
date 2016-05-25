package com.example.ekta.apfinalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CreateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left)
                .replace(R.id.createUserContainer, CreateUserFragment.newInstance(username))
                .commit();
    }
}
