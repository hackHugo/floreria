package com.example.hackhugo.floreria.Controllers.Fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackhugo.floreria.R;
import com.example.hackhugo.floreria.databinding.ActivityInformacionBinding;

/**
 * Created by hackhugo on 05/12/2016.
 */

public class informacion extends Fragment {
    ActivityInformacionBinding binding;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_informacion, container, false);
        return binding.getRoot();
    }
}
