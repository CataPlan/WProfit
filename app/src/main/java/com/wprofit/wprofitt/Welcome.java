package com.wprofit.wprofitt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onClick(View view){
        Intent inte = null;
        switch (view.getId()){
            case R.id.login:
                inte = new Intent(Welcome.this,login.class);

                finish();
                break;
            case R.id.create:
                inte =new Intent(Welcome.this,registro.class);
                finish();
                break;

        }
        startActivity(inte);
    }
}