package com.example.ekta.apfinalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Splash2 extends Fragment {

    public static Splash2 newInstance() {
        Splash2 movieDataFragment = new Splash2();
        return movieDataFragment;
    }

    public Splash2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash2, container, false);
    }

}
