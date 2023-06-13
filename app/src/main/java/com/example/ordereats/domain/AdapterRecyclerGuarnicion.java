package com.example.ordereats.domain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.ActualizaGuarnicionActivity;
import com.example.ordereats.R;
import com.example.ordereats.data.DataApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdapterRecyclerGuarnicion extends RecyclerView.Adapter<AdapterRecyclerGuarnicion.ViewHolderDatos> {
    private Context context;
    private List<GestorMenu> lista;
    private RequestQueue requestQueue;

    public AdapterRecyclerGuarnicion(Context context, List<GestorMenu> lista) {
        this.context = context;
        this.lista = lista;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_guarnicion, parent, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    private void eliminarGuarnicion(GestorMenu guarnicion) {
        DataApi guarnicionDB = new DataApi();
        String url = guarnicionDB.eliminarGuarnicionPorId + "&id=" + guarnicion.getId();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Guarnicion eliminada exitosamente", Toast.LENGTH_SHORT).show();

                        // Lógica para eliminar la guarnicion de la lista
                        lista = lista.stream()
                                .filter(i -> i.getId() != guarnicion.getId())
                                .collect(Collectors.toList());
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error al eliminar la guarnicion", Toast.LENGTH_SHORT).show();
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

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        private TextView nombreGuarnicion;
        private TextView precioGuarnicion;
        private Button buttonEliminar;
        private Button buttonEditar;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombreGuarnicion = itemView.findViewById(R.id.card_nombre_guarnicion);
            precioGuarnicion = itemView.findViewById(R.id.card_precio_guarnicion);
            buttonEliminar = itemView.findViewById(R.id.card_button_eliminar_guarnicion);
            buttonEditar = itemView.findViewById(R.id.card_editar_guarnicion);

            buttonEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = getAdapterPosition();
                    if (posicion != RecyclerView.NO_POSITION) {
                        GestorMenu guarnicion = lista.get(posicion);
                        eliminarGuarnicion(guarnicion);
                    }
                }
            });

            buttonEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = getAdapterPosition();
                    if (posicion != RecyclerView.NO_POSITION) {
                        GestorMenu guarnicion = lista.get(posicion);
                        abrirActualizaGuarnicionActivity(guarnicion);
                    }
                }
            });
        }

        public void asignarDatos(GestorMenu guarnicion) {
            nombreGuarnicion.setText(guarnicion.getNombre());
            precioGuarnicion.setText("Precio: " + guarnicion.getPrecio());
        }

        private void abrirActualizaGuarnicionActivity(GestorMenu guarnicion) {
            Intent intent = new Intent(context, ActualizaGuarnicionActivity.class);
            intent.putExtra("guarnicion", (CharSequence) guarnicion);
            context.startActivity(intent);
        }
    }
}
