package com.example.tanishka.basic_budget_app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

public class UpdateAccount extends AppCompatActivity {


    //public static final String BALANCE = "ACCOUNT_BALANCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final EditText inputBalance;
        final EditText inputBankName;
        final EditText inputAccountNum;
        final AccountEncryptor encryptor;
        Button setUp;
        final String ALIAS = "SECURE_ACCOUNT";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        //inputBalance = (EditText) findViewById(R.id.balance);
        inputBankName = (EditText) findViewById(R.id.bank_name);
        inputAccountNum = (EditText) findViewById(R.id.account_num);



        encryptor = new AccountEncryptor();

        setUp = (Button) findViewById(R.id.set_up_account);
        setUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Double accountBalance = Double.parseDouble(inputBalance.getText().toString());
                //String accountBalance = inputBalance.getText().toString();
                String bankName = inputBankName.getText().toString();
                String accountNum = inputAccountNum.getText().toString();
                final String privateInfo = bankName + ":" + accountNum;

                //for testing purposes: Log.d("un-encrypted data", privateInfo);
                encryptText(encryptor, ALIAS, privateInfo);

                //for testing purposes: decryptText(ALIAS, encryptor);

                //goToViewBudget(accountBalance);
                goToHome();
            }
        });
    }

    /*
     * Step 1: encrypt the file
     */
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

    /**
     * Step 2: save the encrypted file (see below for further explanation)
     * method to save encrypted account information to a file. the file can be used to check if account
     * information has already been saved (returns true if yes) can also be used later to implement
     * functionality to handle multiple accounts
     */
    private void saveAccountToFile(byte[] encryptedText){
        String fname = "private_info";
        String fcontents = Base64.encodeToString(encryptedText, Base64.DEFAULT);

        try {
            FileOutputStream outputStream = openFileOutput(fname, Context.MODE_PRIVATE);
            outputStream.write(fcontents.getBytes());
            outputStream.close();
            Log.d("Set Up Account Information", "SUCCESS: account saved to file!");

        } catch (Exception e) {
            Log.d("Set Up Account Information", "ERROR: account not saved to file!");
            e.printStackTrace();
        }



    }

    private void decryptText(String alias, AccountEncryptor encryptor){
        try {
            AccountDecryptor decryptor = new AccountDecryptor();
            getAccountFromFile(decryptor, alias, encryptor);
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException e) {
            e.printStackTrace();
        }
    }



    private void getAccountFromFile(AccountDecryptor decryptor, String alias, AccountEncryptor encryptor){
        try {

            FileInputStream inputStream = openFileInput("private_info");
            inputStream.close();

            String decryptedText = decryptor.decryptData(alias, encryptor.getEncryption(), encryptor.getIv());
            //for testing purposes: Log.d("decrypted_data: ", decryptedText);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException
                | UnrecoverableEntryException | KeyStoreException e) {

            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Log.d("Check Account Set Up", "ERROR: file not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("Check Account Set Up", "ERROR: i/o");
            e.printStackTrace();
        }


    }

    // go to view budget activity
    private void goToHome() {
        Intent intent = new Intent(this, HomeScreen.class);
        //intent.putExtra(BALANCE, balance);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
