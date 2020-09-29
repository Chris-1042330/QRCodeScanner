package com.bitsplease.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    private EditText NewPassword;
    private EditText NewEmail;
    private Button RegisterBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        NewPassword = findViewById(R.id.NewPassword);
        NewEmail = findViewById(R.id.NewEmail);
        RegisterBTN = findViewById(R.id.RegisterBTN);

        RegisterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputpass = NewPassword.getText().toString();
                String inputemail = NewEmail.getText().toString();

                if (inputpass.isEmpty() || inputemail.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please enter name, password and E-mail to register", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}