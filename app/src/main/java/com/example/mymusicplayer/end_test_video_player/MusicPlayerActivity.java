package com.example.mymusicplayer.end_test_video_player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymusicplayer.R;

import java.io.IOException;

public class MusicPlayerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private String musicUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        musicUrl = getIntent().getStringExtra("url");

        TextView musicTitleTextView = findViewById(R.id.musicTitleTextView);
        musicTitleTextView.setText(getMusicTitleFromUrl(musicUrl));

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(musicUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    private String getMusicTitleFromUrl(String musicUrl) {
        // 解析音乐文件的标题
        // ...
        return null;
    }
}