package com.example.lloyd.autowallpaperchangerandplanner;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.widget.Spinner;

import java.util.Random;


public class Image_Changer_Service extends Service {
    int newWallpaper = 0;
    Random randImage = new Random();
    Spinner spinner;
    int wallpaperChangePeriod = 0;


    public Image_Changer_Service() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //get spinner reference


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new WallpaperLoop();
        return super.onStartCommand(intent, flags, startId);
    }


}


