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
import com.example.hackhugo.floreria.databinding.ActivityFloresBinding;

/**
 * Created by hackhugo on 04/12/2016.
 */

public class FragmentFlores extends Fragment {
    View myView;
    private String[] flores = {"rosas", "tulipanes", "lillies", "orientales",
            "alcatraz", "orquídeas", "ave del paraiso", "girasol"};
    ActivityFloresBinding binding;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, flores);
        binding.listView.setAdapter(adaptador);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_flores, container, false);
        return binding.getRoot();
    }
}
