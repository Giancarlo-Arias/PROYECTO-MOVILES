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

public class ActualizaIngredienteEspecialActivity extends AppCompatActivity {
    private DataApi ingredienteEspecialDB;
    private RequestQueue requestQueue;
    private EditText editNombre;
    private EditText editPrecio;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualiza_ingrediente_especial);
        ingredienteEspecialDB = new DataApi();
        requestQueue = Volley.newRequestQueue(this);
        editNombre = findViewById(R.id.edit_nombre);
        editPrecio = findViewById(R.id.edit_precio);
        btnGuardar = findViewById(R.id.btn_guardar);

        Intent intent = getIntent();
        GestorMenu ingredienteEspecial = (GestorMenu) intent.getSerializableExtra("ingredienteEspecial");

        if (ingredienteEspecial != null) {
            // Mostrar la información del objeto en los inputs
            editNombre.setText(ingredienteEspecial.getNombre());
            editPrecio.setText(String.valueOf(ingredienteEspecial.getPrecio()));
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los inputs
                String nombre = editNombre.getText().toString();
                float precio = Float.parseFloat(editPrecio.getText().toString());

                // Validar campos vacíos
                if (nombre.isEmpty()) {
                    Toast.makeText(ActualizaIngredienteEspecialActivity.this, "El campo nombre debe estar lleno", Toast.LENGTH_SHORT).show();
                } else {
                    // Actualizar los datos en el backend usando Volley
                    String url = ingredienteEspecialDB.actualizarIngredienteEspecial + "&id=" + ingredienteEspecial.getId()
                            + "&nombre=" + nombre + "&precio=" + precio;

                    StringRequest request = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(ActualizaIngredienteEspecialActivity.this, "La actualización se realizó con éxito", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ActualizaIngredienteEspecialActivity.this, ListarIngredienteEspecialActivity.class);
                                    startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(ActualizaIngredienteEspecialActivity.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
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
