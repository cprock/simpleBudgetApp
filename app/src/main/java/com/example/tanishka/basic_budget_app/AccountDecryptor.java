package com.example.tanishka.basic_budget_app;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 * Class: AccountDecryptor
 * Responsibility: To decrypt sensitive data such as account information
 *
 *
 * Note: This code was written with the help of the following source:
 * https://gist.github.com/JosiasSena/3bf4ca59777f7dedcaf41a495d96d984
 *
 */


class AccountDecryptor {
    private static final String CIPHER_TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    private KeyStore keyStore;

    AccountDecryptor() throws CertificateException, NoSuchAlgorithmException,
            KeyStoreException, IOException {
        initKeyStore();
    }

    private void initKeyStore() throws KeyStoreException, CertificateException,
            NoSuchAlgorithmException, IOException {
        keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);
    }

    String decryptData(final String alias, final byte[] encryptedData, final byte[] encryptionIV) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException,
            UnrecoverableEntryException, KeyStoreException {

        final Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        final GCMParameterSpec spec = new GCMParameterSpec(128, encryptionIV);
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(alias), spec);

        return new String(cipher.doFinal(encryptedData), "UTF-8");
    }

    private SecretKey getSecretKey(final String alias) throws UnrecoverableEntryException,
            NoSuchAlgorithmException, KeyStoreException {

        return ((KeyStore.SecretKeyEntry) keyStore.getEntry(alias, null)).getSecretKey();
    }



}
