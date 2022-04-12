package com.example.laptrinhdidong_lab8_12_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity_rigister extends AppCompatActivity {
    private EditText txtname,txtemail_register,txtpassword_rigister,txtpassword_rigister2;
    private Button btnregister;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);


        txtname = findViewById(R.id.txtname);
        txtpassword_rigister=findViewById(R.id.txtpassword_register);
        txtemail_register = findViewById(R.id.txteamil_register);
        txtpassword_rigister2= findViewById(R.id.txtpassword_register2);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();




    }
    private void OnClick_register(String email, String password)
    {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity_rigister.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("name",txtname.getText().toString().trim());
                    map.put("email",txtemail_register.getText().toString().trim());
                    db.getReference().child("managerUsers").push().child("User").setValue(map);

                    Toast.makeText(MainActivity_rigister.this,"Bạn đã đăng ký thành công",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(MainActivity_rigister.this,MainActivity_finsh.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity_rigister.this,"Đăng ký không thành công!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}