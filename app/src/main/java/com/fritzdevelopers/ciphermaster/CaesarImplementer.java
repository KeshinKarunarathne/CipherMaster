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

public class CaesarImplementer extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private TextInputLayout caesarEncrypter;
    private TextInputLayout caesarDecrypter;
    private TextView finalMessage;
    private TextView originalMessage;
    int key_encrypt;
    int key_decrypt;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_implementer);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Initialising the spinner displaying the possible values for the shift in the encrypter
        Spinner caesar_spinner_encrypt = findViewById(R.id.caesar_spinner_key_encrypt);
        ArrayAdapter<CharSequence> caesar_adapter_encrypt = ArrayAdapter.createFromResource(this, R.array.caesar_shift_values, android.R.layout.simple_spinner_item);
        caesar_adapter_encrypt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        caesar_spinner_encrypt.setAdapter(caesar_adapter_encrypt);
        caesar_spinner_encrypt.setOnItemSelectedListener(this);

        //Initialising the spinner displaying the possible values for the shift in the decrypter
        Spinner caesar_spinner_decrypt = findViewById(R.id.caesar_spinner_key_decrypt);
        ArrayAdapter<CharSequence> caesar_adapter_decrypt = ArrayAdapter.createFromResource(this, R.array.caesar_shift_values, android.R.layout.simple_spinner_item);
        caesar_adapter_decrypt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        caesar_spinner_decrypt.setAdapter(caesar_adapter_decrypt);
        caesar_spinner_decrypt.setOnItemSelectedListener(this);

        caesarEncrypter = findViewById(R.id.caesar_encrypter);
        caesarDecrypter = findViewById(R.id.caesar_decrypter);
        finalMessage = findViewById(R.id.caesar_encrypted_message);
        originalMessage = findViewById(R.id.caesar_decrypted_message);
    }

    public void toCaesarAbout(View view) {

        Intent caesar = new Intent(this, AboutCaesar.class);
        startActivity(caesar);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getId() == R.id.caesar_spinner_key_encrypt) {
            key_encrypt = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
        }

        if (adapterView.getId() == R.id.caesar_spinner_key_decrypt) {
            key_decrypt = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void caesarEncrypt(View v) {
        String messageToEncrypt = caesarEncrypter.getEditText().getText().toString();

        if (messageToEncrypt.isEmpty()) {
            caesarEncrypter.setError("There is no message to encrypt");
        }
        else {
            caesarEncrypter.setError(null);
            finalMessage.setText("Encrypted Message: \n" + CaesarCipher.caesar(messageToEncrypt, key_encrypt));
        }
    }

    public void caesarDecrypt(View v) {
        String messageToDecrypt = caesarDecrypter.getEditText().getText().toString();

        if (messageToDecrypt.isEmpty()) {
            caesarDecrypter.setError("There is no message to encrypt");
        }
        else {
            caesarDecrypter.setError(null);
            originalMessage.setText("Decrypted Message: \n" + CaesarCipher.caesarDecrypt(messageToDecrypt, key_decrypt));
        }
    }


}