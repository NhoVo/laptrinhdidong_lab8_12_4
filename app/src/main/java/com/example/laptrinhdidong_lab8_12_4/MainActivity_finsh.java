package com.example.laptrinhdidong_lab8_12_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity_finsh extends AppCompatActivity {
    private Button btnfinish;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finsh);

        btnfinish= findViewById(R.id.btnfinish);
        auth=FirebaseAuth.getInstance();



        btnfinish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(MainActivity_finsh.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity_finsh.this,"Bạn đã hoàn thành", Toast.LENGTH_LONG).show();
            }
        });
    }
}