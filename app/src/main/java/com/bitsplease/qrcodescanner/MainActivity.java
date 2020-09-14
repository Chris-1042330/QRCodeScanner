package com.bitsplease.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (user is loggedin){
        setContentView(R.layout.activity_main);
        //}
        // else{
        //  setContentView(R.layout.activity_login);
        // }
    }


    public void openScanner(View view){
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }
}