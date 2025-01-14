package com.fritzdevelopers.ciphermaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class AboutBifid extends AppCompatActivity {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_bifid);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4383335608939724/3988876580");
        AdRequest request = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(request);

        //Creating the button that activates the interstitial ad
        Button buttonToBifidImplementer = (Button) findViewById(R.id.toBifidImplementer);
        buttonToBifidImplementer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else {
                    BifidImplementer();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Load the next interstitial.
                        BifidImplementer();
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    }
                });

            }
        });
    }

    public void toIntermediate(View view) {

        Intent intermediate = new Intent(this, Intermediate.class);
        startActivity(intermediate);
    }

    public void BifidImplementer() {

        Intent bifidImplementer = new Intent(this, BifidImplementer.class);
        startActivity(bifidImplementer);
    }
}