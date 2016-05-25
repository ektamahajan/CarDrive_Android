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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.ekta.apfinalproject.model.CarData;
import com.example.ekta.apfinalproject.model.CarMap;
import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;



public class MyRecyclerFragment extends Fragment {

    CarData carUser;
    public static final String ARG_SEC_NUMBER = "section_number";
    RecyclerView mRecyclerView;
    FragmentActivity mActivity;
    RecyclerView.LayoutManager mLayoutManager;
    List<Map<String, ?>> carList;
    CarMap carMap;
    MyAccountRecyclerViewAdapter myAccountRecylerAdapter;
    Firebase ref = new Firebase("https://apfinalproject.firebaseio.com/cardata/");

    public MyRecyclerFragment() {
        // Required empty public constructor
    }


    public static MyRecyclerFragment newInstance(List<Map<String, ?>> carList1) {
        MyRecyclerFragment rfragment = new MyRecyclerFragment();
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
        carMap = new CarMap();

        if (getArguments() != null) {
            carList = (List<Map<String, ?>>) getArguments().getSerializable(ARG_SEC_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_my_recycler, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        myAccountRecylerAdapter = new MyAccountRecyclerViewAdapter(getActivity(), carList);
        mRecyclerView.setAdapter(myAccountRecylerAdapter);
        setRetainInstance(true);
        final OnListItemSelectedListener mListener;
        try {
            mListener = (OnListItemSelectedListener) getContext();
        } catch (ClassCastException e) {
            throw new ClassCastException("The hosting activity of the fragment" +
                    "forgot to implement onFragmentInteractionListener");
        }
        myAccountRecylerAdapter.setOnItemClickListener(new MyAccountRecyclerViewAdapter.OnItemClickListener() {

                                                           @Override
                                                           public void onItemClick(View v, int position) {

                                                               //CarData carData1 = myFirebaseRecylerAdapter.getItem(position);
                                                               HashMap<String, ?> car1 = (HashMap<String, ?>) carList.get(position);
                                                               mListener.onListItemSelected(position, car1);

                                                           }

                                                           @Override
                                                           public void onOverFlowMenuClicked(View v, final int position) {
                                                               PopupMenu popup = new PopupMenu(getActivity(), v);
                                                               popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                                                   @Override
                                                                   public boolean onMenuItemClick(MenuItem item) {
                                                                       switch (item.getItemId()) {
                                                                           case R.id.item_delete:
                                                                               HashMap<String, ?> car1 = (HashMap<String, ?>) carList.get(position);
                                                                               ref.child((String) car1.get("id")).removeValue();
                                                                               carList.remove(position);
                                                                               myAccountRecylerAdapter.notifyItemRemoved(position);
                                                                               return true;
                                                                           default:
                                                                               return false;
                                                                       }
                                                                   }
                                                               });
                                                               MenuInflater inflater = popup.getMenuInflater();
                                                               inflater.inflate(R.menu.actionlongclicked, popup.getMenu());
                                                               popup.show();
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
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(myAccountRecylerAdapter);
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

class MyAccountRecyclerViewAdapter extends RecyclerView.Adapter<MyAccountRecyclerViewAdapter.ViewHolder> {

    public List<Map<String, ?>> mDataSet;
    private Context mContext;
    OnItemClickListener mItemClickListner;

    public MyAccountRecyclerViewAdapter(Context myContext, List<Map<String, ?>> myDataSet) {
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
    public MyAccountRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        public ImageView vOverflow;
        public TextView vMiles;
        public TextView vCity;
        public TextView vPrice;

        public ViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.iconMovie);
            vTitle = (TextView) v.findViewById(R.id.titleCar);
            vPrice = (TextView) v.findViewById(R.id.titleprice);
            vDescription = (TextView) v.findViewById(R.id.titlemodel);
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
            vDescription.setText((String) car.get("description"));
            vMiles.setText((String) car.get("milesDriven") + " miles");
            vPrice.setText("$" + (String) car.get("price"));
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
