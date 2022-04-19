package com.example.laptrinhdidong_lab8_12_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity_finsh extends AppCompatActivity {
    private Button btnfinish;
    private FirebaseAuth auth;
    private ImageButton imghappy;
    private ImageButton imgsad;
    private ImageButton imgnormal;
    private int happy;
    private int sad;
    private int normal;
    private int id;

    private DatabaseHandler_Faces db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finsh);

        btnfinish= findViewById(R.id.btnfinish);
        imghappy= findViewById(R.id.imghappy);
        imgsad= findViewById(R.id.imgsad);
        imgnormal = findViewById(R.id.imgnormal);


        auth=FirebaseAuth.getInstance();

        Bundle bl = getIntent().getExtras();
      final  String email = (String) bl.get("email");

        btnfinish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(MainActivity_finsh.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity_finsh.this,"Bạn đã hoàn thành", Toast.LENGTH_LONG).show(); }
        });
        imghappy.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                db.addContact(new Faces
                        (email, happy+=1));
                Toast.makeText(MainActivity_finsh.this, "Email: " + email + " - happy: " +happy, Toast.LENGTH_SHORT).show();
            }
        });
    }

}