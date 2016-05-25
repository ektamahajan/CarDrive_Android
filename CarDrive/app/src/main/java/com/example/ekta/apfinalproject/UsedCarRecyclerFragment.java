package com.example.ekta.apfinalproject;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ekta.apfinalproject.model.CarData;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsedCarRecyclerFragment extends Fragment {

    public static final String ARG_SEC_NUMBER = "section_number";
    RecyclerView mRecyclerView;
    FragmentActivity mActivity;
    CarData carData;
    List<Map<String, ?>> carList;
    RecyclerView.LayoutManager mLayoutManager;
    MyCarRecyclerViewAdapter mRecyclerViewAdapter;


    public UsedCarRecyclerFragment() {
        // Required empty public constructor
    }


    public static UsedCarRecyclerFragment newInstance(List<Map<String, ?>> carList1) {
        UsedCarRecyclerFragment rfragment = new UsedCarRecyclerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SEC_NUMBER, (Serializable) carList1);
        rfragment.setArguments(args);
        return rfragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);
        carList = (List<Map<String, ?>>) getArguments().getSerializable(ARG_SEC_NUMBER);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_used_car_recycler, container, false);

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

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerViewAdapter = new MyCarRecyclerViewAdapter(getActivity(), carList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        setRetainInstance(true);
        final OnListItemSelectedListener mListener;
        try {
            mListener = (OnListItemSelectedListener) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException("The hosting activity of the fragment" +
                    "forgot to implement onFragmentInteractionListener");
        }
        mRecyclerViewAdapter.setOnItemClickListener(new MyCarRecyclerViewAdapter.OnItemClickListener() {

                                                        @Override
                                                        public void onItemClick(View v, int position) {

                                                            HashMap<String, ?> car1 = (HashMap<String, ?>) carList.get(position);
                                                            mListener.onListItemSelected(position, car1);

                                                        }

                                                        @Override
                                                        public void onOverFlowMenuClicked(View v, final int position) {


                                                        }

                                                    }
        );
        adapterAnimation();
        itemAnimation();

        return rootView;
    }

    public interface OnListItemSelectedListener {
        void onListItemSelected(int position, HashMap<String, ?> car);
    }

    public void adapterAnimation() {
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mRecyclerViewAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);

        mRecyclerView.setAdapter(scaleAdapter);
    }

    private void itemAnimation() {
        SlideInLeftAnimator animator = new SlideInLeftAnimator();
        animator.setInterpolator(new OvershootInterpolator());

        animator.setAddDuration(500);
        animator.setRemoveDuration(500);

        mRecyclerView.setItemAnimator(animator);
    }

}

class MyCarRecyclerViewAdapter extends RecyclerView.Adapter<MyCarRecyclerViewAdapter.ViewHolder> {

    public List<Map<String, ?>> mDataSet;
    private Context mContext;
    OnItemClickListener mItemClickListner;

    public MyCarRecyclerViewAdapter(Context myContext, List<Map<String, ?>> myDataSet) {
        mContext = myContext;
        mDataSet = myDataSet;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onOverFlowMenuClicked(View view, int position);
    }


    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListner = mItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        return 0;
    }

    @Override
    public MyCarRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            default:
                v = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.cardview, parent, false);
                break;
        }
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView vIcon;
        public TextView vTitle;
        public TextView vDescription;
        public TextView vPrice;
        public ImageView vOverflow;
        public TextView vMiles;
        public TextView vCity;

        public ViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.iconMovie);
            vTitle = (TextView) v.findViewById(R.id.titleCar);
            vDescription = (TextView) v.findViewById(R.id.titlemodel);
            vPrice = (TextView) v.findViewById(R.id.titleprice);
            vOverflow = (ImageView) v.findViewById(R.id.cardImageView);
            vMiles = (TextView) v.findViewById(R.id.titlemiles);
            vCity = (TextView) v.findViewById(R.id.titlecity);

            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mItemClickListner != null) {
                        mItemClickListner.onItemClick(v, getAdapterPosition());
                    }
                }
            });

            if (vOverflow != null) {
                vOverflow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListner != null) {
                            mItemClickListner.onOverFlowMenuClicked(v, getAdapterPosition());
                        }
                    }
                });
            }
        }

        public void bindMovieData(Map<String, ?> car) {
            vTitle.setText((String) car.get("brand"));
            vDescription.setText((String) car.get("model"));
            vPrice.setText("$" + (String) car.get("price"));
            vMiles.setText((String) car.get("milesDriven") + " miles");
            vCity.setText((String) car.get("city"));
            String imageFile = (String) car.get("url");
            if (imageFile != null) {
                byte[] decodedString = Base64.decode(imageFile, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                vIcon.setImageBitmap(decodedByte);
            } else
                Picasso.with(mContext).load(R.drawable.audi).into(vIcon);
        }

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, ?> car = mDataSet.get(position);
        holder.bindMovieData(car);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
