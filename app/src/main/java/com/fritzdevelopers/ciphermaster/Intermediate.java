package com.fritzdevelopers.ciphermaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Intermediate extends AppCompatActivity {

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void toAffineAbout(View view) {

        Intent affine = new Intent(this, AboutAffine.class);
        startActivity(affine);
    }

    public void toAutokeyAbout(View view) {

        Intent autokey = new Intent(this, AboutAutokey.class);
        startActivity(autokey);
    }

    public void toBifidAbout(View view) {

        Intent bifid = new Intent(this, AboutBifid.class);
        startActivity(bifid);
    }

    public void toCaesarAbout(View view) {

        Intent caesar = new Intent(this, AboutCaesar.class);
        startActivity(caesar);
    }

    public void toColumnarAbout(View view) {

        Intent columnar = new Intent(this, AboutColumnar.class);
        startActivity(columnar);
    }

    public void toFourSquareAbout(View view) {

        Intent fourSquare = new Intent(this, AboutFourSquare.class);
        startActivity(fourSquare);
    }

    public void toPlayfairAbout(View view) {

        Intent playfair = new Intent(this, AboutPlayfair.class);
        startActivity(playfair);
    }

    public void toVigenereAbout(View view) {

        Intent vigenere = new Intent(this, AboutVigenere.class);
        startActivity(vigenere);
    }

    public void toXORAbout(View view) {

        Intent xor = new Intent(this, AboutXOR.class);
        startActivity(xor);
    }
}