package com.wprofit.wprofitt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//setContentView(R.layout.activity_registro);
public class registro extends AppCompatActivity {

    EditText usuario,correo,pass,repass;
    Button registrar,yaacc;
    FirebaseAuth mAuth;

    private String email,nameUser,passwd,repasswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = findViewById(R.id.username);
        correo = findViewById(R.id.correo);
        pass = findViewById(R.id.password);
        repass = findViewById(R.id.confirmpassword);
        registrar = findViewById(R.id.registrar);
        yaacc = findViewById(R.id.yaacc);

        //mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        yaacc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(registro.this,login.class);
                startActivity(inte);
                finish();
            }
        });
        registrar.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                nameUser = usuario.getText().toString().trim();
                email = correo.getText().toString().trim();
                passwd = pass.getText().toString().trim();
                repasswd = repass.getText().toString().trim();

                if(nameUser.isEmpty() || email.isEmpty() || passwd.isEmpty() || repasswd.isEmpty() ){
                    Toast.makeText(registro.this, "Campos vacios,completelo", Toast.LENGTH_SHORT).show();
                }
                else if(!email.isEmpty() && !passwd.isEmpty() && !repasswd.isEmpty()){
                    if (emailval(email)){
                        if (passwd.equals(repasswd)){

                            if(repasswd.length()>6){
                                mAuth.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(registro.this, "Exito al crear cuenta", Toast.LENGTH_SHORT).show();
                                            Intent inte = new Intent(registro.this, login.class);
                                            startActivity(inte);
                                        }else{
                                            Toast.makeText(registro.this, "la cuenta ya existe", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(registro.this, "la contraseña debe ser mayor a 6caracteres", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(registro.this, "No coinciden las contraseñas", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(registro.this, "Ingrese Email Valido", Toast.LENGTH_SHORT).show();
                    }
                }
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
            irLogin();
        }
    }

    public void irLogin(){
        Intent inte = new Intent(registro.this,login.class);
        startActivity(inte);
        finish();
    }
}