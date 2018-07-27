package com.example.sarjhu.parkingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Otpgen extends AppCompatActivity {

    Button generateRandom;
    TextView randomResult;
    Random myRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpgen);
        generateRandom = (Button)findViewById(R.id.generate);
        randomResult = (TextView)findViewById(R.id.randomresult);

        generateRandom.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String result = "";
                myRandom = new Random();


                    result += String.valueOf(myRandom.nextInt()) + "\n";
               result=result.substring(0,4);

                randomResult.setText(result);
            }});

    }
}

