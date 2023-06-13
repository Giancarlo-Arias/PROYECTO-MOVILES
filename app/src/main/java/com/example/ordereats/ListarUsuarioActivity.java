package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ordereats.adapter.ListaUsuarioAdapter;
import com.example.ordereats.data.SQLLITE_OpenHelper;
import com.example.ordereats.domain.User;

import java.util.ArrayList;

public class ListarUsuarioActivity extends AppCompatActivity {

    Button btnMenu;

    RecyclerView listaUsuario;
    ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario);

        btnMenu = (Button) findViewById(R.id.btnRegresar);

        listaUsuario = (RecyclerView) findViewById(R.id.recyclerUsuarios);

        SQLLITE_OpenHelper helperUser = new SQLLITE_OpenHelper(ListarUsuarioActivity.this,
                "dbordereats",null,1);
        ListaUsuarioAdapter listaUsuarioAdapter = new ListaUsuarioAdapter(helperUser.showUsers());
        listaUsuario.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        listaUsuario.setAdapter(listaUsuarioAdapter);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarUsuarioActivity.this, Menu.class);
                startActivity(i);
            }
        });
    }
}