package com.example.ordereats.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSpinnerPorcion extends ArrayAdapter<GestorMenu> {

    public AdapterSpinnerPorcion(Context context, ArrayList<gestorCondimento> porciones) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
           // convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_porcion, parent, false);
        }

        GestorMenu porcion = getItem(position);

       // TextView textViewIdPorcion = convertView.findViewById(R.id.textViewIdPorcion);
       // TextView textViewNombrePorcion = convertView.findViewById(R.id.textViewNombrePorcion);

        //textViewIdPorcion.setText(String.valueOf(porcion.getId()));
        //textViewNombrePorcion.setText(porcion.getNombre());

        return convertView;
    }
}

