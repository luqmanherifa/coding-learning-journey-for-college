package com.stiki.luqman.ppb2_tugas3_cameraview;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new
                Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i,1);
    }
}