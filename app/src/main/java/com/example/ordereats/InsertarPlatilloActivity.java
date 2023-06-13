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
import com.android.volley.toolbox.Volley;
import com.example.ordereats.data.DataApi;
import com.example.ordereats.domain.GestorMenu;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.UnknownHostException;

public class InsertarPlatilloActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
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

    public void agregarPlatillo(View view) throws UnknownHostException {
        String nombrePlatillo = nombreEditText.getText().toString();
        String descripcionPlatillo = descripcionEditText.getText().toString();
        String precioString = precioEditText.getText().toString();

        if (nombrePlatillo.isEmpty()) {
            Toast.makeText(this, "Falta el nombre del platillo", Toast.LENGTH_SHORT).show();
            return;
        }

        if (descripcionPlatillo.isEmpty()) {
            Toast.makeText(this, "Falta la descripción del platillo", Toast.LENGTH_SHORT).show();
            return;
        }

        if (precioString.isEmpty()) {
            Toast.makeText(this, "Falta el precio del platillo", Toast.LENGTH_SHORT).show();
            return;
        }

        double precioPlatillo;
        try {
            precioPlatillo = Double.parseDouble(precioString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "El precio del platillo debe ser un número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        String urlQuery = dataApi.insertarPlatillo + "&nombre=" + nombrePlatillo + "&descripcion=" + descripcionPlatillo + "&precio=" + precioPlatillo;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlQuery, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error al agregar el platillo", Toast.LENGTH_SHORT).show();
        Log.e("AgregarPlatillo", "Error: " + error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            // Obtener la respuesta del servidor
            String message = response.getString("message");

            // Mostrar mensaje de éxito
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            // Limpiar los campos de entrada
            nombreEditText.setText("");
            descripcionEditText.setText("");
            precioEditText.setText("");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void volverInicio(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}
