package com.wprofit.wprofitt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class login extends AppCompatActivity {

    EditText usuario,correo,pass;
    Button iniciar,registrar;
    FirebaseAuth mAuth;

    private String email;
    private String username;
    private String passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        correo = findViewById(R.id.username);

        pass = findViewById(R.id.password);
        registrar = findViewById(R.id.registrar);
        iniciar = findViewById(R.id.iniciar);
        //mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = correo.getText().toString().trim();
                passwd = pass.getText().toString().trim();

                if (email.isEmpty() || passwd.isEmpty()){
                    Toast.makeText(login.this, "ingrese datos", Toast.LENGTH_SHORT).show();
                }else{
                    if (emailval(email)){
                        mAuth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(login.this, "inició correcto", Toast.LENGTH_SHORT).show();
                                    irHome();
                                }else{
                                    Toast.makeText(login.this, "contraseña incorrecta", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(login.this, "email no valido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(login.this,registro.class);
                startActivity(inte);
                finish();
            }
        });



    }

    private Boolean emailval(String email){

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser usuario= mAuth.getCurrentUser();
        if(usuario != null){
            irHome();
        }
    }

    public void irHome(){
        Intent inte = new Intent(login.this,MainActivity.class);
        startActivity(inte);
        finish();
    }
}