package com.example.hackhugo.floreria.Controllers.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackhugo.floreria.Controllers.FechaRegistroActivity;
import com.example.hackhugo.floreria.Controllers.TablaAdapter;
import com.example.hackhugo.floreria.Modelos.Fechas;
import com.example.hackhugo.floreria.R;
import com.example.hackhugo.floreria.databinding.ActivityFechaBinding;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by hackhugo on 04/12/2016.
 */

public class ListaFecha extends Fragment {
    ActivityFechaBinding binding;
    FragmentManager oFragment = getFragmentManager();
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Realm realm = Realm.getDefaultInstance();
        RealmQuery<Fechas> query = realm.where(Fechas.class);
        ArrayList<Fechas> List = new ArrayList<Fechas>();

        List.addAll(query.findAll());
        TablaAdapter tabla = new TablaAdapter(getActivity(), List);
        binding.listView2.setAdapter(tabla);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),FechaRegistroActivity.class);
                getActivity().startActivity(mainIntent);
                getActivity().finish();
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_fecha, container, false);
        return binding.getRoot();
    }
}
