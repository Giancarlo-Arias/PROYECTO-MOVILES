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
import com.example.ordereats.R;
import com.example.ordereats.data.DataApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdapterRecyclerUsuarios extends RecyclerView.Adapter<AdapterRecyclerUsuarios.ViewHolderDatos> {


    private Context context;

    private List<User> lista;

    private RequestQueue requestQueue;

    public AdapterRecyclerUsuarios(Context context, List<User> lista){
        this.context = context;
        this.lista = lista;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_usuario,parent,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(lista.get(position));
    }

    @Override
    public int getItemCount() {return lista.size();
    }

    private void eliminarusuario(User usuario){

        DataApi userDB = new DataApi();
        String url = userDB.eliminarUsuario + "&id=" + usuario.getUser_Id();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Usuario eliminado exitosamente", Toast.LENGTH_SHORT).show();

                        lista = lista.stream()
                                .filter(p -> p.getUser_Id() != usuario.getUser_Id())
                                .collect(Collectors.toList());
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error al eliminar el usuario", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        requestQueue.add(request);
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{
        private TextView nameUser;
        private TextView lastnameUser;
        private TextView emailUser;
        private Button btnDele;
        private Button btnEdit;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nameUser = itemView.findViewById(R.id.card_name);
            lastnameUser =itemView.findViewById(R.id.card_user_lastname);
            emailUser = itemView.findViewById(R.id.card_usuario_correo);
            btnEdit = itemView.findViewById(R.id.card_editar_usuario);
            btnDele = itemView.findViewById(R.id.card_button_eliminar_usuario);//revisar
            btnDele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posicion = getAdapterPosition();
                    if (posicion != RecyclerView.NO_POSITION){
                        User user = lista.get(posicion);
                        eliminarusuario(user);
                    }
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posicion = getAdapterPosition();
                    if(posicion != RecyclerView.NO_POSITION){
                        User user = lista.get(posicion);
                        //editarUsuarioActivity(user);
                    }
                }
            });
        }

        public void asignarDatos(User usuario){
            nameUser.setText(usuario.getUser_Name());
            lastnameUser.setText("Apellido "+usuario.getUser_Lastname());
            emailUser.setText("Correo: "+usuario.getUser_Email());
        }

        /*private void editarUsuarioActivity(User user){
            Intent intent = new Intent(context, editarUsuarioActivity.class);
            intent.putExtra("usuario", user);
            context.startActivity(intent);
        }*/

    }
}
