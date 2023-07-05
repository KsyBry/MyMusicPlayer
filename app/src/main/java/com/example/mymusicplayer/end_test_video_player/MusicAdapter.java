package com.example.mymusicplayer.end_test_video_player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mymusicplayer.R;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends BaseAdapter {
    private Context context;
    private List<String> musicUrls;
    private List<String> musicTitles;

    public MusicAdapter(Context context) {
        this.context = context;
        // 设置播放列表数据
        musicUrls = new ArrayList<>();
        musicUrls.add("音乐1的URL");
        musicUrls.add("音乐2的URL");
        // ...

        musicTitles = new ArrayList<>();
        musicTitles.add("音乐1的标题");
        musicTitles.add("音乐2的标题");
        // ...
    }

    public String getMusicUrl(int position) {
        return musicUrls.get(position);
    }

    public String getMusicTitle(int position) {
        return musicTitles.get(position);
    }

    @Override
    public int getCount() {
        return musicUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView musicTitleTextView = convertView.findViewById(R.id.musicTitleTextView);
        musicTitleTextView.setText(musicTitles.get(position));

        return convertView;
    }
}