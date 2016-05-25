package com.example.ekta.apfinalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.firebase.client.Firebase;


public class CarDetailsFragment extends Fragment {

    public static final String ARG_USER = "user";
    com.example.ekta.apfinalproject.model.CarData carUser;
    EditText model;
    EditText milesDriven;
    EditText color;
    EditText price;
    Spinner brand;
    final Firebase ref = new Firebase("https://apfinalproject.firebaseio.com/cardata/");

    public static CarDetailsFragment newInstance(com.example.ekta.apfinalproject.model.CarData carUser) {
        CarDetailsFragment fragment = new CarDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, carUser);
        fragment.setArguments(args);
        return fragment;
    }

    public CarDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            carUser = (com.example.ekta.apfinalproject.model.CarData) getArguments().getSerializable(ARG_USER);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_car_details, container, false);
        brand = (Spinner) rootView.findViewById(R.id.editBrand);
        model = (EditText) rootView.findViewById(R.id.editModel);
        milesDriven = (EditText) rootView.findViewById(R.id.editMiles);
        color = (EditText) rootView.findViewById(R.id.editColor);
        price = (EditText) rootView.findViewById(R.id.editPrice);
        Button continueButton = (Button) rootView.findViewById(R.id.continueButton1);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brand1 = String.valueOf(brand.getSelectedItem());
                String model1 = model.getText().toString();
                String milesDriven1 = milesDriven.getText().toString();
                String color1 = color.getText().toString();
                String price1 = price.getText().toString();
                if (brand1.equals("") || model1.equals("") || milesDriven1.equals("")
                        || color1.equals("") || price1.equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    carUser.setBrand(brand1);
                    carUser.setModel(model1);
                    carUser.setMilesDriven(milesDriven1);
                    carUser.setColor(color1);
                    carUser.setPrice(price1);
                    ref.child(carUser.getId()).setValue(carUser);
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left)
                            .replace(R.id.createUserContainer, ImageUploadFragment.newInstance(carUser))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return rootView;
    }

}
