package com.example.anuradha.bloombergproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    EditText password;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        password = findViewById(R.id.id_password);
        enter = findViewById(R.id.id_enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(password.getText()).equals(("bloomberg"))){
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "INVALID PASSWORD!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
