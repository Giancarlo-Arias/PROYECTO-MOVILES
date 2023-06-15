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
import com.example.ordereats.ActualizaPlatilloActivity;
import com.example.ordereats.R;
import com.example.ordereats.data.DataApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdapterRecyclerPlatillos extends RecyclerView.Adapter<AdapterRecyclerPlatillos.ViewHolderDatos> {
    private Context context;
    private List<GestorMenu> lista;
    private RequestQueue requestQueue;

    public AdapterRecyclerPlatillos(Context context, List<GestorMenu> lista) {
        this.context = context;
        this.lista = lista;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_platillo, parent, false);
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

    private void eliminarPlatillo(GestorMenu platillo) {
        DataApi platilloDB = new DataApi();
        String url = platilloDB.eliminarPlatoPorId + "&id=" + platillo.getId();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Platillo eliminado exitosamente", Toast.LENGTH_SHORT).show();

                        // Lógica para eliminar el platillo de la lista
                        lista = lista.stream()
                                .filter(p -> p.getId() != platillo.getId())
                                .collect(Collectors.toList());
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error al eliminar el platillo", Toast.LENGTH_SHORT).show();
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
        private TextView nombrePlatillo;
        private TextView descripcionPlatillo;
        private TextView precioPlatillo;
        private Button buttonEliminar;
        private Button buttonEditar;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombrePlatillo = itemView.findViewById(R.id.card_nombre_platillo);
            descripcionPlatillo = itemView.findViewById(R.id.card_descripcion_platillo);
            precioPlatillo = itemView.findViewById(R.id.card_precio_platillo);
            buttonEliminar = itemView.findViewById(R.id.card_button_eliminar_platillo);
            buttonEditar = itemView.findViewById(R.id.card_editar_platillo);

            buttonEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = getAdapterPosition();
                    if (posicion != RecyclerView.NO_POSITION) {
                        GestorMenu platillo = lista.get(posicion);
                        eliminarPlatillo(platillo);
                    }
                }
            });

            buttonEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = getAdapterPosition();
                    if (posicion != RecyclerView.NO_POSITION) {
                        GestorMenu platillo = lista.get(posicion);
                        abrirActualizaPlatilloActivity(platillo);
                    }
                }
            });
        }

        public void asignarDatos(GestorMenu platillo) {
            nombrePlatillo.setText(platillo.getNombre());
            descripcionPlatillo.setText("Descripción: " + platillo.getDescripcion());
            precioPlatillo.setText("Precio: " + platillo.getPrecio());
        }

        private void abrirActualizaPlatilloActivity(GestorMenu platillo) {
            Intent intent = new Intent(context, ActualizaPlatilloActivity.class);
            intent.putExtra("platillo", platillo);
            context.startActivity(intent);
        }
    }
}

