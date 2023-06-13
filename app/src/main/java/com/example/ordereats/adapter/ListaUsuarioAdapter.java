package com.example.ordereats.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordereats.R;
import com.example.ordereats.domain.User;

import java.util.ArrayList;

public class ListaUsuarioAdapter extends RecyclerView.Adapter<ListaUsuarioAdapter.contactoViewHolder> {

    ArrayList<User> userArrayList;

    public ListaUsuarioAdapter(ArrayList<User> listaUser){
        this.userArrayList = listaUser;
    }



    @NonNull
    @Override
    public ListaUsuarioAdapter.contactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item,null,false);
       // return new contactoViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaUsuarioAdapter.contactoViewHolder holder, int position) {
        final User user = userArrayList.get(position);
        holder.setDatos(user);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class contactoViewHolder extends RecyclerView.ViewHolder{
        TextView txtnombree,txtapellidoo,txttelefonoo, txtcorreoo;


        public contactoViewHolder(@NonNull View itemView) {
            super(itemView);
            //txtnombree = itemView.findViewById(R.id.txt_Name);
           // txtapellidoo = itemView.findViewById(R.id.txt_Lastname);
            //txttelefonoo = itemView.findViewById(R.id.txt_Phone);
            //txtcorreoo = itemView.findViewById(R.id.txt_Correo);
        }
        public void setDatos(User user){
            txtnombree.setText(String.valueOf(user.getUser_Name()));
            txtapellidoo.setText(String.valueOf(user.getUser_Lastname()));
            txttelefonoo.setText(String.valueOf(user.getUser_Phone()));
            txtcorreoo.setText(String.valueOf(user.getUser_Email()));
        }
    }
}
