package com.example.t11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Feedback extends AppCompatActivity {
        WebView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        view=findViewById(R.id.view);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl("http://renishlakhani.epizy.com");



    }
}
