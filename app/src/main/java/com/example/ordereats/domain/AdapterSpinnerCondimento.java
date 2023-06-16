package com.example.ordereats.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ordereats.domain.gestorCondimento;

import java.util.ArrayList;

public class AdapterSpinnerCondimento extends ArrayAdapter<gestorCondimento> {
    private ArrayList<gestorCondimento> condimentos;

    public AdapterSpinnerCondimento(Context context, ArrayList<gestorCondimento> condimentos) {
        super(context, 0, condimentos);
        this.condimentos = condimentos;
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
        gestorCondimento condimento = condimentos.get(position);
        textView.setText(condimento.getNombre());

        return convertView;
    }
}
