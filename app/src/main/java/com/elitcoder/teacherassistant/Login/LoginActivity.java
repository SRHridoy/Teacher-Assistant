package com.elitcoder.teacherassistant.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elitcoder.teacherassistant.Options.OptionActivity;
import com.elitcoder.teacherassistant.R;

import java.util.OptionalInt;

public class LoginActivity extends AppCompatActivity {
//TODO:Developer Tomal, you have to finish this . You will work on both login ui and backend logic...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //TODO: Devloper Hridoy Just trying to connect all the acitivities for Demo...
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionIntent = new Intent(LoginActivity.this, OptionActivity.class);
                startActivity(optionIntent);
            }
        });

    }
}