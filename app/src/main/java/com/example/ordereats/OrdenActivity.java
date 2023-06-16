package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.data.DataApi;
import com.example.ordereats.domain.AdapterSpinnerPicante;
import com.example.ordereats.domain.GestorGuarnicion;
import com.example.ordereats.domain.GestorMenu;
import com.example.ordereats.domain.gestorCoccion;
import com.example.ordereats.domain.gestorCondimento;
import com.example.ordereats.domain.gestorEspeciales;
import com.example.ordereats.domain.gestorPicante;
import com.example.ordereats.domain.gestorProteina;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrdenActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    private DataApi picantedata;
    private  RequestQueue requestQueue;
    private JsonObjectRequest jsonRequest;

    private Spinner platillo;
    private Spinner ingredienteSpecial;
    private Spinner proteina;
    private Spinner guarnicion;
    private Spinner condimento;
    private Spinner picante;
    private Spinner porcion;
    private Spinner coccion;

    Button registrar;


    private ArrayList<gestorPicante> picantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);
        requestQueue = Volley.newRequestQueue(this);
        picantedata = new DataApi();
        jsonRequest = new JsonObjectRequest(Request.Method.GET,picantedata.optenerNivelPicante,null,this,this);
        requestQueue.add(jsonRequest);
        Toast.makeText(this, "Enviando solicitud...", Toast.LENGTH_SHORT).show();

        picante = findViewById(R.id.spinnerNivelPicante);
        picantes = new ArrayList<>();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {


        try {

            JSONArray jsonArrayPicante = response.getJSONArray("picante");
            ArrayList<gestorPicante>mostrarPicantes= new ArrayList<>();
            for (int i = 0; i < jsonArrayPicante.length(); i++) {
                JSONObject jsonObject = jsonArrayPicante.getJSONObject(i);
                int id_nivel_picante = jsonObject.getInt("tb_nivel_picante");
                String nombre = jsonObject.getString("nombre");

                gestorPicante gestor = new gestorPicante(id_nivel_picante,nombre);
                mostrarPicantes.add(gestor);
            }
            if (mostrarPicantes.isEmpty()) {
                Toast.makeText(this, "No hay Nivel de Picantes.", Toast.LENGTH_SHORT).show();
            } else {
                AdapterSpinnerPicante adapter = new AdapterSpinnerPicante(this, mostrarPicantes);
                picante.setAdapter(adapter);
            }









// ...


        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void volverInicio(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(intent);
    }

}