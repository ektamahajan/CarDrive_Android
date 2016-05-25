package com.example.ekta.apfinalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.firebase.client.Firebase;

public class CreateUserFragment extends Fragment {

    public static final String ARG_USERNAME = "username";
    String username;
    EditText name;
    EditText number;
    EditText address;
    EditText city;
    EditText state;
    com.example.ekta.apfinalproject.model.CarData carUser;
    String id;

    final Firebase ref = new Firebase("https://apfinalproject.firebaseio.com/cardata/");

    public static CreateUserFragment newInstance(String username) {
        CreateUserFragment fragment = new CreateUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    public CreateUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            username = (String) getArguments().getSerializable(ARG_USERNAME);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_create_user, container, false);
        carUser = new com.example.ekta.apfinalproject.model.CarData();
        id = username.replace(".", "");
        carUser.setId(id);
        carUser.setEmail(username);
        ref.child(carUser.getId()).setValue(carUser);
        name = (EditText) rootView.findViewById(R.id.editName);
        number = (EditText) rootView.findViewById(R.id.editNumber);
        address = (EditText) rootView.findViewById(R.id.editAddress);
        city = (EditText) rootView.findViewById(R.id.editCity);
        state = (EditText) rootView.findViewById(R.id.editState);
        Button continueButton = (Button) rootView.findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String number1 = number.getText().toString();
                String address1 = address.getText().toString();
                String city1 = city.getText().toString();
                String state1 = state.getText().toString();
                if (name1.equals("") || number1.equals("") || address1.equals("")
                        || city1.equals("") || state1.equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    carUser.setName(name.getText().toString());
                    carUser.setNumber(number.getText().toString());
                    carUser.setAddress(address1);
                    carUser.setCity(city1);
                    carUser.setState(state1);
                    ref.child(carUser.getId()).setValue(carUser);
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left)
                            .replace(R.id.createUserContainer, com.example.ekta.apfinalproject.CarDetailsFragment.newInstance(carUser))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return rootView;
    }

}
