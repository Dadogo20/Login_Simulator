package com.example.loginsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.splash_screen);
        //To initiate the splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent to_login = new Intent(SplashScreen.this, LogIn.class);
                startActivity(to_login);
                finish();
            }
        }, 3000);
    }
}