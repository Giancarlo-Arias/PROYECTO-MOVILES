package com.example.ordereats.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ordereats.domain.gestorProteina;

import java.util.ArrayList;

public class AdapterSpinnerProteina extends ArrayAdapter<gestorProteina> {
    private ArrayList<gestorProteina> proteinas;

    public AdapterSpinnerProteina(Context context, ArrayList<gestorProteina> proteinas) {
        super(context, 0, proteinas);
        this.proteinas = proteinas;
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
        gestorProteina proteina = proteinas.get(position);
        textView.setText(proteina.getNombre());

        return convertView;
    }
}
