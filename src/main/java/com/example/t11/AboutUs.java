package com.example.t11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class AboutUs extends AppCompatActivity {
        WebView view1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        view1=findViewById(R.id.view1);
        view1.getSettings().setJavaScriptEnabled(true);
        view1.loadUrl("http://renishlakhani.epizy.com");

    }
}
