package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private TextView txtWelcome;

    Button logout, listaUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtWelcome = findViewById(R.id.txtWelcome2);
        String username = getIntent().getStringExtra("username");
        if (username != null) {
            txtWelcome.setText("Bienvenido, " + username + "!");
        } else {
            txtWelcome.setText("Bienvenido!");
        }

        logout= (Button) findViewById(R.id.btnLogOut2);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, MainActivity.class);
                startActivity(i);
            }
        });

        listaUsuario= (Button) findViewById(R.id.btnListUs);
        listaUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, ListarUsuarioActivity.class);
                startActivity(i);
            }
        });
    }

    public void ListarPlatillos(View view) {
        Intent intent = new Intent(this, ListarPlatilloActivity.class);
        startActivity(intent);
    }
    public void ListarIngredientesEspeciales(View view) {
        Intent intent = new Intent(this, ListarIngredienteEspecialActivity.class);
        startActivity(intent);
    }
    public void RegistrarOrden(View view) {
        Intent intent = new Intent(this, OrdenActivity.class);
        startActivity(intent);
    }

}