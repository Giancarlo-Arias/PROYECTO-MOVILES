package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.adapter.ListaUsuarioAdapter;
import com.example.ordereats.data.DataApi;
import com.example.ordereats.data.SQLLITE_OpenHelper;
import com.example.ordereats.domain.AdapterRecyclerUsuarios;
import com.example.ordereats.domain.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListarUsuarioActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    private int ID_USER = 0;

    private DataApi userDB;
    Button btnMenu;

    private RequestQueue requestQueue;

    private RecyclerView recycler;

    private ArrayList<User> usuarios;

    private JsonObjectRequest jsonRequest;
    //RecyclerView listaUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario);
        requestQueue = Volley.newRequestQueue(this);

        //btnMenu = (Button) findViewById(R.id.btnRegresar);

        Intent intent = getIntent();
        ID_USER = intent.getIntExtra("user_id",0);
        userDB = new DataApi();
        jsonRequest = new JsonObjectRequest(Request.Method.GET, userDB.obtenerUsuarios, null, this, this);
        requestQueue.add(jsonRequest);

        Toast.makeText(this, "Enviando solicitud...", Toast.LENGTH_SHORT).show();

        recycler = findViewById(R.id.recyclerUsuarios);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        usuarios = new ArrayList<>();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error al obtener los usuarios.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            JSONArray jsonArray = response.getJSONArray("usuarios");
            ArrayList<User> usuarios = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int user_Id = jsonObject.getInt("user_id");
                String user_Name = jsonObject.getString("user_name");
                String user_Lastname = jsonObject.getString("user_Lastname");
                String user_Phone = jsonObject.getString("user_phone");
                String user_Email = jsonObject.getString("user_email");
                String user_Password =jsonObject.getString("user_password");
                int user_Active =jsonObject.getInt("user_active");
                User user = new User( user_Id,  user_Name,  user_Lastname,  user_Phone,  user_Email,  user_Password, user_Active);
                usuarios.add(user);
            }
            if (usuarios.isEmpty()) {
                Toast.makeText(this, "No hay usuarios.", Toast.LENGTH_SHORT).show();
            } else {
                AdapterRecyclerUsuarios adapter = new AdapterRecyclerUsuarios(this, usuarios);
                recycler.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}