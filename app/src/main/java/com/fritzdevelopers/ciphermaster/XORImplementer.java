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

public class XORImplementer extends AppCompatActivity {
    private TextInputLayout xorEncrypterKey;
    private TextInputLayout xorEncrypter;
    private TextInputLayout xorDecrypterKey;
    private TextInputLayout xorDecrypter;
    private TextView finalMessage;
    private TextView originalMessage;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_o_r_implementer);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        xorEncrypterKey = findViewById(R.id.xor_key_encrypt);
        xorEncrypter = findViewById(R.id.xor_encrypter);
        xorDecrypterKey = findViewById(R.id.xor_key_decrypt);
        xorDecrypter = findViewById(R.id.xor_decrypter);
        finalMessage = findViewById(R.id.xor_encrypted_message);
        originalMessage = findViewById(R.id.xor_decrypted_message);
    }

    public void toXORAbout(View view) {

        Intent xor = new Intent(this, AboutXOR.class);
        startActivity(xor);
    }

    public void xorEncrypt(View v) {
        String encryptKey = xorEncrypterKey.getEditText().getText().toString();
        String messageToEncrypt = xorEncrypter.getEditText().getText().toString();

        if (encryptKey.isEmpty() && messageToEncrypt.isEmpty()) {
            xorEncrypterKey.setError("No key has been provided");
            xorEncrypter.setError("There is no message to encrypt");
        }
        else if (encryptKey.isEmpty()) {
            xorEncrypterKey.setError("No key has been provided");
        }
        else if (messageToEncrypt.isEmpty()) {
            xorEncrypter.setError("There is no message to encrypt");
        }
        else {
            xorEncrypterKey.setError(null);
            xorEncrypter.setError(null);
            //String encryptedMessage = "Encrypted";
            finalMessage.setText("Encrypted Message: \n" + XORCipher.XOR(messageToEncrypt, encryptKey.charAt(0)));
        }
    }

    public void xorDecrypt(View v) {
        String decryptKey = xorDecrypterKey.getEditText().getText().toString();
        String messageToDecrypt = xorDecrypter.getEditText().getText().toString();

        if (decryptKey.isEmpty() && messageToDecrypt.isEmpty()) {
            xorDecrypterKey.setError("No key has been provided");
            xorDecrypter.setError("There is no message to decrypt");
        }
        else if (decryptKey.isEmpty()) {
            xorDecrypterKey.setError("No key has been provided");
        }
        else if (messageToDecrypt.isEmpty()) {
            xorDecrypter.setError("There is no message to decrypt");
        }
        else {
            xorDecrypterKey.setError(null);
            xorDecrypter.setError(null);
            //String decryptedMessage = "Decrypted";
            originalMessage.setText("Decrypted Message: \n" + XORCipher.XORDecrypt(messageToDecrypt, decryptKey.charAt(0)));
        }
    }
}