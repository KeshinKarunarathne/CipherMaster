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

public class AffineImplementer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextInputLayout affineEncrypter;
    private TextInputLayout affineDecrypter;
    private TextView finalMessage;
    private TextView originalMessage;
    int a_value_encrypt;
    int b_value_encrypt;
    int a_value_decrypt;
    int b_value_decrypt;

    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affine_implementer);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Initialising the spinner displaying the values of a in the encrypter
        Spinner a_spinner_encrypt = findViewById(R.id.affine_spinner_a_encrypt);
        ArrayAdapter<CharSequence> a_adapter_encrypt = ArrayAdapter.createFromResource(this, R.array.a_values, android.R.layout.simple_spinner_item);
        a_adapter_encrypt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a_spinner_encrypt.setAdapter(a_adapter_encrypt);
        a_spinner_encrypt.setOnItemSelectedListener(this);

        //Initialising the spinner displaying the values of b in the encrypter
        Spinner b_spinner_encrypt = findViewById(R.id.affine_spinner_b_encrypt);
        ArrayAdapter<CharSequence> b_adapter_encrypt = ArrayAdapter.createFromResource(this, R.array.b_values, android.R.layout.simple_spinner_item);
        b_adapter_encrypt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b_spinner_encrypt.setAdapter(b_adapter_encrypt);
        b_spinner_encrypt.setOnItemSelectedListener(this);

        //Initialising the spinner displaying the values of a in the decrypter
        Spinner a_spinner_decrypt = findViewById(R.id.affine_spinner_a_decrypt);
        ArrayAdapter<CharSequence> a_adapter_decrypt = ArrayAdapter.createFromResource(this, R.array.a_values, android.R.layout.simple_spinner_item);
        a_adapter_decrypt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a_spinner_decrypt.setAdapter(a_adapter_decrypt);
        a_spinner_decrypt.setOnItemSelectedListener(this);

        //Initialising the spinner displaying the values of b in the decrypter
        Spinner b_spinner_decrypt = findViewById(R.id.affine_spinner_b_decrypt);
        ArrayAdapter<CharSequence> b_adapter_decrypt = ArrayAdapter.createFromResource(this, R.array.b_values, android.R.layout.simple_spinner_item);
        b_adapter_decrypt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        b_spinner_decrypt.setAdapter(b_adapter_decrypt);
        b_spinner_decrypt.setOnItemSelectedListener(this);

        affineEncrypter = findViewById(R.id.affine_encrypter);
        affineDecrypter = findViewById(R.id.affine_decrypter);
        finalMessage = findViewById(R.id.affine_encrypted_message);
        originalMessage = findViewById(R.id.affine_decrypted_message);
        //spinner_a = findViewById(R.id.affine_spinner_a_encrypt);

    }

    public void toAffineAbout(View view) {

        Intent affine = new Intent(this, AboutAffine.class);
        startActivity(affine);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.affine_spinner_a_encrypt) {
            a_value_encrypt = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
        }
        if (adapterView.getId() == R.id.affine_spinner_b_encrypt) {
            b_value_encrypt = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
        }
        if (adapterView.getId() == R.id.affine_spinner_a_decrypt) {
            a_value_decrypt = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
        }
        if (adapterView.getId() == R.id.affine_spinner_b_decrypt) {
            b_value_decrypt = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void affineEncrypt(View v) {
        String messageToEncrypt = affineEncrypter.getEditText().getText().toString();

        if (messageToEncrypt.isEmpty()) {
            affineEncrypter.setError("There is no message to encrypt");
        }
        else {
            affineEncrypter.setError(null);
            //String encryptedMessage = "Encrypted";
            finalMessage.setText("Encrypted Message: \n" + AffineCipher.affine(messageToEncrypt, a_value_encrypt, b_value_encrypt));
        }
    }

    public void affineDecrypt(View v) {
        String messageToDecrypt = affineDecrypter.getEditText().getText().toString();

        if (messageToDecrypt.isEmpty()) {
            affineDecrypter.setError("There is no message to decrypt");
        }
        else {
            affineDecrypter.setError(null);
            //String decryptedMessage = "Decrypted";
            originalMessage.setText("Decrypted Message: \n" + AffineCipher.affineDecrypt(messageToDecrypt, a_value_decrypt, b_value_decrypt));
        }
    }
}