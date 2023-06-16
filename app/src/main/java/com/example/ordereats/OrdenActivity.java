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
import com.example.ordereats.domain.AdapterSpinnerCoccion;
import com.example.ordereats.domain.AdapterSpinnerGuarnicion;
import com.example.ordereats.domain.AdapterSpinnerPicante;
import com.example.ordereats.domain.AdapterSpinnerPorcion;
import com.example.ordereats.domain.AdapterSpinnerProteina;
import com.example.ordereats.domain.GestorGuarnicion;
import com.example.ordereats.domain.gestorCoccion;
import com.example.ordereats.domain.gestorPicante;
import com.example.ordereats.domain.gestorPorcion;
import com.example.ordereats.domain.gestorProteina;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrdenActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    private DataApi data;

    private  RequestQueue requestQueue;
    private  RequestQueue requestQueuproteina;
    private RequestQueue requestQueueCoccion;
    private RequestQueue requestQueuePorcion;
    private RequestQueue requestQueueGuarnicion;
////////////////////////////////////////////////////////////////////////////////////////////////////
    private JsonObjectRequest jsonRequest;
    private JsonObjectRequest jsonRequestProteina;

    private JsonObjectRequest jsonRequestCoccion;
    private JsonObjectRequest jsonRequestPorcion;
    private JsonObjectRequest jsonRequestGuarnicion;

////////////////////////////////////////////////////////////////////////////////////////////////////
    private Spinner platillo;
    private Spinner ingredienteSpecial;
    private Spinner proteina;
    private Spinner guarnicion;
    private Spinner condimento;
    private Spinner picante;
    private Spinner porcion;
    private Spinner coccion;

    Button registrar;
