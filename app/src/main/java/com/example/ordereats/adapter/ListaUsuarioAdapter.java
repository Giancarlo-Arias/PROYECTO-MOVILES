package com.example.ordereats.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordereats.R;
import com.example.ordereats.domain.User;

import java.util.ArrayList;

public class ListaUsuarioAdapter extends RecyclerView.Adapter<ListaUsuarioAdapter.ContactoViewHolder> {

    private ArrayList<User> userArrayList;
    private OnItemClickListener listener;

    public ListaUsuarioAdapter(ArrayList<User> listaUser) {
        this.userArrayList = listaUser;
    }

    public interface OnItemClickListener {
        void onItemClick(User user);

        void onUpdateClick(User user);

        void onDeleteClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_usuario, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        final User user = userArrayList.get(position);
        holder.setDatos(user);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(user);
                }
            }
        });

        holder.buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onUpdateClick(user);
                }
            }
        });

        holder.buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDeleteClick(user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView txtnombree, txtapellidoo, txttelefonoo, txtcorreoo;
        Button buttonEditar, buttonEliminar;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnombree = itemView.findViewById(R.id.card_name);
            txtapellidoo = itemView.findViewById(R.id.card_user_lastname);
            //txttelefonoo = itemView.findViewById(R.id.car);
            //txtcorreoo = itemView.findViewById(R.id.txt_Correo);
            buttonEditar = itemView.findViewById(R.id.card_editar_usuario);
            buttonEliminar = itemView.findViewById(R.id.card_button_eliminar_usuario);
        }

        public void setDatos(User user) {
            txtnombree.setText(String.valueOf(user.getUser_Name()));
            txtapellidoo.setText(String.valueOf(user.getUser_Lastname()));
            //txttelefonoo.setText(String.valueOf(user.getUser_Phone()));
           // txtcorreoo.setText(String.valueOf(user.getUser_Email()));
        }
    }
}
