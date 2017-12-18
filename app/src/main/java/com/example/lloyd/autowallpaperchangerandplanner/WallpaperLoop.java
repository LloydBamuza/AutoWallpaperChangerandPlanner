package com.example.lloyd.autowallpaperchangerandplanner;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import static com.example.lloyd.autowallpaperchangerandplanner.MainActivity.appContext;
import static com.example.lloyd.autowallpaperchangerandplanner.MainActivity.duration;
import static com.example.lloyd.autowallpaperchangerandplanner.MainActivity.imageFolderPaths;
import static com.example.lloyd.autowallpaperchangerandplanner.MainActivity.totNumImages;


/**
 * Created by lloyd on 2017/12/18.
 */

public class WallpaperLoop extends Activity {
    Random randImage;
    int newWallpaper = 0;
    Uri newWallpaperUri = null;


    WallpaperLoop()
    {

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    try {
                        Thread.sleep(duration * (1000 * 60));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    randImage = new Random(totNumImages);

                    //get new wallpaper uri
                    if (!(newWallpaper == totNumImages - 1)) {

                        newWallpaper++;
                        newWallpaperUri = imageFolderPaths.get(newWallpaper);
                    } else {
                        newWallpaper = 0;
                        newWallpaperUri = imageFolderPaths.get(newWallpaper);
                    }

                    WallpaperManager wpm = null;
                    //get wallpaper manager
                    try {
                        wpm = WallpaperManager.getInstance(appContext);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    //create bitmap
                    Bitmap w = null;
                    try {
                        w = MediaStore.Images.Media.getBitmap(appContext.getContentResolver(), newWallpaperUri);
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "Image not found", Toast.LENGTH_LONG).show();
                    }


                    //finally,set the wallpaper
                    try {
                        wpm.setBitmap(w);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();

    }
}

