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

public class BifidImplementer extends AppCompatActivity {
    private TextInputLayout bifidEncrypterKey;
    private TextInputLayout bifidEncrypter;
    private TextInputLayout bifidDecrypterKey;
    private TextInputLayout bifidDecrypter;
    private TextView finalMessage;
    private TextView originalMessage;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bifid_implementer);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        bifidEncrypterKey = findViewById(R.id.bifid_key_encrypt);
        bifidEncrypter = findViewById(R.id.bifid_encrypter);
        bifidDecrypterKey = findViewById(R.id.bifid_key_decrypt);
        bifidDecrypter = findViewById(R.id.bifid_decrypter);
        finalMessage = findViewById(R.id.bifid_encrypted_message);
        originalMessage = findViewById(R.id.bifid_decrypted_message);
    }

    public void toBifidAbout(View view) {

        Intent bifid = new Intent(this, AboutBifid.class);
        startActivity(bifid);
    }

    public void bifidEncrypt(View v) {
        String encryptKey = bifidEncrypterKey.getEditText().getText().toString();
        String messageToEncrypt = bifidEncrypter.getEditText().getText().toString();

        if (encryptKey.isEmpty() && messageToEncrypt.isEmpty()) {
            bifidEncrypterKey.setError("No key has been provided");
            bifidEncrypter.setError("There is no message to encrypt");
        }
        else if (encryptKey.isEmpty()) {
            bifidEncrypterKey.setError("No key has been provided");
        }
        else if (messageToEncrypt.isEmpty()) {
            bifidEncrypter.setError("There is no message to encrypt");
        }
        else {
            bifidEncrypterKey.setError(null);
            bifidEncrypter.setError(null);
            //String encryptedMessage = "Encrypted";
            finalMessage.setText("Encrypted Message: \n" + BifidCipher.bifid(messageToEncrypt, encryptKey));
        }
    }

    public void bifidDecrypt(View v) {
        String decryptKey = bifidDecrypterKey.getEditText().getText().toString();
        String messageToDecrypt = bifidDecrypter.getEditText().getText().toString();

        if (decryptKey.isEmpty() && messageToDecrypt.isEmpty()) {
            bifidDecrypterKey.setError("No key has been provided");
            bifidDecrypter.setError("There is no message to decrypt");
        }
        else if (decryptKey.isEmpty()) {
            bifidDecrypterKey.setError("No key has been provided");
        }
        else if (messageToDecrypt.isEmpty()) {
            bifidDecrypter.setError("There is no message to decrypt");
        }
        else {
            bifidDecrypterKey.setError(null);
            bifidDecrypter.setError(null);
            //String decryptedMessage = "Decrypted";
            originalMessage.setText("Decrypted Message: \n" + BifidCipher.bifidDecrypt(messageToDecrypt, decryptKey));
        }
    }


}