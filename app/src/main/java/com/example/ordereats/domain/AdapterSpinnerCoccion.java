package com.example.ordereats.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ordereats.domain.GestorMenu;

import java.util.ArrayList;

public class AdapterSpinnerCoccion extends ArrayAdapter<gestorCoccion> {

    public AdapterSpinnerCoccion(Context context, ArrayList<GestorMenu> cocciones) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_coccion, parent, false);
        }

        gestorCoccion coccion = getItem(position);

        TextView textViewIdCoccion = convertView.findViewById(R.id.textViewIdCoccion);
        TextView textViewNombreCoccion = convertView.findViewById(R.id.textViewNombreCoccion);

        textViewIdCoccion.setText(String.valueOf(coccion.getId_grado_coccion()));
        textViewNombreCoccion.setText(coccion.getNombre());

        return convertView;
    }
}
