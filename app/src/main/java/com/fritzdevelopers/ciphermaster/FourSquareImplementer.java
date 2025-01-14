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

public class FourSquareImplementer extends AppCompatActivity {
    private TextInputLayout fourSquareEncrypterKeyOne;
    private TextInputLayout fourSquareEncrypterKeyTwo;
    private TextInputLayout fourSquareEncrypter;
    private TextInputLayout fourSquareDecrypterKeyOne;
    private TextInputLayout fourSquareDecrypterKeyTwo;
    private TextInputLayout fourSquareDecrypter;
    private TextView finalMessage;
    private TextView originalMessage;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_square_implementer);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        fourSquareEncrypterKeyOne = findViewById(R.id.four_square_key_encrypt_1);
        fourSquareEncrypterKeyTwo = findViewById(R.id.four_square_key_encrypt_2);
        fourSquareEncrypter = findViewById(R.id.four_square_encrypter);
        fourSquareDecrypterKeyOne = findViewById(R.id.four_square_key_decrypt_1);
        fourSquareDecrypterKeyTwo = findViewById(R.id.four_square_key_decrypt_2);
        fourSquareDecrypter = findViewById(R.id.four_square_decrypter);
        finalMessage = findViewById(R.id.four_square_encrypted_message);
        originalMessage = findViewById(R.id.four_square_decrypted_message);
    }

    public void fourSquareEncrypt(View v) {
        String encryptKeyOne = fourSquareEncrypterKeyOne.getEditText().getText().toString();
        String encryptKeyTwo = fourSquareEncrypterKeyTwo.getEditText().getText().toString();
        String messageToEncrypt = fourSquareEncrypter.getEditText().getText().toString();

        if (encryptKeyOne.isEmpty() && encryptKeyTwo.isEmpty() && messageToEncrypt.isEmpty()) {
            fourSquareEncrypterKeyOne.setError("No key has been provided");
            fourSquareEncrypterKeyTwo.setError("No key has been provided");
            fourSquareEncrypter.setError("There is no message to encrypt");
        } else if (encryptKeyOne.isEmpty() && encryptKeyTwo.isEmpty()) {
            fourSquareEncrypterKeyOne.setError("No key has been provided");
            fourSquareEncrypterKeyTwo.setError("No key has been provided");
        } else if (encryptKeyOne.isEmpty() && messageToEncrypt.isEmpty()) {
            fourSquareEncrypterKeyOne.setError("No key has been provided");
            fourSquareEncrypter.setError("There is no message to encrypt");
        } else if (encryptKeyTwo.isEmpty() && messageToEncrypt.isEmpty()) {
            fourSquareEncrypterKeyTwo.setError("No key has been provided");
            fourSquareEncrypter.setError("There is no message to encrypt");
        } else if (encryptKeyOne.isEmpty()) {
            fourSquareEncrypterKeyOne.setError("No key has been provided");
        } else if (encryptKeyTwo.isEmpty()) {
            fourSquareEncrypterKeyTwo.setError("No key has been provided");
        } else if (messageToEncrypt.isEmpty()) {
            fourSquareEncrypter.setError("There is no message to encrypt");
        }
        else {
            fourSquareEncrypterKeyOne.setError(null);
            fourSquareEncrypterKeyTwo.setError(null);
            fourSquareEncrypter.setError(null);
            //String encryptedMessage = "Encrypted";
            finalMessage.setText("Encrypted Message: \n" + FourSquareCipher.fourSquare(messageToEncrypt, encryptKeyOne, encryptKeyTwo));
        }
    }

    public void toFourSquareAbout(View view) {

        Intent fourSquare = new Intent(this, AboutFourSquare.class);
        startActivity(fourSquare);
    }

    public void fourSquareDecrypt(View v) {
        String decryptKeyOne = fourSquareDecrypterKeyOne.getEditText().getText().toString();
        String decryptKeyTwo = fourSquareDecrypterKeyTwo.getEditText().getText().toString();
        String messageToDecrypt = fourSquareDecrypter.getEditText().getText().toString();

        if (decryptKeyOne.isEmpty() && decryptKeyTwo.isEmpty() && messageToDecrypt.isEmpty()) {
            fourSquareDecrypterKeyOne.setError("No key has been provided");
            fourSquareDecrypterKeyTwo.setError("No key has been provided");
            fourSquareDecrypter.setError("There is no message to encrypt");
        } else if (decryptKeyOne.isEmpty() && decryptKeyTwo.isEmpty()) {
            fourSquareDecrypterKeyOne.setError("No key has been provided");
            fourSquareDecrypterKeyTwo.setError("No key has been provided");
        } else if (decryptKeyOne.isEmpty() && messageToDecrypt.isEmpty()) {
            fourSquareDecrypterKeyOne.setError("No key has been provided");
            fourSquareDecrypter.setError("There is no message to encrypt");
        } else if (decryptKeyTwo.isEmpty() && messageToDecrypt.isEmpty()) {
            fourSquareDecrypterKeyTwo.setError("No key has been provided");
            fourSquareDecrypter.setError("There is no message to encrypt");
        } else if (decryptKeyOne.isEmpty()) {
            fourSquareDecrypterKeyOne.setError("No key has been provided");
        } else if (decryptKeyTwo.isEmpty()) {
            fourSquareDecrypterKeyTwo.setError("No key has been provided");
        } else if (messageToDecrypt.isEmpty()) {
            fourSquareDecrypter.setError("There is no message to encrypt");
        }
        else {
            fourSquareDecrypterKeyOne.setError(null);
            fourSquareDecrypterKeyTwo.setError(null);
            fourSquareDecrypter.setError(null);
            //String decryptedMessage = "Encrypted";
            originalMessage.setText("Decrypted Message: \n" + FourSquareCipher.fourSquareDecrypt(messageToDecrypt, decryptKeyOne, decryptKeyTwo));
        }
    }

}