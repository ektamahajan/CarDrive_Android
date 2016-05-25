package com.example.ekta.apfinalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class youtubeAdapter extends RecyclerView.Adapter<youtubeAdapter.VideoInfoHolder> {


    public static final List<com.example.ekta.apfinalproject.VideoEntry> VIDEO_LIST;
    public final List<com.example.ekta.apfinalproject.VideoEntry> entries;
    public static String key = "AIzaSyC8193ARBflT04gz9xJ207f5bPT103B-z0";
    Context ctx;
    String videoId;
    TextView title;
    Configuration display;


    static {
        List<com.example.ekta.apfinalproject.VideoEntry> list = new ArrayList<>();
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Porsche Cayenne | 5 Reasons to Buy | Autotrader", "89AdmrM8QKs"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Toyota Corolla | 5 Reasons to Buy | Autotrader", "2lbUz5NIPXw"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Toyota Highlander | 5 Reasons to Buy | Autotrader", "8cA0TqD45w8"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Lexus IS 350 F Sport | 5 Reasons to Buy | Autotrader", "vwpensRKbhk"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Porsche Macan | 5 Reasons to Buy | Autotrader", "DcwnhRNRUYo"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Dodge Challenger | 5 Reasons to Buy | Autotrader", "yAEvGYlxxIc"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Ford Focus | 5 Reasons to Buy | Autotrader", "tHetltaiKuU"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Honda Civic Sedan | 5 Reasons to Buy | Autotrader ", "NAU0S8sWzMw"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Lexus LS 460 | 5 Reasons to Buy | Autotrader", "tyDtIOAvTko"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Volvo V60 | 5 Reasons to Buy | Autotrader", "ZU3NtmS3UVI"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Ford Flex | 5 Reasons to Buy | Autotrader", "ekBKLzd1xaA"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Nissan Pathfinder | 5 Reasons to Buy | Autotrader", "a01QnviRtrE"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Chevrolet Colorado | 5 Reasons to Buy | Autotrader", "okOxiMqMzto"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Mazda 3 Grand Touring | 5 Reasons to Buy | Autotrader", "-fQdoruwpWk"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Ford F 150 | 5 Reasons to Buy | Autotrader", "I4pxFkDHtJE"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Ford Mustang GT | 5 Reasons to Buy | Autotrader", "odwYcf-dTLk"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Mercedes Benz C400 | 5 Reasons to Buy | Autotrader", "Fr5xMoP9w00"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Toyota Camry | 5 Reasons to Buy | Autotrader", "kjBArA_Pcp4"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Chevorlet Trax | 5 Reasons to Buy | Autotrader", "mSVSlFwRAXY"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Honda Odyssey | 5 Reasons to Buy | Autotrader", "unb65OYm0Uo"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Lincoln MKC | 5 Reasons to Buy | Autotrader", "uqcfv54Z0fs"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Toyota Prius | 5 Reasons to Buy | Autotrader", "I9KS9ZIF9sY"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Lexus RC-F | 5 Reasons to Buy | Autotrader", "mN7TSLJ3dZM"));
        list.add(new com.example.ekta.apfinalproject.VideoEntry("2016 Toyota Sienna | 5 Reasons to Buy | Autotrader", "pkkDCUQ_kGg"));

        VIDEO_LIST = Collections.unmodifiableList(list);
    }

    public youtubeAdapter(Context context, List<com.example.ekta.apfinalproject.VideoEntry> entries) {
        this.ctx = context;
        this.entries = entries;

    }

    @Override
    public VideoInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtubecardview, parent, false);
        return new VideoInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoInfoHolder holder, final int position) {

        videoId = VIDEO_LIST.get(position).videoId;
        com.example.ekta.apfinalproject.VideoEntry entry = VIDEO_LIST.get(position);
        title.setText(entry.text);

        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };


        holder.youTubeThumbnailView.initialize(key, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(videoId);
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });
    }

    @Override
    public int getItemCount() {
        return VIDEO_LIST.size();
    }

    public class VideoInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected LinearLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        protected ImageView playButton;


        public VideoInfoHolder(View itemView) {
            super(itemView);
            playButton = (ImageView) itemView.findViewById(R.id.btnYoutube_player);
            playButton.setOnClickListener(this);
            relativeLayoutOverYouTubeThumbnailView = (LinearLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);

        }

        @Override
        public void onClick(View v) {

            display = ctx.getResources().getConfiguration();
            if (display.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Intent intent1 = YouTubeStandalonePlayer.createVideoIntent((Activity) ctx, key, videoId, 0, true, false);
                ctx.startActivity(intent1);
            } else if (display.orientation == Configuration.ORIENTATION_PORTRAIT) {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) ctx, key, videoId, 0, true, true);
                ctx.startActivity(intent);
            }

        }


    }


}
