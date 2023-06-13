package com.example.ordereats.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ordereats.R;

import java.util.ArrayList;

public class AdapterSpinnerCondimentos extends ArrayAdapter<GestorMenu> {

    public AdapterSpinnerCondimentos(Context context, ArrayList<gestorCondimento> condimentos) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
           // convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_condimento, parent, false);
        }

        GestorMenu condimento = getItem(position);

       // TextView textViewIdCondimento = convertView.findViewById(R.id.textViewIdCondimento);
       // TextView textViewNombreCondimento = convertView.findViewById(R.id.textViewNombreCondimento);

       // textViewIdCondimento.setText(String.valueOf(condimento.getId()));
        // textViewNombreCondimento.setText(condimento.getNombre());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_condimento_dropdown, parent, false);
        }

        GestorMenu condimento = getItem(position);

       // TextView textViewNombreCondimento = convertView.findViewById(R.id.textViewNombreCondimentoDropdown);

       // textViewNombreCondimento.setText(condimento.getNombre());

        return convertView;
    }
}
