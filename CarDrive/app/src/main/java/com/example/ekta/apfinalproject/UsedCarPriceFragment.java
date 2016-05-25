package com.example.ekta.apfinalproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;


public class UsedCarPriceFragment extends Fragment {

    private Spinner spinner1;
    private Button btnSubmit;

    public static UsedCarPriceFragment newInstance() {
        UsedCarPriceFragment fragment = new UsedCarPriceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public UsedCarPriceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_used_car_price, container, false);
        addListenerOnButton(rootView);
        addListenerOnSpinnerItemSelection(rootView);
        return rootView;
    }

    public void addListenerOnSpinnerItemSelection(View view) {
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton(View view) {

        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), com.example.ekta.apfinalproject.UsedCarRecyclerActivity.class);
                intent.putExtra("positionPrice", spinner1.getSelectedItemPosition());
                intent.putExtra("position", -1);
                startActivity(intent);
            }

        });
    }

}
