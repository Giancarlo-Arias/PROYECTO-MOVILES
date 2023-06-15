package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class ActualizaPlatilloActivity extends AppCompatActivity {
    private DataApi platilloDB;
    private GestorMenu platillo;
    private RequestQueue requestQueue;
    private EditText editNombre;
    private EditText editDescripcion;
    private EditText editPrecio;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualiza_platillo);
        platilloDB = new DataApi();
        requestQueue = Volley.newRequestQueue(this);
        editNombre = findViewById(R.id.edit_nombre);
        editDescripcion = findViewById(R.id.edit_descripcion);
        editPrecio = findViewById(R.id.edit_precio);
        btnGuardar = findViewById(R.id.btn_guardar);
        platillo = (GestorMenu) getIntent().getSerializableExtra("platillo");
        if (platillo != null) {
            editNombre.setText(platillo.getNombre());
            editDescripcion.setText(platillo.getDescripcion());
            editPrecio.setText(String.valueOf(platillo.getPrecio()));
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los inputs
                String nombre = editNombre.getText().toString();
                String descripcion = editDescripcion.getText().toString();
                float precio = Float.parseFloat(editPrecio.getText().toString());

                // Validar campos vacíos
                if (nombre.isEmpty() || descripcion.isEmpty()) {
                    Toast.makeText(ActualizaPlatilloActivity.this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show();
                } else {
                    // Actualizar los datos en el backend usando Volley
                    String url = platilloDB.actualizarPlato + "&id=" + platillo.getId()
                            + "&nombre=" + nombre + "&descripcion=" + descripcion + "&precio=" + precio;

                    StringRequest request = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(ActualizaPlatilloActivity.this, "La actualización se realizó con éxito", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ActualizaPlatilloActivity.this, ListarPlatilloActivity.class);
                                    startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(ActualizaPlatilloActivity.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<>();
                            headers.put("Content-Type", "application/json");
                            return headers;
                        }
                    };

                    requestQueue.add(request);
                }
            }
        });
    }

    public void volverInicio(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
