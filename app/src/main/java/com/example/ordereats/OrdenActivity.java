package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.data.DataApi;
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

    private DataApi ordenDB;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);
        
       // ordenDB = new ArrayList<>();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray jsonArray = null;
        try {
            jsonArray = response.getJSONArray("platillos");
            JSONArray jsonArrayEspeciales = response.getJSONArray("especiales");
            JSONArray jsonArrayProteina = response.getJSONArray("proteina");
            JSONArray jsonArrayGuarnicion = response.getJSONArray("guarnicion");
            JSONArray jsonArrayCondimento = response.getJSONArray("condimentos");
            JSONArray jsonArrayPicante = response.getJSONArray("picante");
            JSONArray jsonArrayPorcion = response.getJSONArray("porcion");
            JSONArray jsonArrayCoccion = response.getJSONArray("coccion");


            ArrayList<GestorMenu> platillos = new ArrayList<>();
            ArrayList<gestorEspeciales> especiales = new ArrayList<>();
            ArrayList<gestorProteina> proteinas = new ArrayList<>();
            ArrayList<GestorGuarnicion> guarniciones = new ArrayList<>();
            ArrayList<gestorCondimento> condimentos = new ArrayList<>();
            ArrayList<gestorPicante> picante = new ArrayList<>();
            ArrayList<GestorMenu> porcion = new ArrayList<>();
            ArrayList<gestorCoccion> coccion = new ArrayList<>();



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