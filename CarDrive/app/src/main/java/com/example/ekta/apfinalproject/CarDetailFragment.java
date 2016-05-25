package com.example.ekta.apfinalproject;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;


public class CarDetailFragment extends Fragment {

    private static final String ARG_CAR = "movie";
    com.example.ekta.apfinalproject.model.CarData carData;
    HashMap<String, ?> car1;
    public static final String TAG = MainActivity.class.getSimpleName();
    private GoogleMap googleMap;
    ShareActionProvider mShareActionProvide;

    public interface OnListItemSelectedListener {
        public void onListItemSelected(HashMap<String, ?> car);
    }

    public static CarDetailFragment newInstance(HashMap<String, ?> car) {
        CarDetailFragment movieDataFragment = new CarDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAR, car);
        movieDataFragment.setArguments(args);
        return movieDataFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);

        if (getArguments() != null) {
            car1 = (HashMap<String, ?>) getArguments().getSerializable(ARG_CAR);
        }
    }

    public CarDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_car_detail, container, false);

        final TextView brand = (TextView) rootView.findViewById(R.id.carBrand);
        final TextView model = (TextView) rootView.findViewById(R.id.modelYear);
        final TextView color = (TextView) rootView.findViewById(R.id.carColor);
        final TextView mileage = (TextView) rootView.findViewById(R.id.carMileage);
        final TextView description = (TextView) rootView.findViewById(R.id.carPriceTitle);
        final TextView price = (TextView) rootView.findViewById(R.id.carPrice);
        final TextView address = (TextView) rootView.findViewById(R.id.carAddress);
        final TextView contact = (TextView) rootView.findViewById(R.id.carContact);
        final ImageView carImage = (ImageView) rootView.findViewById(R.id.carImage);
        final TextView title = (TextView) rootView.findViewById(R.id.carNameDetail);
        final TextView titlemiles = (TextView) rootView.findViewById(R.id.carNameTitleMiles);
        final Button contactSeller = (Button) rootView.findViewById(R.id.sellerContact);
        final Button addressSeller = (Button) rootView.findViewById(R.id.sellerAddress);


        String carBrand = (String) car1.get("brand");
        brand.setText(carBrand);
        String carModel = (String) car1.get("model");
        model.setText(carModel);
        String carColor = (String) car1.get("color");
        color.setText(carColor);
        String carMileage = (String) car1.get("milesDriven");
        mileage.setText(carMileage);
        String carDescription = (String) car1.get("price");
        description.setText("$" + " " + carDescription);
        String carPrice = (String) car1.get("price");
        price.setText(carPrice);
        final String carAddress = car1.get("address") + " " + car1.get("city") + " " + car1.get("state");
        address.setText(carAddress);
        final String carContact = (String) car1.get("number");
        contact.setText(carContact);
        title.setText(carBrand + " " + carModel);
        titlemiles.setText(carMileage + " miles");
        contactSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phone = new Intent(Intent.ACTION_DIAL);
                phone.setData(Uri.parse("tel:" + carContact));
                startActivity(phone);

            }
        });
        addressSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog alertDialog = new Dialog(getContext()); //Read Update
                alertDialog.setContentView(R.layout.custom1);
                alertDialog.setTitle(carAddress);

                try {
                    if (googleMap == null) {
                        googleMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.google_map)).getMap();

                        if (googleMap == null) {
                            Toast.makeText(getContext(), "Error creating map", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (NullPointerException exception) {
                    Log.e("mapApp", exception.toString());
                }

                configureMap(googleMap);
                LatLng address1 = geocodeAddress(carAddress);
                googleMap.addMarker(new MarkerOptions().position(address1));
                CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(address1, 14);
                googleMap.animateCamera(camera);
                alertDialog.show();

            }
        });

        //Integer moviesImage = (Integer)movie.get("image");
        //movieImage.setImageResource(moviesImage);
        String imageFile = (String) car1.get("url");
        byte[] decodedString = Base64.decode(imageFile, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        if (decodedByte != null)
            carImage.setImageBitmap(decodedByte);
        else
            carImage.setImageResource(R.drawable.audi);
        /*try {
            if (googleMap == null) {
                googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map)).getMap();

                if (googleMap == null) {
                    Toast.makeText(getContext(), "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }

                configureMap(googleMap);
                LatLng address1 = geocodeAddress(carAddress);
                googleMap.addMarker(new MarkerOptions().position(address1));
                CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(address1, 14);
                googleMap.animateCamera(camera);*/

        return rootView;

    }

    private void configureMap(GoogleMap map) {
        if (map == null)
            return;
        try {
            MapsInitializer.initialize(getContext());
        } catch (Exception e) {
            Log.e(TAG, "Have GoogleMap but then error", e);
            return;
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {

        }

        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
    }

    LatLng geocodeAddress(String place) {
        Geocoder gc = new Geocoder(getContext());
        LatLng latlng;
        if (gc.isPresent()) {
            try {
                List<Address> list = gc.getFromLocationName(place, 1);
                Address address = list.get(0);
                double lat = address.getLatitude();
                double lng = address.getLongitude();
                latlng = new LatLng(lat, lng);
                return latlng;
            } catch (Exception e) {

            }
        }

        return null;
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater)
    {
        inflater.inflate(R.menu.share_movie,menu);

        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvide = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        //intentShare.setType("image/*");
        intentShare.putExtra(Intent.EXTRA_TEXT, (String) movie.get("name"));
        mShareActionProvide.setShareIntent(intentShare);

        super.onCreateOptionsMenu(menu, inflater);
    }*/


}

