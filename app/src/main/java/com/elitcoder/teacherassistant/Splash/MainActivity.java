package com.elitcoder.teacherassistant.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.elitcoder.teacherassistant.Login.LoginActivity;
import com.elitcoder.teacherassistant.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //For Splash Screen
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent  goTtoLoginActivity = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(goTtoLoginActivity);
            }
        },1500);
    }
}