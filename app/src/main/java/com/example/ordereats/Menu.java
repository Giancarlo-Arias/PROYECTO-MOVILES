package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void ListarPlatillos(View view) {
        Intent intent = new Intent(this, ListarPlatilloActivity.class);
        startActivity(intent);
    }
    public void ListarIngredientesEspeciales(View view) {
        Intent intent = new Intent(this, ListarIngredienteEspecialActivity.class);
        startActivity(intent);
    }

}