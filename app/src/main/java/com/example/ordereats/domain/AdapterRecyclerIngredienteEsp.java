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
import com.example.ordereats.ActualizaIngredienteEspecialActivity;
import com.example.ordereats.R;
import com.example.ordereats.data.DataApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdapterRecyclerIngredienteEsp extends RecyclerView.Adapter<AdapterRecyclerIngredienteEsp.ViewHolderDatos> {
    private Context context;
    private List<GestorMenu> lista;
    private RequestQueue requestQueue;

    public AdapterRecyclerIngredienteEsp(Context context, List<GestorMenu> lista) {
        this.context = context;
        this.lista = lista;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_ingrediente_especial, parent, false);
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

    private void eliminarIngredienteEspecial(GestorMenu ingredienteEspecial) {
        DataApi ingredienteEspecialDB = new DataApi();
        String url = ingredienteEspecialDB.eliminarIngredienteEspecial + "&id=" + ingredienteEspecial.getId();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Ingrediente especial eliminado exitosamente", Toast.LENGTH_SHORT).show();

                        // Lógica para eliminar el ingrediente especial de la lista
                        lista = lista.stream()
                                .filter(i -> i.getId() != ingredienteEspecial.getId())
                                .collect(Collectors.toList());
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error al eliminar el ingrediente especial", Toast.LENGTH_SHORT).show();
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
        private TextView nombreIngredienteEspecial;
        private TextView precioIngredienteEspecial;
        private Button buttonEliminar;
        private Button buttonEditar;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombreIngredienteEspecial = itemView.findViewById(R.id.card_nombre_ingrediente_especial);
            precioIngredienteEspecial = itemView.findViewById(R.id.card_precio_ingrediente_especial);
            buttonEliminar = itemView.findViewById(R.id.card_button_eliminar_ingrediente_especial);
            buttonEditar = itemView.findViewById(R.id.card_editar_ingrediente_especial);

            buttonEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = getAdapterPosition();
                    if (posicion != RecyclerView.NO_POSITION) {
                        GestorMenu ingredienteEspecial = lista.get(posicion);
                        eliminarIngredienteEspecial(ingredienteEspecial);
                    }
                }
            });

            buttonEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicion = getAdapterPosition();
                    if (posicion != RecyclerView.NO_POSITION) {
                        GestorMenu ingredienteEspecial = lista.get(posicion);
                        abrirActualizaIngredienteEspecialActivity(ingredienteEspecial);
                    }
                }
            });
        }

        public void asignarDatos(GestorMenu ingredienteEspecial) {
              nombreIngredienteEspecial.setText(ingredienteEspecial.getNombre());
            precioIngredienteEspecial.setText("Precio: " + ingredienteEspecial.getPrecio());
        }

        private void abrirActualizaIngredienteEspecialActivity(GestorMenu ingredienteEspecial) {
            Intent intent = new Intent(context, ActualizaIngredienteEspecialActivity.class);
            intent.putExtra("ingredienteEspecial",ingredienteEspecial);

            context.startActivity(intent);
        }
    }
}
