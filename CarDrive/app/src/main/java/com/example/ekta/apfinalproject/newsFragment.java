package com.example.ekta.apfinalproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.example.ekta.apfinalproject.newsService;
import com.example.ekta.apfinalproject.model.Doc;
import com.example.ekta.apfinalproject.model.NewsResponse;
import com.example.ekta.apfinalproject.newsAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class newsFragment extends Fragment {

    RecyclerView newsRecyclerView;
    Doc data1;
    LinearLayoutManager newsLayoutManager;
    OnListItemSelectedListener mListener;
    com.example.ekta.apfinalproject.newsAdapter adapter;
    NewsResponse newsResp;

    public newsFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_news_detail_view, container, false);
        newsRecyclerView = (RecyclerView) rootView.findViewById(R.id.news_cardList);
        newsRecyclerView.setHasFixedSize(true);
        newsLayoutManager = new LinearLayoutManager(getActivity());
        newsRecyclerView.setLayoutManager(newsLayoutManager);
        try {
            mListener = (OnListItemSelectedListener) getContext();

        } catch (ClassCastException e) {
            throw new ClassCastException("The hosting activity of the Fragment" +
                    "forget to implement onFragmentInteraction Listener");
        }

        data1 = new Doc();

        if (!connectInternet()) {
            Toast.makeText(getActivity(), "Verify internet access", Toast.LENGTH_SHORT).show();
            return null;
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nytimes.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsService service = retrofit.create(newsService.class);

        Map<String, String> params = new HashMap<>();
        params.put("fq", "section_name:(\"Automobiles\")ANDnews_desk:(\"cars\")");
        params.put("api-key", "55adc2b814f2f34921bfd2091233032a:7:74911914");

        service.articleSearch(params).enqueue(new retrofit2.Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, retrofit2.Response<NewsResponse> response) {

                newsResp = response.body();
                adapter = new newsAdapter(getActivity(), newsResp.getResponse().getDocs());
                newsRecyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new newsAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View v, int position) {

                        mListener.OnListItemSelected(position, adapter.getData());
                    }

                });
                adapterAnimation();
                itemAnimation();
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("API CALL FAILED", error.getMessage());
            }
        });
        return rootView;
    }

    public boolean connectInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public interface OnListItemSelectedListener {
        public void OnListItemSelected(int position, List<Doc> data);

    }

    public void adapterAnimation() {
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);

        newsRecyclerView.setAdapter(scaleAdapter);
    }

    private void itemAnimation() {
        SlideInLeftAnimator animator = new SlideInLeftAnimator();
        animator.setInterpolator(new OvershootInterpolator());

        animator.setAddDuration(500);
        animator.setRemoveDuration(500);

        newsRecyclerView.setItemAnimator(animator);
    }

}

