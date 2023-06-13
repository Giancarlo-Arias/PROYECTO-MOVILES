package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.data.DataApi;
import com.example.ordereats.domain.GestorMenu;

import java.util.HashMap;
import java.util.Map;

public class InsertarPlatilloActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {
    private GestorMenu gestorMenu;
    private DataApi dataApi;
    private RequestQueue requestQueue;
    private EditText nombreEditText;
    private EditText descripcionEditText;
    private EditText precioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_platillo);
        requestQueue = Volley.newRequestQueue(this);
        gestorMenu = new GestorMenu();
        dataApi = new DataApi(); // Crear una instancia de DataApi
        nombreEditText = findViewById(R.id.nombreEditText);
        descripcionEditText = findViewById(R.id.descripcionEditText);
        precioEditText = findViewById(R.id.precioEditText);
    }

    public void agregarPlatillo(View view) {
        String nombre = nombreEditText.getText().toString();
        String descripcion = descripcionEditText.getText().toString();
        double precio = Double.parseDouble(precioEditText.getText().toString());

        boolean isFieldsValid = true;

        if (nombre.isEmpty()) {
            isFieldsValid = false;
            Toast.makeText(this, "Falta el nombre", Toast.LENGTH_SHORT).show();
        }

        if (descripcion.isEmpty()) {
            isFieldsValid = false;
            Toast.makeText(this, "Falta la descripción", Toast.LENGTH_SHORT).show();
        }

        if (precioEditText.getText().toString().isEmpty()) {
            isFieldsValid = false;
            Toast.makeText(this, "Falta el precio", Toast.LENGTH_SHORT).show();
        }

        if (isFieldsValid) {
            String url = dataApi.insertarPlatillo + "&nombre=" + nombre + "&descripcion=" + descripcion + "&precio=" + precio;
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(InsertarPlatilloActivity.this, "La inserción se realizó con éxito", Toast.LENGTH_SHORT).show();
                            nombreEditText.setText("");
                            descripcionEditText.setText("");
                            precioEditText.setText("");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(InsertarPlatilloActivity.this, "Error al insertar los datos", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };
            requestQueue.add(request);
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error al agregar el platillo", Toast.LENGTH_SHORT).show();
        Log.e("AgregarPlatillo", "Error: " + error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        // Lógica de respuesta exitosa
    }

    public void volverInicio(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
