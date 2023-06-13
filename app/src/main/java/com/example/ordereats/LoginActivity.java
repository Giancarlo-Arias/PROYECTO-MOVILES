package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView txtWelcome;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtWelcome = findViewById(R.id.txtWelcome);

        String username = getIntent().getStringExtra("username");
        if (username != null) {
            txtWelcome.setText("Bienvenido, " + username + "!");
        } else {
            txtWelcome.setText("Bienvenido!");
        }

        logout= (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, Menu.class);
                startActivity(i);
            }
        });
    }
}