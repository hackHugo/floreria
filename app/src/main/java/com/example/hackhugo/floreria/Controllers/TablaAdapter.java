package com.example.hackhugo.floreria.Controllers;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.hackhugo.floreria.Modelos.Fechas;
import com.example.hackhugo.floreria.R;
import com.example.hackhugo.floreria.databinding.CellcustomBinding;

import java.util.ArrayList;

/**
 * Created by hackhugo on 05/12/2016.
 */

public class TablaAdapter extends ArrayAdapter<Fechas> {
    CellcustomBinding binding;
    public TablaAdapter(Context context, ArrayList<Fechas> objects) {
        super(context, 0, objects);
    }



    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        Fechas oFecha = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.cellcustom, parent, false);
            convertView = binding.getRoot();
        }
        binding.txtdes.setText(oFecha.getDescripcion());
        binding.txtfecha.setText(oFecha.getFecha());
        return convertView;
    }
}
