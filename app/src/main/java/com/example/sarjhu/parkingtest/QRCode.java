package com.example.sarjhu.parkingtest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;

import com.google.zxing.aztec.detector.Detector;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class QRCode extends AppCompatActivity implements View.OnClickListener{
    private Button scanBtn;

    private TextView tvScanFormat, tvScanContent;

    private LinearLayout llSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        scanBtn = (Button) findViewById(R.id.scan_button);

        tvScanFormat = (TextView) findViewById(R.id.tvScanFormat);

        tvScanContent = (TextView) findViewById(R.id.tvScanContent);

        llSearch = (LinearLayout) findViewById(R.id.llSearch);

        scanBtn.setOnClickListener(this);
    }

            public void onClick(View v) {
              llSearch.setVisibility(View.GONE);

              IntentIntegrator integrator = new IntentIntegrator(QRCode.this);

              integrator.setPrompt("Scan a barcode or QRcode");

              integrator.setOrientationLocked(false);

              integrator.initiateScan();
            }





    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {

            if (result.getContents() == null) {

                llSearch.setVisibility(View.GONE);

                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

            } else {

                llSearch.setVisibility(View.VISIBLE);

                tvScanContent.setText(result.getContents());

                tvScanFormat.setText(result.getFormatName());

            }

        } else {

            super.onActivityResult(requestCode, resultCode, data);

        }

    }

}