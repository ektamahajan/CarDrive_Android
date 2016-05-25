package com.example.ekta.apfinalproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Splash3 extends Fragment {

    public static Splash3 newInstance() {
        Splash3 movieDataFragment = new Splash3();
        return movieDataFragment;
    }

    public Splash3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_splash3, container, false);
        Button button = (Button) rootView.findViewById(R.id.continueApp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), com.example.ekta.apfinalproject.MainActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
