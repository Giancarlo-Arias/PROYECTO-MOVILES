package com.example.ordereats;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ordereats.data.SQLLITE_OpenHelper;

public class MainActivity extends AppCompatActivity {

    Button btnlogin;
    Button btnnew;

    private EditText email;
    private EditText pass;

    private SQLLITE_OpenHelper helperUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helperUser = new SQLLITE_OpenHelper(this,"dbordereats",null,1);
        
        email = findViewById(R.id.txt_Email);
        pass = findViewById(R.id.txtPassword);



        btnlogin=(Button)findViewById(R.id.btn_login);
        btnnew=(Button) findViewById(R.id.btn_register);//create account

        //login
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPass = pass.getText().toString();

                try {
                    Cursor cursor = helperUser.validateUser(userEmail, userPass);
                    if (cursor != null && cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        String nombreUsuario = cursor.getString(1); // Reemplaza el índice 1 si corresponde a la columna "Name"
                        Toast.makeText(MainActivity.this, "Usuario Correcto", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Menu.class);
                        intent.putExtra("username", nombreUsuario); // Pasar el nombre de usuario al siguiente Activity
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

    }
}