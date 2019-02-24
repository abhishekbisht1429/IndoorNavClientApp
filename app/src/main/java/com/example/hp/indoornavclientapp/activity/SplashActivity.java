package com.example.hp.indoornavclientapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.indoornavclientapp.R;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_TIME_OUT = 3600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        },SPLASH_TIME_OUT);
    }
}
