package com.example.laptrinhdidong_lab8_12_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private Button btnsignin, btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsignin= findViewById(R.id.btnsignin);
        btnregister= findViewById(R.id.btnregister);

        btnsignin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity_sign_in.class);
            startActivity(intent);
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity_rigister.class);
                startActivity(intent);
            }
        });

    }
}