package com.example.ekta.apfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.example.ekta.apfinalproject.model.CarData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsedCarRecyclerActivity extends AppCompatActivity implements com.example.ekta.apfinalproject.UsedCarRecyclerFragment.OnListItemSelectedListener {

    private boolean serviceWillBeDismissed;

    List<Map<String, ?>> carList;
    public Firebase ref;
    int position;
    int positionPrice;
    android.support.v7.widget.Toolbar mToolBar;
    ActionBar mActionBar;
    String[] arr = {"Maruti", "Hyundai", "Honda", "Audi",
            "Mercedes", "Acura", "Jaguar", "Jissan",
            "Tata", "Toyota", "BMW", "Chevrolet",
            "Renault", "Volkswagen", "Ford", "Mahendra"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_car_recycler);

        position = (int) getIntent().getSerializableExtra("position");
        positionPrice = (int) getIntent().getSerializableExtra("positionPrice");
        carList = new ArrayList<Map<String, ?>>();
        Firebase.setAndroidContext(getApplicationContext());
        ref = new Firebase("https://apfinalproject.firebaseio.com/cardata/");
        if (positionPrice == -1) {
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
                        if (arr[position].equals(brand)) {
                            carList.add(CarMap.createCar(name, description, id, brand, email, url, model, price,
                                    number, address, milesDriven, color, city, state));
                        }
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
        } else if (position == -1) {
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
                        int price1 = Integer.parseInt(price);
                        if (positionPrice == 0 && price1 < 5000) {
                            carList.add(CarMap.createCar(name, description, id, brand, email, url, model, price,
                                    number, address, milesDriven, color, city, state));
                        } else if (positionPrice == 1 && price1 > 5000 && price1 < 10000) {
                            carList.add(CarMap.createCar(name, description, id, brand, email, url, model, price,
                                    number, address, milesDriven, color, city, state));
                        } else if (positionPrice == 2 && price1 > 10000 && price1 < 20000) {
                            carList.add(CarMap.createCar(name, description, id, brand, email, url, model, price,
                                    number, address, milesDriven, color, city, state));
                        } else if (positionPrice == 3 && price1 > 20000 && price1 < 50000) {
                            carList.add(CarMap.createCar(name, description, id, brand, email, url, model, price,
                                    number, address, milesDriven, color, city, state));
                        } else if (positionPrice == 4 && price1 > 50000 && price1 < 100000) {
                            carList.add(CarMap.createCar(name, description, id, brand, email, url, model, price,
                                    number, address, milesDriven, color, city, state));
                        } else if (positionPrice == 5 && price1 > 100000) {
                            carList.add(CarMap.createCar(name, description, id, brand, email, url, model, price,
                                    number, address, milesDriven, color, city, state));
                        }
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
        mToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.rec_toolbar);
        setSupportActionBar(mToolBar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        serviceWillBeDismissed = false;


        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(getResources().getDrawable(R.drawable.homeblack));

        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageDrawable(getResources().getDrawable(R.drawable.newsblack));

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
                Intent intent = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.NewsActivity.class);
                startActivity(intent);


            }
        });

        itemIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.ekta.apfinalproject.YoutubeActivity.class);
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

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(false);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.recycle_container, com.example.ekta.apfinalproject.UsedCarRecyclerFragment.newInstance(carList))
                    .commit();
        }
    }

    public void onListItemSelected(int position, HashMap<String, ?> car) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recycle_container, CarDetailFragment.newInstance(car))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rec_toolbar, menu);
        return true;
    }
}
