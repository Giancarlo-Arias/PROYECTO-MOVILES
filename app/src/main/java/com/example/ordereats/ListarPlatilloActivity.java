package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.data.DataApi;
import com.example.ordereats.domain.AdapterRecyclerPlatillos;
import com.example.ordereats.domain.GestorMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListarPlatilloActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private int ID_PLATILLO = 0;
    private DataApi platilloDB;
    private RequestQueue requestQueue;
    private RecyclerView recycler;
    private ArrayList<GestorMenu> platillos;
    private JsonObjectRequest jsonRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platillo);
        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        ID_PLATILLO = intent.getIntExtra("id_platillo", 0);
        platilloDB = new DataApi();
        jsonRequest = new JsonObjectRequest(Request.Method.GET, platilloDB.obtenerPlatillos, null, this, this);
        requestQueue.add(jsonRequest);
        Toast.makeText(this, "Enviando solicitud...", Toast.LENGTH_SHORT).show();

        recycler = findViewById(R.id.recyclerPlatillos);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        platillos = new ArrayList<>();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("platillos");
            ArrayList<GestorMenu> platillos = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idPlatillo = jsonObject.getInt("id_plato");
                String nombre = jsonObject.getString("nombre");
                String descripcion = jsonObject.getString("descripcion");
                double precio = jsonObject.getDouble("precio");
                GestorMenu platillo = new GestorMenu(idPlatillo, nombre, descripcion, precio);
                platillos.add(platillo);
            }
            if (platillos.isEmpty()) {
                Toast.makeText(this, "No hay platillos.", Toast.LENGTH_SHORT).show();
            } else {
                AdapterRecyclerPlatillos adapter = new AdapterRecyclerPlatillos(this, platillos);
                recycler.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error al obtener los platillos.", Toast.LENGTH_SHORT).show();
    }

    public void volverInicio(View view) {
        Intent intent = new Intent(view.getContext(), Menu.class);
        view.getContext().startActivity(intent);
    }

    public void agregarPlatillo(View view) {
        Intent intent = new Intent(view.getContext(), InsertarPlatilloActivity.class);
        view.getContext().startActivity(intent);
    }
}