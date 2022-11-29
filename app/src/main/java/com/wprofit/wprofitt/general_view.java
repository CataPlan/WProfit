package com.wprofit.wprofitt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class general_view extends AppCompatActivity {
    Button cerrars;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_view);
        cerrars= findViewById(R.id.cerrarsesion);
        mAuth = FirebaseAuth.getInstance();
        cerrars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                irMain();
            }
        });
    }
    public void irMain(){
        Intent inte = new Intent(general_view.this,login.class);
        startActivity(inte);
        finish();
    }
}