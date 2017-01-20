package com.example.hackhugo.floreria.Controllers.Fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.hackhugo.floreria.R;
import com.example.hackhugo.floreria.databinding.ActivityEventosBinding;

/**
 * Created by hackhugo on 04/12/2016.
 */

public class FragmentEventos extends Fragment {
    View myView;
    //frangmento de la vista  eventos
    ActivityEventosBinding binding;
    private String[] eventos = {"boda", "quince años", "bautizo", "cumpleaños",
            "pésame", "aniversario", "10 de mayo", "San valentín"};
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, eventos);
        binding.listView.setAdapter(adaptador);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_eventos, container, false);
        return binding.getRoot();
    }
}
