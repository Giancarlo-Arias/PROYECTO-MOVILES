package com.example.ordereats.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ordereats.domain.gestorPlatillos;

import java.util.ArrayList;

public class AdapterSpinnerPlatillos extends ArrayAdapter<gestorPlatillos> {
    private ArrayList<gestorPlatillos> platillos;

    public AdapterSpinnerPlatillos(Context context, ArrayList<gestorPlatillos> platillos) {
        super(context, 0, platillos);
        this.platillos = platillos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        gestorPlatillos platillo = platillos.get(position);
        textView.setText(platillo.getNombre());

        return convertView;
    }
}
