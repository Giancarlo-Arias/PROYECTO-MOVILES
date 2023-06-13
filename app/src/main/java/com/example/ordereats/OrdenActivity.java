package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.data.DataApi;
import com.example.ordereats.domain.AdapterRecyclerPlatillos;
import com.example.ordereats.domain.AdapterSpinnerCoccion;
import com.example.ordereats.domain.AdapterSpinnerCondimentos;
import com.example.ordereats.domain.AdapterSpinnerGuarniciones;
import com.example.ordereats.domain.AdapterSpinnerPorcion;
import com.example.ordereats.domain.AdapterSpinnerProteinas;
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
        requestQueue = Volley.newRequestQueue(this);
        ordenDB = new DataApi();
        jsonRequest = new JsonObjectRequest(Request.Method.GET, ordenDB.obtenerPlatillos, null, this, this);
        requestQueue.add(jsonRequest);
        platillo = findViewById(R.id.spinnerPlatillo);

        ordenDB = new ArrayList<>();
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
                platillo.setAdapter((SpinnerAdapter) adapter);

            }

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nombre = jsonObject.getString("nombre");
                double precio = jsonObject.getDouble("precio");
                gestorEspeciales especial = new gestorEspeciales(nombre, precio); // Solo se pasan nombre y precio en el constructor
                especiales.add(especial);
            }

            if (especiales.isEmpty()) {
                Toast.makeText(this, "No hay especiales.", Toast.LENGTH_SHORT).show();
            } else {
                AdapterRecyclerPlatillos adapter = new AdapterRecyclerPlatillos(this, especiales);
                platillo.setAdapter((SpinnerAdapter) adapter);
            }


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idProteina = jsonObject.getInt("idProteina");
                String nombre = jsonObject.getString("nombre");
                gestorProteina proteinaItem = new gestorProteina(idProteina, nombre); // Pasamos idProteina y nombre en el constructor
                proteinas.add(proteinaItem);
            }

            if (proteinas.isEmpty()) {
                Toast.makeText(this, "No hay proteínas.", Toast.LENGTH_SHORT).show();
            } else {
                AdapterSpinnerProteinas adapter = new AdapterSpinnerProteinas(this, proteinas);
                proteina.setAdapter(adapter);
            }


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String nombre = jsonObject.getString("nombre");
                GestorGuarnicion guarnicionItem = new GestorGuarnicion(id, nombre); // Pasamos id y nombre en el constructor
                guarniciones.add(guarnicionItem);
            }

            if (guarniciones.isEmpty()) {
                Toast.makeText(this, "No hay guarniciones.", Toast.LENGTH_SHORT).show();
            } else {
                AdapterSpinnerGuarniciones adapter = new AdapterSpinnerGuarniciones(this, guarniciones);
                guarnicion.setAdapter(adapter);
            }


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idCondimento = jsonObject.getInt("id_Condimento");
                String nombre = jsonObject.getString("nombre");
                double precio = jsonObject.getDouble("precio");
                gestorCondimento condimento = new gestorCondimento(idCondimento, nombre, precio);
                condimentos.add(condimento);
            }

// ...

            AdapterSpinnerCondimentos adapter = new AdapterSpinnerCondimentos(this, condimentos);
            condimento.setAdapter(adapter);


// ...

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idPicante = jsonObject.getInt("id_nivel_picante");
                String nombre = jsonObject.getString("nombre");
                gestorPicante picantes = new gestorPicante(idPicante, nombre);
                picante.add(picantes);
            }
            AdapterSpinnerCondimentos adapter1 = new AdapterSpinnerCondimentos(this, condimentos);
            condimento.setAdapter(adapter1);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idPorcion = jsonObject.getInt("id_tamano_porcion");
                String nombre = jsonObject.getString("nombre");
                double precio = jsonObject.getDouble("precio");
                GestorMenu porciones = new GestorMenu(idPorcion, nombre, precio);
                porcion.add(porciones);
            }
            AdapterSpinnerPorcion adapterporcion = new AdapterSpinnerPorcion(this, condimentos);
            condimento.setAdapter(adapterporcion);


            gestorCoccion cocciones = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idCoccion = jsonObject.getInt("id_coccion");
                String nombre = jsonObject.getString("nombre");

                gestorCoccion cocciones = new gestorCoccion(idCoccion, nombre);
                coccion.add(cocciones);
            }

            AdapterSpinnerCoccion adapter3 = new AdapterSpinnerCoccion(getApplicationContext(), cocciones);
            coccion.setAdapter(adapter3);


// ...


        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
    }


    }
    public void volverInicio(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(intent);
    }

}