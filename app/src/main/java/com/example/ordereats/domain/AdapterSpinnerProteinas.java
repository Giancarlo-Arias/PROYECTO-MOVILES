package com.example.ordereats.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ordereats.domain.GestorMenu;

import java.util.ArrayList;

public class AdapterSpinnerProteinas extends ArrayAdapter<GestorMenu> {

    public AdapterSpinnerProteinas(Context context, ArrayList<GestorMenu> proteinas) {
        super(context, 0, proteinas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        GestorMenu proteina = getItem(position);

        TextView textViewNombreProteina = convertView.findViewById(android.R.id.text1);
        textViewNombreProteina.setText(proteina.getNombre());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        GestorMenu proteina = getItem(position);

        TextView textViewNombreProteina = convertView.findViewById(android.R.id.text1);
        textViewNombreProteina.setText(proteina.getNombre());

        return convertView;
    }
}
