package com.fritzdevelopers.ciphermaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static int WELCOME_TIMEOUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent(MainActivity.this, Intermediate.class);
                startActivity(welcome);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, WELCOME_TIMEOUT);
    }
}