package com.example.mymusicplayer.end_test_video_player;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicDownloadService extends IntentService {
    private static final String TAG = "MusicDownloadService";

    public MusicDownloadService() {
        super("MusicDownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String musicUrl = intent.getStringExtra("url");
        downloadMusic(musicUrl);
    }

    private void downloadMusic(String musicUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(musicUrl)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // 从URL中获取文件名
            String fileName = getFileNameFromUrl(musicUrl);
            // 创建保存文件的目录
            File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Music");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // 创建保存文件的路径
            File file = new File(directory, fileName);

            // 读取响应的输入流并写入文件
            InputStream inputStream = response.body().byteStream();
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // 关闭流
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Log.e(TAG, "Error occurred during music download", e);
        }
    }

    private String getFileNameFromUrl(String url) {
        String[] segments = url.split("/");
        return segments[segments.length - 1];
    }
}
