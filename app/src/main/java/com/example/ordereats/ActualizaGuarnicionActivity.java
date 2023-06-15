package com.example.ordereats;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.data.DataApi;
import com.example.ordereats.domain.GestorMenu;
import org.json.JSONException;
import org.json.JSONObject;

public class ActualizaGuarnicionActivity extends AppCompatActivity {
    private DataApi guarnicionDB;
    private EditText editTextNombre;
    private EditText editTextPrecio;
    private Button buttonActualizar;

    private GestorMenu guarnicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualiza_guarnicion);
        guarnicionDB = new DataApi();
        // Obtener los elementos de la interfaz
        editTextNombre = findViewById(R.id.edit_nombre);
        editTextPrecio = findViewById(R.id.edit_precio);
        buttonActualizar = findViewById(R.id.btn_guardar);

        guarnicion = (GestorMenu) getIntent().getSerializableExtra("guarnicion");
        editTextNombre.setText(guarnicion.getNombre());
        editTextPrecio.setText(String.valueOf(guarnicion.getPrecio()));
        buttonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoNombre = editTextNombre.getText().toString().trim();
                double nuevoPrecio = Double.parseDouble(editTextPrecio.getText().toString().trim());
                actualizarGuarnicion(guarnicion.getId(), nuevoNombre, nuevoPrecio);
            }
        });
    }

    private void actualizarGuarnicion(int idGuarnicion, String nuevoNombre, double nuevoPrecio) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id", idGuarnicion);
            jsonBody.put("nombre", nuevoNombre);
            jsonBody.put("precio", nuevoPrecio);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest request = new StringRequest(Request.Method.GET, guarnicionDB.actualizarGuarnicion+"&id=" + idGuarnicion + "&nombre=" + nuevoNombre + "&precio=" + nuevoPrecio,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(ActualizaGuarnicionActivity.this, "Guarnicion actualizada correctamente", Toast.LENGTH_SHORT).show();

                                finish();
                            } else {
                                String error = jsonObject.getString("error");
                                Toast.makeText(ActualizaGuarnicionActivity.this, "Error al actualizar la guarnicion: " + error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActualizaGuarnicionActivity.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(request);
    }
}
