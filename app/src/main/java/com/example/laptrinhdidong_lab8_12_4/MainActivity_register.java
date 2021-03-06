package com.example.laptrinhdidong_lab8_12_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity_register extends AppCompatActivity {
    private EditText txtname,txtemail_register,txtpassword_rigister,txtpassword_rigister2;
    private Button btnregister;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        txtname = findViewById(R.id.txtname);
        txtpassword_rigister=findViewById(R.id.txtpassword_register);
        txtemail_register = findViewById(R.id.txteamil_register);
        txtpassword_rigister2= findViewById(R.id.txtpassword_register2);
        btnregister = findViewById(R.id.btnregister_register);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtYourName= txtname.getText().toString().trim();
                String txtEmailRegister = txtemail_register.getText().toString().trim();
                String txtTypePasswordRegister = txtpassword_rigister.getText().toString().trim();
                String txtReTypePasswordRegister = txtpassword_rigister2.getText().toString().trim();

                // check
                if(txtYourName.equals("") || txtEmailRegister.equals("") || txtTypePasswordRegister.equals("") || txtReTypePasswordRegister.equals("")) {
                    Toast.makeText(MainActivity_register.this, "B???n c???n nh???p ?????y ????? th??ng tin ????? ti???p t???c!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(txtTypePasswordRegister.length() < 6) {
                    Toast.makeText(MainActivity_register.this, "M???t kh???u c???n ph???i l???n h??n 6 k?? t???!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!txtTypePasswordRegister.equals(txtReTypePasswordRegister)) {
                    Toast.makeText(MainActivity_register.this, "M???t kh???u nh???p l???i kh??ng kh???p!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // action
                OnClick_register(txtEmailRegister, txtTypePasswordRegister);
            }
        });


    }
    private void OnClick_register(String email, String password)
    {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity_register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("name",txtname.getText().toString().trim());
                    map.put("email",txtemail_register.getText().toString().trim());
                    db.getReference().child("managerUsers").push().child("User").setValue(map);

                    Toast.makeText(MainActivity_register.this,"B???n ???? ????ng k?? th??nh c??ng",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(MainActivity_register.this,MainActivity_finsh.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity_register.this,"????ng k?? kh??ng th??nh c??ng!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}