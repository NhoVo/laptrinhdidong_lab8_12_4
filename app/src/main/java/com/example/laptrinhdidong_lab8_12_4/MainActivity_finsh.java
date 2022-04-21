package com.example.laptrinhdidong_lab8_12_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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


    private DatabaseFaces db;
    private FacesDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finsh);

        btnfinish= findViewById(R.id.btnfinish);
        imghappy= findViewById(R.id.imghappy);
        imgsad= findViewById(R.id.imgsad);
        imgnormal = findViewById(R.id.imgnormal);


        auth=FirebaseAuth.getInstance();
        // SQLite
        db = Room.databaseBuilder(getApplicationContext(),DatabaseFaces.class,"faces-manager").allowMainThreadQueries().build();
        //interface

        dao= db.facesDAO();



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
                Faces faces = dao.findByEmail(email);
                // check email
                if(faces != null) {
                    faces.setHappy(faces.getHappy() + 1);

                    try {
                        dao.updateFaces(faces);
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    Toast.makeText(MainActivity_finsh.this, "Email: " + email + " - Happy: " + faces.getHappy(), Toast.LENGTH_SHORT).show();
                } else {
                    dao.saveFaces(new Faces(email, 1, 0, 0));
                    Toast.makeText(MainActivity_finsh.this, email + " - Happy: 1", Toast.LENGTH_SHORT).show();
                }

            }
        });
        imgsad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Faces faces = dao.findByEmail(email);
                // check email
                if(faces != null) {
                    faces.setUnhappy(faces.getUnhappy()+ 1);

                    try {
                        dao.updateFaces(faces);
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    Toast.makeText(MainActivity_finsh.this, "Email: " + email + " - UnHappy: " + faces.getUnhappy(), Toast.LENGTH_SHORT).show();
                } else {
                    dao.saveFaces(new Faces(email, 0, 1, 0));
                    Toast.makeText(MainActivity_finsh.this, email + " - UnHappy: 1", Toast.LENGTH_SHORT).show();
                }

            }
        });
        imgnormal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Faces faces = dao.findByEmail(email);
                // check email
                if(faces != null) {
                    faces.setNormal(faces.getNormal()+ 1);

                    try {
                        dao.updateFaces(faces);
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    Toast.makeText(MainActivity_finsh.this, "Email: " + email + " - Normal: " + faces.getNormal(), Toast.LENGTH_SHORT).show();
                } else {
                    dao.saveFaces(new Faces(email, 0, 0, 1));
                    Toast.makeText(MainActivity_finsh.this, email + " - Normal: 1", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}