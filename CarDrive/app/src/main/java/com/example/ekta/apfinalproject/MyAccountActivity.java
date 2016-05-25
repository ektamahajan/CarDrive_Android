package com.example.ekta.apfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ekta.apfinalproject.model.CarData;
import com.example.ekta.apfinalproject.model.CarDetailFragment;
import com.example.ekta.apfinalproject.model.CarMap;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAccountActivity extends AppCompatActivity implements com.example.ekta.apfinalproject.MyRecyclerFragment.OnListItemSelectedListener {

    CarData carData = new CarData();
    android.support.v7.widget.Toolbar mToolBar;
    ActionBar mActionBar;
    List<Map<String, ?>> carList;
    public Firebase ref;
    String id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Bundle bundle = getIntent().getExtras();
        if ((CarData) bundle.getSerializable("CarData") != null) {
            carData = (CarData) bundle.getSerializable("CarData");
            id1 = carData.getId();
            carList = new ArrayList<Map<String, ?>>();
            Firebase.setAndroidContext(getApplicationContext());
            ref = new Firebase("https://apfinalproject.firebaseio.com/cardata/");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        CarData carData1 = messageSnapshot.getValue(CarData.class);
                        String name = carData1.getName();
                        String description = carData1.getDescription();
                        String id = carData1.getId();
                        String brand = carData1.getBrand();
                        String email = carData1.getEmail();
                        String url = carData1.getUrl();
                        String model = carData1.getModel();
                        String price = carData1.getPrice();
                        String number = carData1.getNumber();
                        String address = carData1.getAddress();
                        String milesDriven = carData1.getMilesDriven();
                        String color = carData1.getColor();
                        String city = carData1.getCity();
                        String state = carData1.getState();
                        if (id.equals(id1))
                            carList.add(CarMap.createCar(name, description, id, brand, email, url, model, price,
                                    number, address, milesDriven, color, city, state));
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
        } else if (bundle.get("loginId") != null) {
            id1 = (String) bundle.get("loginId");
            id1 = id1.replace(".", "");
            carList = new ArrayList<Map<String, ?>>();
            Firebase.setAndroidContext(getApplicationContext());
            ref = new Firebase("https://apfinalproject.firebaseio.com/cardata/");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        CarData carData1 = messageSnapshot.getValue(CarData.class);
                        String name = carData1.getName();
                        String description = carData1.getDescription();
                        String id = carData1.getId();
                        String brand = carData1.getBrand();
                        String email = carData1.getEmail();
                        String url = carData1.getUrl();
                        String model = carData1.getModel();
                        String price = carData1.getPrice();
                        String number = carData1.getNumber();
                        String address = carData1.getAddress();
                        String milesDriven = carData1.getMilesDriven();
                        String color = carData1.getColor();
                        String city = carData1.getCity();
                        String state = carData1.getState();
                        if (id.equals(id1))
                            carList.add(CarMap.createCar(name, description, id, brand, email, url, model, price,
                                    number, address, milesDriven, color, city, state));
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
        }
        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.run();

        mToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_rec_toolbar);
        setSupportActionBar(mToolBar);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(false);
        setUpToolBarItemSelected();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.my_recycle_container, com.example.ekta.apfinalproject.MyRecyclerFragment.newInstance(carList))
                    .commit();
        }
    }

    public void onListItemSelected(int position, HashMap<String, ?> car) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.my_recycle_container, CarDetailFragment.newInstance(car))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.myaccount, menu);
        return true;
    }

    void setUpToolBarItemSelected() {
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.action_logout:
                        ref.unauth();
                        Intent myIntent = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.MainActivity.class);
                        startActivity(myIntent);
                        return true;
                    case R.id.action_home:
                        Intent myIntent1 = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.MainActivity.class);
                        startActivity(myIntent1);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
