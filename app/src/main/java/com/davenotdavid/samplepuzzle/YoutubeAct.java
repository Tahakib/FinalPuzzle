package com.davenotdavid.samplepuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeAct extends YouTubeBaseActivity {

    private static final String API_KEY="AIzaSyAKuf-BlmDwseGuuaBROVRK8MjKyT1eLoc";
    YouTubePlayerView mYoutubePlayerView;
    Button btnPlay;
    Button homepage;
    YouTubePlayer.OnInitializedListener mOnInitializeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        homepage=(Button)findViewById(R.id.homeact);
        mYoutubePlayerView=(YouTubePlayerView)findViewById(R.id.youtubeplay);
        // mYoutubePlayerView=(YouTubePlayerView)findViewById(R.id.youtubeplay);
        mOnInitializeListener= new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("gHkELWFqGKQ");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        mYoutubePlayerView.initialize(API_KEY,mOnInitializeListener);

        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome();
            }
        });

    }
    public void openhome()
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
