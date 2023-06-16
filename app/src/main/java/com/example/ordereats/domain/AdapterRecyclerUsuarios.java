package com.example.ordereats.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ordereats.R;

import java.util.List;

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

        }

        public void asignarDatos(User usuario){
            nameUser.setText(usuario.getUser_Name());
            lastnameUser.setText("Apellido "+usuario.getUser_Lastname());
            emailUser.setText("Correo: "+usuario.getUser_Email());
        }


    }
}
