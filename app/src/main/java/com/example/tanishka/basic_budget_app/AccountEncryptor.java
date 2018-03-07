package com.example.tanishka.basic_budget_app;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Class: AccountEncryptor
 * Responsibility: To encrypt sensitive data such as account information
 *
 *
 * Note: This code was written with the help of the following source:
 * https://gist.github.com/JosiasSena/3bf4ca59777f7dedcaf41a495d96d984
 *
 */

class AccountEncryptor {
    private static final String CIPHER_TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    private byte[] encryption;
    private byte[] iv;

    AccountEncryptor(){}

    byte[] encryptText(final String alias, final String textToEncrypt) throws NoSuchPaddingException,
            NoSuchAlgorithmException, UnsupportedEncodingException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeyException, NoSuchProviderException, InvalidAlgorithmParameterException {

        final Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(alias));

        iv = cipher.getIV();

        encryption = cipher.doFinal(textToEncrypt.getBytes("UTF-8"));

        return encryption;
    }

    private SecretKey getSecretKey(final String alias) throws NoSuchProviderException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {

        final KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);

        keyGenerator.init(new KeyGenParameterSpec.Builder(alias,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .build());

        return keyGenerator.generateKey();
    }

    byte[] getEncryption() {
        return encryption;
    }

    byte[] getIv() {
        return iv;
    }


}
