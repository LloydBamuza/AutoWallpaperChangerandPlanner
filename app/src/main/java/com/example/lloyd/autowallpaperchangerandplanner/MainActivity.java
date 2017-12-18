package com.example.lloyd.autowallpaperchangerandplanner;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    static int duration;
    Intent incomming;
    static Context appContext;
    static ArrayList<Uri> imageFolderPaths;
    static int totNumImages;
    static Spinner spinner;
    final int IMAGE_DIR_RES = 808;
    Button btnApply, btnPickFrmFile, btnGal;
    ArrayAdapter<String> spinnerItems;
    String[] items = {"1 minute", "5 minutes", "10 minutes", "30 minutes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        incomming = getIntent();
        if ( incomming.getAction() == Intent.ACTION_SEND_MULTIPLE || incomming.getAction() == Intent.ACTION_SEND) {
            loadImages(RESULT_OK, incomming);
        }

        //Get Spinner reference
        spinner = (Spinner) findViewById(R.id.spinner);

        //Populate Spinner
        spinnerItems = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(spinnerItems);

        //Get button reference
        btnApply = (Button) findViewById(R.id.btnApply);
        btnPickFrmFile = (Button) findViewById(R.id.btnPickFile);
        btnGal = (Button) findViewById(R.id.btnGal);

        btnGal.setOnClickListener(e -> {

            Toast.makeText(getApplicationContext(), "Go to Gallary App, select multiple images and choose 'share via", Toast.LENGTH_LONG).show();

        });
        // Event Pick from file
        btnPickFrmFile.setOnClickListener(e -> {

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(Intent.createChooser(intent, "Pick"), IMAGE_DIR_RES);

        });

        btnApply.setOnClickListener(e -> {
            getDuration();
            startService(new Intent(this, Image_Changer_Service.class));
            Toast.makeText(getApplicationContext(),"Wallpaper changer started",Toast.LENGTH_LONG).show();
        });




    }
    public void getDuration() {
        switch (spinner.getSelectedItem().toString()) {

            case "1 minute":
                duration = 1;
                break;

            case "5 minutes":
                duration = 5;
                break;

            case "10 minutes":
                duration = 10;
                break;

            case "30 minutes":
                duration = 30;
                break;

        }
    }

    //Get and save Images' uri
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        appContext = getApplicationContext();
        loadImages(resultCode, data);

    }

    void loadImages(int rCode, Intent dta) {
        appContext = getApplicationContext();
        ClipData.Item item;
        imageFolderPaths = new ArrayList<Uri>();
        if (rCode == RESULT_OK) {
            ClipData clipUris = dta.getClipData();
            for (int i = 0; i < clipUris.getItemCount(); i++) {
                item = clipUris.getItemAt(i);

                imageFolderPaths.add(item.getUri());
                totNumImages++;
            }
            Toast.makeText(getApplicationContext(), totNumImages + " Images added", Toast.LENGTH_SHORT).show();

        }
    }
}





