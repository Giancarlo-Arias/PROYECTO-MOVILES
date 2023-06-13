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
import com.android.volley.toolbox.Volley;
import com.example.ordereats.data.DataApi;
import com.example.ordereats.domain.GestorMenu;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.UnknownHostException;

public class InsertarGuarnicionActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private GestorMenu gestorMenu;
    private DataApi dataApi;
    private RequestQueue requestQueue;
    private EditText nombreEditText;
    private EditText precioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_guarnicion);
        requestQueue = Volley.newRequestQueue(this);
        gestorMenu = new GestorMenu();
        dataApi = new DataApi(); // Crear una instancia de DataApi
        nombreEditText = findViewById(R.id.nombreEditText);
        precioEditText = findViewById(R.id.precioEditText);
    }

    public void agregarGuarnicion(View view) throws UnknownHostException {
        String nombreGuarnicion = nombreEditText.getText().toString();
        String precioString = precioEditText.getText().toString();

        if (nombreGuarnicion.isEmpty()) {
            Toast.makeText(this, "Falta el nombre de la guarnición", Toast.LENGTH_SHORT).show();
            return;
        }

        if (precioString.isEmpty()) {
            Toast.makeText(this, "Falta el precio de la guarnición", Toast.LENGTH_SHORT).show();
            return;
        }

        double precioGuarnicion;
        try {
            precioGuarnicion = Double.parseDouble(precioString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "El precio de la guarnición debe ser un número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        String urlQuery = dataApi.insertarGuarnicion + "&nombre=" + nombreGuarnicion + "&precio=" + precioGuarnicion;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlQuery, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error al agregar la guarnición", Toast.LENGTH_SHORT).show();
        Log.e("AgregarGuarnicion", "Error: " + error.getMessage());
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
