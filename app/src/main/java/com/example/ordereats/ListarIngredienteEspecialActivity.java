package com.example.ordereats;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.ordereats.domain.GestorMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.example.ordereats.domain.AdapterRecyclerIngredienteEsp;

public class ListarIngredienteEspecialActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private int ID_INGREDIENTEESPECIAL = 0;
    private DataApi ingredienteEspecialDB;
    private RequestQueue requestQueue;
    private ArrayList<GestorMenu> ingredienteEspecial;
    private JsonObjectRequest jsonRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ingrediente_especial);
        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        ID_INGREDIENTEESPECIAL = intent.getIntExtra("id_ingrediente_especial", 0);
        ingredienteEspecialDB = new DataApi();
        jsonRequest = new JsonObjectRequest(Request.Method.GET, ingredienteEspecialDB.obtenerTodosIngredientesEspeciales, null, this, this);
        requestQueue.add(jsonRequest);
        Toast.makeText(this, "Enviando solicitud...", Toast.LENGTH_SHORT).show();

        ingredienteEspecial = new ArrayList<>();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("ingredientes_especiales");
            ArrayList<GestorMenu> ingredientesEspeciales = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idIngredienteEspecial = jsonObject.getInt("id_ingrediente_especial");
                String nombre = jsonObject.getString("nombre");
                double precio = jsonObject.getDouble("precio");
                GestorMenu ingredienteEspecial = new GestorMenu(idIngredienteEspecial, nombre,"nada", precio);
                ingredientesEspeciales.add(ingredienteEspecial);
            }
            if (ingredientesEspeciales.isEmpty()) {
                Toast.makeText(this, "No hay ingredientes especiales.", Toast.LENGTH_SHORT).show();
            } else {
                RecyclerView recycler = findViewById(R.id.recyclerIngredientesEspeciales);
                AdapterRecyclerIngredienteEsp adapter = new AdapterRecyclerIngredienteEsp(this, ingredientesEspeciales);
                recycler.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error al obtener los ingredientes especiales.", Toast.LENGTH_SHORT).show();
    }

    public void volverInicio(View view) {
        Intent intent = new Intent(view.getContext(), Menu.class);
        view.getContext().startActivity(intent);
    }

    public void agregarIngredienteEspecial(View view) {
        Intent intent = new Intent(view.getContext(), InsertarIngredienteEspecialActivity.class);
        view.getContext().startActivity(intent);
    }
}