////////////////////////////////////////////////////////////////////////////////////////////////////

    private ArrayList<gestorPicante> picantes;
    private ArrayList<gestorProteina> proteinas;

    private ArrayList<gestorCoccion> cocciones;
    private ArrayList<gestorPorcion> porciones;
    private ArrayList<GestorGuarnicion> guarniciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);


        requestQueue = Volley.newRequestQueue(this);
        data = new DataApi();
        jsonRequest = new JsonObjectRequest(Request.Method.GET,data.optenerNivelPicante,null,this,this);
        requestQueue.add(jsonRequest);
        Toast.makeText(this, "Enviando solicitud...", Toast.LENGTH_SHORT).show();

        picante = findViewById(R.id.spinnerNivelPicante);
        picantes = new ArrayList<>();

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        requestQueuproteina = Volley.newRequestQueue(this);
        jsonRequestProteina=new JsonObjectRequest(Request.Method.GET,data.optenerPoteina,null,this,this);
        requestQueuproteina.add(jsonRequestProteina);
        Toast.makeText(this, "Enviando solicitud...", Toast.LENGTH_SHORT).show();

        proteina = findViewById(R.id.spinnerProteína);
        proteinas = new ArrayList<>();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        requestQueueCoccion = Volley.newRequestQueue(this);
        jsonRequestCoccion=new JsonObjectRequest(Request.Method.GET,data.optenerCoccion,null,this,this);
        requestQueueCoccion.add(jsonRequestCoccion);
        Toast.makeText(this, "Enviando solicitud...", Toast.LENGTH_SHORT).show();

        coccion = findViewById(R.id.spinnerGradoCocción);
        cocciones= new ArrayList<>();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        requestQueuePorcion = Volley.newRequestQueue(this);
        jsonRequestPorcion=new JsonObjectRequest(Request.Method.GET,data.optenerTamanoPorcion,null,this,this);
        requestQueuePorcion.add(jsonRequestPorcion);
        Toast.makeText(this, "Enviando solicitud...", Toast.LENGTH_SHORT).show();

        porcion = findViewById(R.id.spinnerGradoCocción);
        porciones= new ArrayList<>();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        requestQueueGuarnicion = Volley.newRequestQueue(this);
        jsonRequestGuarnicion=new JsonObjectRequest(Request.Method.GET,data.optenerTamanoPorcion,null,this,this);
        requestQueueGuarnicion.add(jsonRequestGuarnicion);
        Toast.makeText(this, "Enviando solicitud...", Toast.LENGTH_SHORT).show();

        guarnicion = findViewById(R.id.spinnerGradoCocción);
        guarniciones= new ArrayList<>();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {


        try {
////////////////////////////////////////////////////////////////Picante///////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////Picante///////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////Proteinas///////////////////////////////////////////////////////////
            JSONArray jsonArrayProteina = response.getJSONArray("picante");
            ArrayList<gestorProteina>mostrarProteinas= new ArrayList<>();
            for (int i = 0; i < jsonArrayProteina.length(); i++) {
                JSONObject jsonObject = jsonArrayProteina.getJSONObject(i);
                int id_proteina = jsonObject.getInt("id_proteina");
                String nombre = jsonObject.getString("nombre");

                gestorPicante gestor = new gestorPicante(id_proteina,nombre);
                mostrarPicantes.add(gestor);
            }
            if (mostrarProteinas.isEmpty()) {
                Toast.makeText(this, "No hay Proteínas.", Toast.LENGTH_SHORT).show();
            } else {
                AdapterSpinnerProteina adapter = new AdapterSpinnerProteina(this, mostrarProteinas);
                proteina.setAdapter(adapter);
            }

////////////////////////////////////////////////////////////////Proteinas///////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////Coccion///////////////////////////////////////////////////////////
            JSONArray jsonArrayCoccion = response.getJSONArray("picante");
            ArrayList<gestorCoccion> mostrarCoccion= new ArrayList<>();
            for (int i = 0; i < jsonArrayCoccion.length(); i++) {
                JSONObject jsonObject = jsonArrayCoccion.getJSONObject(i);
                int id_grado_coccion = jsonObject.getInt("id_grado_coccion");
                String nombre = jsonObject.getString("nombre");

                gestorCoccion gestorcoccion = new gestorCoccion(id_grado_coccion,nombre);
                mostrarCoccion.add(gestorcoccion);
            }
            if (mostrarProteinas.isEmpty()) {
                Toast.makeText(this, "No hay occiones.", Toast.LENGTH_SHORT).show();
            } else {
                AdapterSpinnerCoccion adapterCoccion = new AdapterSpinnerCoccion(this, mostrarCoccion);
                coccion.setAdapter(adapterCoccion);
            }

////////////////////////////////////////////////////////////////Coccion///////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////Porcion///////////////////////////////////////////////////////////
            JSONArray jsonArrayPorcion = response.getJSONArray("picante");
            ArrayList<gestorPorcion> mostrarPorcion= new ArrayList<>();
            for (int i = 0; i < jsonArrayPorcion.length(); i++) {
                JSONObject jsonObject = jsonArrayPorcion.getJSONObject(i);
                int id_tamano_pocion = jsonObject.getInt("id_tamano_pocion");
                String nombre = jsonObject.getString("nombre");
                double precio = jsonObject.getDouble("precio");

                gestorPorcion gestorporcion = new gestorPorcion(id_tamano_pocion,nombre,precio);
                mostrarPorcion.add(gestorporcion);
            }
            if (mostrarPorcion.isEmpty()) {
                Toast.makeText(this, "No hay occiones.", Toast.LENGTH_SHORT).show();
            } else {
                //llamado al adapter
                AdapterSpinnerPorcion adapterPorcion = new AdapterSpinnerPorcion(this,mostrarPorcion);
                porcion.setAdapter(adapterPorcion);
            }

////////////////////////////////////////////////////////////////Porcion///////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////Guarnicion///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Porcion///////////////////////////////////////////////////////////

            JSONArray jsonArrayGuarnicion = response.getJSONArray("picante");
            ArrayList<GestorGuarnicion> mostrarGuarnicion= new ArrayList<>();
            for (int i = 0; i < jsonArrayGuarnicion.length(); i++) {
                JSONObject jsonObject = jsonArrayGuarnicion.getJSONObject(i);
                int id_guarniciones = jsonObject.getInt("id_guarniciones");
                String nombre = jsonObject.getString("nombre");
                double precio = jsonObject.getDouble("precio");
                boolean estado = jsonObject.getBoolean("estado");

                GestorGuarnicion gestorGuarnicion = new GestorGuarnicion(id_guarniciones,nombre,precio, estado);
                mostrarGuarnicion.add(gestorGuarnicion);
            }
            if (mostrarGuarnicion.isEmpty()) {
                Toast.makeText(this, "No hay occiones.", Toast.LENGTH_SHORT).show();
            } else {
                //llamado al adapter
                AdapterSpinnerGuarnicion adapterGuarnicion = new AdapterSpinnerGuarnicion(this, mostrarGuarnicion);
                guarnicion.setAdapter(adapterGuarnicion);

            }
////////////////////////////////////////////////////////////////Guarnicion///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Porcion///////////////////////////////////////////////////////////






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