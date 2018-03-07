package com.example.tanishka.basic_budget_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class UpdateBalance extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText inputBalance;
        final EditText inputBankName;
        final EditText inputAccountNum;
        double accountBalance;
        final AccountEncryptor encryptor;
        Button setUp;
        final String ALIAS = "SECURE_ACCOUNT";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_balance);

        inputBalance = (EditText) findViewById(R.id.balance);
        inputBankName = (EditText) findViewById(R.id.bank_name);
        inputAccountNum = (EditText) findViewById(R.id.account_num);
        //accountBalance = Double.parseDouble(inputBalance.getText().toString());


        /*inputBankName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    final bankName = inputBankName.getText().toString();

                    return true;
                }
                return false;
            }
        });

        inputAccountNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    String accountNum = inputAccountNum.getText().toString();
                    return true;
                }
                return false;
            }
        });
        */



        encryptor = new AccountEncryptor();

        setUp = (Button) findViewById(R.id.set_up_account);
        setUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bankName = inputBankName.getText().toString();
                String accountNum = inputAccountNum.getText().toString();
                final String privateInfo = bankName + ":" + accountNum;

                Log.d("un-encrypted data", privateInfo);
                encryptText(encryptor, ALIAS, privateInfo);
                getAccountFromFile(ALIAS, encryptor);
            }
        });



    }

    /**
     * method to save encrypted account information to a file. the file can be used to check if account
     * information has already been saved (returns true if yes) can also be used later to implement
     * functionality to handle multiple accounts
     */
    private void saveAccountToFile(byte[] eText){
        Log.d("UpdateBalance", "account saved to file!");

    }

    private void getAccountFromFile(String alias, AccountEncryptor encryptor){
        try {
            AccountDecryptor decryptor = new AccountDecryptor();
            decryptText(decryptor, alias, encryptor);
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
            e.printStackTrace();
        }
    }

    private void encryptText(AccountEncryptor encryptor, String alias, String textToEncrypt){
        try {
            final byte[] encryptedText = encryptor.encryptText(alias, textToEncrypt);
            saveAccountToFile(encryptedText);

        } catch (NoSuchAlgorithmException | BadPaddingException | InvalidKeyException |
                InvalidAlgorithmParameterException | NoSuchPaddingException | NoSuchProviderException |
                IllegalBlockSizeException | UnsupportedEncodingException e) {

            e.printStackTrace();
        }
    }

    private void decryptText(AccountDecryptor decryptor, String alias, AccountEncryptor encryptor){
        try {
            String decryptedText = decryptor.decryptData(alias, encryptor.getEncryption(), encryptor.getIv());
            Log.d("decrypted_data: ", decryptedText);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException
                | UnrecoverableEntryException | KeyStoreException e) {

            e.printStackTrace();
        }


    }
}
