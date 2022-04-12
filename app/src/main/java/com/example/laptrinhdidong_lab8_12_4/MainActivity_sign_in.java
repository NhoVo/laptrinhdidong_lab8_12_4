package com.example.laptrinhdidong_lab8_12_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity_sign_in extends AppCompatActivity {
    private EditText txtemail,txtpassword;
    private Button btnsignin;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        txtemail = findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);
        btnsignin = findViewById(R.id.btnsignin1);
        auth = FirebaseAuth.getInstance();

        btnsignin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String txtEmail = txtemail.getText().toString().trim();
                String txtPassword = txtpassword.getText().toString().trim();

                if(txtEmail.equals("")|| txtPassword.equals(""))
                {
                    Toast.makeText(MainActivity_sign_in.this,"Vui lòng nhập email và password",Toast.LENGTH_LONG).show();
                    return;
                }
                onClick_btnSignIn(txtEmail,txtPassword);

            }
        });
    }

    private void onClick_btnSignIn(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(MainActivity_sign_in.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity_sign_in.this, "Đăng nhập thành công. Xin chúc mừng bạn.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity_sign_in.this,MainActivity_finsh.class);
                startActivity(intent);
            }
        });
    }
}