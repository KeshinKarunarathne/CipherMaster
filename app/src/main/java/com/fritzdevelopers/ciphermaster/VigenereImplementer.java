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

public class VigenereImplementer extends AppCompatActivity {
    private TextInputLayout vigenereEncrypterKey;
    private TextInputLayout vigenereEncrypter;
    private TextInputLayout vigenereDecrypterKey;
    private TextInputLayout vigenereDecrypter;
    private TextView finalMessage;
    private TextView originalMessage;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere_implementer);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        vigenereEncrypterKey = findViewById(R.id.vigenere_key_encrypt);
        vigenereEncrypter = findViewById(R.id.vigenere_encrypter);
        vigenereDecrypterKey = findViewById(R.id.vigenere_key_decrypt);
        vigenereDecrypter = findViewById(R.id.vigenere_decrypter);
        finalMessage = findViewById(R.id.vigenere_encrypted_message);
        originalMessage = findViewById(R.id.vigenere_decrypted_message);
    }

    public void toVigenereAbout(View view) {

        Intent vigenere = new Intent(this, AboutVigenere.class);
        startActivity(vigenere);
    }

    public void vigenereEncrypt(View v) {
        String encryptKey = vigenereEncrypterKey.getEditText().getText().toString();
        String messageToEncrypt = vigenereEncrypter.getEditText().getText().toString();

        if (encryptKey.isEmpty() && messageToEncrypt.isEmpty()) {
            vigenereEncrypterKey.setError("No key has been provided");
            vigenereEncrypter.setError("There is no message to encrypt");
        }
        else if (encryptKey.isEmpty()) {
            vigenereEncrypterKey.setError("No key has been provided");
        }
        else if (messageToEncrypt.isEmpty()) {
            vigenereEncrypter.setError("There is no message to encrypt");
        }
        else {
            vigenereEncrypterKey.setError(null);
            vigenereEncrypter.setError(null);
            //String encryptedMessage = "Encrypted";
            finalMessage.setText("Encrypted Message: \n" + VigenereCipher.vigenere(messageToEncrypt, encryptKey));
        }
    }

    public void vigenereDecrypt(View v) {
        String decryptKey = vigenereDecrypterKey.getEditText().getText().toString();
        String messageToDecrypt = vigenereDecrypter.getEditText().getText().toString();

        if (decryptKey.isEmpty() && messageToDecrypt.isEmpty()) {
            vigenereDecrypterKey.setError("No key has been provided");
            vigenereDecrypter.setError("There is no message to decrypt");
        }
        else if (decryptKey.isEmpty()) {
            vigenereDecrypterKey.setError("No key has been provided");
        }
        else if (messageToDecrypt.isEmpty()) {
            vigenereDecrypter.setError("There is no message to decrypt");
        }
        else {
            vigenereDecrypterKey.setError(null);
            vigenereDecrypter.setError(null);
            //String decryptedMessage = "Decrypted";
            originalMessage.setText("Decrypted Message: \n" + VigenereCipher.vigenereDecrypt(messageToDecrypt, decryptKey));
        }
    }
}