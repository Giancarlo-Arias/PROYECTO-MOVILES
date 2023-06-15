package com.example.ordereats;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.data.DataApi;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.UnknownHostException;

public class InsertarIngredienteEspecialActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private DataApi dataApi;
    private RequestQueue requestQueue;
    private EditText nombreEditText;
    private EditText precioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_ingrediente_especial);
        requestQueue = Volley.newRequestQueue(this);
        dataApi = new DataApi();
        nombreEditText = findViewById(R.id.nombreEditText);
        precioEditText = findViewById(R.id.precioEditText);
    }

    public void agregarIngredienteEspecial(View view) throws UnknownHostException {
        String nombreIngrediente = nombreEditText.getText().toString();
        String precioString = precioEditText.getText().toString();

        if (nombreIngrediente.isEmpty()) {
            Toast.makeText(this, "Falta el nombre del ingrediente especial", Toast.LENGTH_SHORT).show();
            return;
        }

        if (precioString.isEmpty()) {
            Toast.makeText(this, "Falta el precio del ingrediente especial", Toast.LENGTH_SHORT).show();
            return;
        }

        double precioIngrediente;
        try {
            precioIngrediente = Double.parseDouble(precioString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "El precio del ingrediente especial debe ser un número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        String urlQuery = dataApi.insertarIngredienteEspecial + "&nombre=" + nombreIngrediente + "&precio=" + precioIngrediente;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlQuery, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error al agregar el ingrediente especial", Toast.LENGTH_SHORT).show();
        Log.e("AgregarIngrediente", "Error: " + error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            String message = response.getString("success");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            nombreEditText.setText("");
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
