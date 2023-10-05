package com.stiki.luqman.ppb2_tugas5_scanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission_group.CAMERA;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA =1;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);

        setContentView(scannerView);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkPermission())
            {
                Toast.makeText(MainActivity.this, "Permission is Granted!", Toast.LENGTH_LONG).show();
            }
				else
				{
					requestPermission();
				}
			}
		}

		private boolean checkPermission()
		{
			return (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);

		}
			private void requestPermission()
			{
				ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
			}

            public void onRequestPermissionResult(int requestCode, String permission[] , int grantResult[])
            {
             switch(requestCode)
             {
                 case REQUEST_CAMERA :
                     if(grantResult.length > 0)
                     {
                         boolean cameraAccepted = grantResult[0] == PackageManager.PERMISSION_GRANTED;
                         if (cameraAccepted)
                         {
                             Toast.makeText(MainActivity.this , "Permission Granted", Toast.LENGTH_LONG).show();
                         }
                         else
                         {
                             Toast.makeText(MainActivity.this , "Permission Denied", Toast.LENGTH_LONG).show();
                             if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                             {
                                 if(shouldShowRequestPermissionRationale(CAMERA))
                                 {
                                     displayAlertMessage ("You need to allow access for both permission",
                                             new DialogInterface.OnClickListener() {
                                         @Override
                                         public void onClick(DialogInterface dialogInterface, int i) {
                                             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                                             requestPermissions(new String[]{CAMERA},REQUEST_CAMERA);
                                         }
                                     }});
                                     return;

                                 }
                             }
                         }
                     }
                     break;
                }
            }

            @Override
            public void onResume() {
                super.onResume();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkPermission()) {

                        if (scannerView == null) {
                            scannerView = new ZXingScannerView(this);
                            setContentView(scannerView);
                        }
                        scannerView.setResultHandler(this);
                        scannerView.startCamera();
                    } else {
                        requestPermission();
                    }
                }
            }

        @Override
        public void onDestroy() {
            super.onDestroy();
            scannerView.stopCamera();
        }
            public void displayAlertMessage(String message, DialogInterface.OnClickListener Listener)
            {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(message)
                        .setPositiveButton("OK", Listener)
                        .setNegativeButton("CANCEL", null)
                        .create()
                        .show();
            }
    @Override
    public void handleResult(final Result result) {
    final String scanResult = result.getText();
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Scan Result");
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            scannerView.resumeCameraPreview(MainActivity.this);
        }
    });
    builder.setNeutralButton("Kunjungi Link", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scanResult));
            startActivity(intent);
        }
    });
    builder.setMessage(scanResult);
    AlertDialog alert = builder.create();
    alert.show();
    }
}
