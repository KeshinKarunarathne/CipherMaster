package com.fritzdevelopers.ciphermaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputLayout;

public class PlayfairImplementer extends AppCompatActivity {
    private TextInputLayout playfairEncrypter;
    private TextInputLayout playfairDecrypter;
    private TextView finalMessage;
    private TextView originalMessage;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playfair_implementer);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        playfairEncrypter = findViewById(R.id.playfair_encrypter);
        playfairDecrypter = findViewById(R.id.playfair_decrypter);
        finalMessage = findViewById(R.id.playfair_encrypted_message);
        originalMessage = findViewById(R.id.playfair_decrypted_message);
    }

    public void toPlayfairAbout(View view) {

        Intent playfair = new Intent(this, AboutPlayfair.class);
        startActivity(playfair);
    }

    public void playfairEncrypt(View v) {
        String messageToEncrypt = playfairEncrypter.getEditText().getText().toString();

        if (messageToEncrypt.isEmpty()) {
            playfairEncrypter.setError("There is no message to encrypt");
        }
        else {
            playfairEncrypter.setError(null);
            //String encryptedMessage = "Encrypted";
            finalMessage.setText("Encrypted Message: \n" + PlayfairCipher.playfair(messageToEncrypt));
        }
    }

    public void playfairDecrypt(View v) {
        String messageToDecrypt = playfairDecrypter.getEditText().getText().toString();

        if (messageToDecrypt.isEmpty()) {
            playfairDecrypter.setError("There is no message to decrypt");
        }
        else {
            playfairDecrypter.setError(null);
            //String decryptedMessage = "Decrypted";
            originalMessage.setText("Decrypted Message: \n" + PlayfairCipher.playfairDecrypt(messageToDecrypt));
        }
    }
}