package com.example.ordereats.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSpinnerGuarniciones extends ArrayAdapter<GestorMenu> {

    public AdapterSpinnerGuarniciones(Context context, ArrayList<GestorGuarnicion> guarniciones) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
           // convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_guarnicion, parent, false);
        }

        GestorMenu guarnicion = getItem(position);

       // TextView textViewIdGuarnicion = convertView.findViewById(R.id.textViewIdGuarnicion);
       // TextView textViewNombreGuarnicion = convertView.findViewById(R.id.textViewNombreGuarnicion);

       // textViewIdGuarnicion.setText(String.valueOf(guarnicion.getId()));
       // textViewNombreGuarnicion.setText(guarnicion.getNombre());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
          //  convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_guarnicion_dropdown, parent, false);
        }

        GestorMenu guarnicion = getItem(position);

       // TextView textViewNombreGuarnicion = convertView.findViewById(R.id.textViewNombreGuarnicionDropdown);

        //textViewNombreGuarnicion.setText(guarnicion.getNombre());

        return convertView;
    }
}
