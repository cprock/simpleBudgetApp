package com.example.tanishka.basic_budget_app;

import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AmazonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amazon);

        WebView amazonWebView = (WebView) findViewById(R.id.webview);

        /*try {
            URL url = new URL("https://www.amazon.com/");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                //
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            Log.d("AmazonActivity:", "MalformedURLException: invalid url");
        } catch (IOException e) {
            Log.d("AmazonActivity:", "IOException: invalid url");
        }*/

        amazonWebView.loadUrl("https://www.amazon.com/");

        // enable JavaScript to potentially create an interface between application code and JS code
        WebSettings webSettings = amazonWebView.getSettings();

        // note: setting javascriptenabled to true makes app vulnerable to malicious advertisements because
        // of cross-site scripting attacks. advertisements are sources of potentially malicious code.
        webSettings.setJavaScriptEnabled(false);

        // handling page navigation
        // create a WebViewClient to open links clicked by user within THE SAME WEBVIEW (Consider changing this!)

        amazonWebView.setWebViewClient(new MyWebViewClient());
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

class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request){
        if (request.getUrl().getHost().equals("https://www.amazon.com")){
            return false;
        }

        return true;
    }

}
