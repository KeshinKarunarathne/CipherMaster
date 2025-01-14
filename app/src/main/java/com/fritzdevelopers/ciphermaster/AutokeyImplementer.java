package com.fritzdevelopers.ciphermaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputLayout;

public class AutokeyImplementer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextInputLayout autokeyEncrypter;
    private TextInputLayout autokeyDecrypter;
    private TextView finalMessage;
    private TextView originalMessage;
    String key_for_encryption;
    String key_for_decryption;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autokey_implementer);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Initialising the spinner displaying the letters in the alphabet for the encrypter
        Spinner autokey_spinner_encrypt = findViewById(R.id.autokey_spinner_encrypt);
        ArrayAdapter<CharSequence> autokey_adapter_encrypt = ArrayAdapter.createFromResource(this, R.array.alphabet, android.R.layout.simple_spinner_item);
        autokey_adapter_encrypt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autokey_spinner_encrypt.setAdapter(autokey_adapter_encrypt);
        autokey_spinner_encrypt.setOnItemSelectedListener(this);

        //Initialising the spinner displaying the letters in the alphabet for the decrypter
        Spinner autokey_spinner_decrypt = findViewById(R.id.autokey_spinner_decrypt);
        ArrayAdapter<CharSequence> autokey_adapter_decrypt = ArrayAdapter.createFromResource(this, R.array.alphabet, android.R.layout.simple_spinner_item);
        autokey_adapter_decrypt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autokey_spinner_decrypt.setAdapter(autokey_adapter_decrypt);
        autokey_spinner_decrypt.setOnItemSelectedListener(this);

        autokeyEncrypter = findViewById(R.id.autokey_encrypter);
        autokeyDecrypter = findViewById(R.id.autokey_decrypter);
        finalMessage = findViewById(R.id.autokey_encrypted_message);
        originalMessage = findViewById(R.id.autokey_decrypted_message);
    }

    public void toAutokeyAbout(View view) {

        Intent autokey = new Intent(this, AboutAutokey.class);
        startActivity(autokey);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.autokey_spinner_encrypt) {
            key_for_encryption = adapterView.getItemAtPosition(i).toString();
        }
            if (adapterView.getId() == R.id.autokey_spinner_decrypt) {
                key_for_decryption = adapterView.getItemAtPosition(i).toString();
            }
        }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void autokeyEncrypt(View v) {
        String messageToEncrypt = autokeyEncrypter.getEditText().getText().toString();

        if (messageToEncrypt.isEmpty()) {
            autokeyEncrypter.setError("There is no message to encrypt");
        }
        else {
            autokeyEncrypter.setError(null);
            //String encryptedMessage = "Encrypted";
            finalMessage.setText("Encrypted Message: \n" + AutokeyCipher.autokey(messageToEncrypt, key_for_encryption.charAt(0)));
        }
    }

    public void autokeyDecrypt(View v) {
        String messageToDecrypt = autokeyDecrypter.getEditText().getText().toString();

        if (messageToDecrypt.isEmpty()) {
            autokeyDecrypter.setError("There is no message to decrypt");
        }
        else {
            autokeyDecrypter.setError(null);
            String decryptedMessage = "Decrypted";
            originalMessage.setText("Decrypted Message: \n" + AutokeyCipher.autokeyDecrypt(messageToDecrypt, key_for_decryption.charAt(0)));
        }
    }
}