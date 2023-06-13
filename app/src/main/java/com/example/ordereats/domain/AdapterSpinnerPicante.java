package com.example.ordereats.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ordereats.domain.gestorPicante;

import java.util.ArrayList;

public class AdapterSpinnerPicante extends ArrayAdapter<gestorPicante> {

    public AdapterSpinnerPicante(Context context, ArrayList<gestorPicante> picantes) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_picante, parent, false);
        }

        gestorPicante picante = getItem(position);

        TextView textViewIdPicante = convertView.findViewById(R.id.textViewIdPicante);
        TextView textViewNombrePicante = convertView.findViewById(R.id.textViewNombrePicante);

        textViewIdPicante.setText(String.valueOf(picante.getId_nivel_picante())));
        textViewNombrePicante.setText(picante.getNombre());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_picante_dropdown, parent, false);
        }

        gestorPicante picante = getItem(position);

        TextView textViewNombrePicante = convertView.findViewById(R.id.textViewNombrePicanteDropdown);

        textViewNombrePicante.setText(picante.getNombre());

        return convertView;
    }
}
