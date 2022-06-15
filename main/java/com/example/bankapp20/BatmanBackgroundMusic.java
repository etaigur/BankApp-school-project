package com.example.bankapp20;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class BatmanBackgroundMusic extends Service {

    MediaPlayer player;

    public BatmanBackgroundMusic() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.batman);
        player.setLooping(true);
        player.setVolume(0.03f, 0.03f);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void onStop() { player.stop(); }
}