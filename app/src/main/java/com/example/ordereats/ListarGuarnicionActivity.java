package com.example.ordereats;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.data.DataApi;
import com.example.ordereats.domain.GestorMenu;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import com.example.ordereats.domain.AdapterRecyclerGuarnicion;

public class ListarGuarnicionActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private int ID_GUARNICION = 0;
    private DataApi guarnicionDB;
    private RequestQueue requestQueue;
    private ArrayList<GestorMenu> guarnicion;
    private JsonObjectRequest jsonRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_guarnicion);
        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        ID_GUARNICION = intent.getIntExtra("id_guarniciones", 0);
        guarnicionDB = new DataApi();
        jsonRequest = new JsonObjectRequest(Request.Method.GET, guarnicionDB.obtenerTodasGuarnicion, null, this, this);
        requestQueue.add(jsonRequest);
        Toast.makeText(this, "Enviando solicitud...", Toast.LENGTH_SHORT).show();

        guarnicion = new ArrayList<>();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("guarniciones");
            ArrayList<GestorMenu> guarniciones = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idGuarnicion = jsonObject.getInt("id_guarniciones");
                //Toast.makeText(this, "idGuarnicion."+idGuarnicion, Toast.LENGTH_SHORT).show();
                String nombre = jsonObject.getString("nombre");
                //Toast.makeText(this, "nombre."+nombre, Toast.LENGTH_SHORT).show();
                double precio = jsonObject.getDouble("precio");
                //Toast.makeText(this, "precio."+precio, Toast.LENGTH_SHORT).show();
                GestorMenu guarnicion = new GestorMenu(idGuarnicion, nombre,"nada", precio);
                guarniciones.add(guarnicion);
            }
            if (guarniciones.isEmpty()) {
                Toast.makeText(this, "No hay guarniciones.", Toast.LENGTH_SHORT).show();
            } else {
                // Asegúrate de tener el RecyclerView en tu layout y asignarle el adaptador
                RecyclerView recycler = findViewById(R.id.recyclerGuarniciones);
                AdapterRecyclerGuarnicion adapter = new AdapterRecyclerGuarnicion(this, guarniciones);
                recycler.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error al obtener las guarniciones.", Toast.LENGTH_SHORT).show();
    }

    public void volverInicio(View view) {
        Intent intent = new Intent(view.getContext(), Menu.class);
        view.getContext().startActivity(intent);
    }

    public void agregarGuarnicion(View view) {
        Intent intent = new Intent(view.getContext(), InsertarGuarnicionActivity.class);
        view.getContext().startActivity(intent);
    }
}
