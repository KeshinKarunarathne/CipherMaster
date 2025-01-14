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

public class ColumnarImplementer extends AppCompatActivity {
    private TextInputLayout columnarEncrypterKey;
    private TextInputLayout columnarEncrypter;
    private TextInputLayout columnarDecrypterKey;
    private TextInputLayout columnarDecrypter;
    private TextView finalMessage;
    private TextView originalMessage;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_columnar_implementer);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        columnarEncrypterKey = findViewById(R.id.columnar_key_encrypt);
        columnarEncrypter = findViewById(R.id.columnar_encrypter);
        columnarDecrypterKey = findViewById(R.id.columnar_key_decrypt);
        columnarDecrypter = findViewById(R.id.columnar_decrypter);
        finalMessage = findViewById(R.id.columnar_encrypted_message);
        originalMessage = findViewById(R.id.columnar_decrypted_message);

    }

    public void toColumnarAbout(View view) {

        Intent columnar = new Intent(this, AboutColumnar.class);
        startActivity(columnar);
    }

    public void columnarEncrypt(View v) {
        String encryptKey = columnarEncrypterKey.getEditText().getText().toString();
        String messageToEncrypt = columnarEncrypter.getEditText().getText().toString();

        if (encryptKey.isEmpty() && messageToEncrypt.isEmpty()) {
            columnarEncrypterKey.setError("No key has been provided");
            columnarEncrypter.setError("There is no message to encrypt");
        }
        else if (encryptKey.isEmpty()) {
            columnarEncrypterKey.setError("No key has been provided");
        }
        else if (messageToEncrypt.isEmpty()) {
            columnarEncrypter.setError("There is no message to encrypt");
            //Complete the comparison between the length of the message and the key
        }
        else {
            columnarEncrypterKey.setError(null);
            columnarEncrypter.setError(null);
            //String encryptedMessage = "Encrypted";
            finalMessage.setText("Encrypted Message: \n" + ColumnarTranspositionCipher.columnarTrans(messageToEncrypt, encryptKey));
        }
    }

    public void columnarDecrypt(View v) {
        String decryptKey = columnarDecrypterKey.getEditText().getText().toString();
        String messageToDecrypt = columnarDecrypter.getEditText().getText().toString();

        if (decryptKey.isEmpty() && messageToDecrypt.isEmpty()) {
            columnarDecrypterKey.setError("No key has been provided");
            columnarDecrypter.setError("There is no message to decrypt");
        }
        else if (decryptKey.isEmpty()) {
            columnarDecrypterKey.setError("No key has been provided");
        }
        else if (messageToDecrypt.isEmpty()) {
            columnarDecrypter.setError("There is no message to decrypt");
            //Complete the comparison between the length of the message and the key
        }
        else if (!CommonMethods.messageVerified(messageToDecrypt, decryptKey)) {
            columnarDecrypter.setError("The message to decrypt is invalid. The reason for this may be one of the following: \n 1. The length of the message is not a multiple of the length of the key \n 2. Each term in your message may not have an equal length \n 3. The message may consist of more than one term even though the length of the key matches that of the message");
        }
        else {
            columnarDecrypterKey.setError(null);
            columnarDecrypter.setError(null);
            //String decryptedMessage = "Decrypted";
            originalMessage.setText("Decrypted Message: \n" + ColumnarTranspositionCipher.columnarTransDecrypt(messageToDecrypt, decryptKey));
        }
    }
}