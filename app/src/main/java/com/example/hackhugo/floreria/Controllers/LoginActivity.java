package com.example.hackhugo.floreria.Controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hackhugo.floreria.Modelos.Usuarios;
import com.example.hackhugo.floreria.R;
import com.example.hackhugo.floreria.Utils.DatosStaticos;
import com.example.hackhugo.floreria.databinding.ActivityLoginBinding;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by hackhugo on 04/12/2016.
 */

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(LoginActivity.this, RegistrarActivity.class);
                LoginActivity.this.startActivity(mainIntent);
                LoginActivity.this.finish();
            }
        });
        binding.btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                RealmQuery<Usuarios> query = realm.where(Usuarios.class);
                query.equalTo("Correo", binding.txtUser.getText().toString()).equalTo("Contrasena", binding.txtPass.getText().toString()).findAll();
                if (query.count() == 1) {
                    DatosStaticos.id = query.findFirst().getId();
                    DatosStaticos.correo = query.findFirst().getCorreo();
                    Intent mainIntent = new Intent(LoginActivity.this, MenuActivity.class);
                    LoginActivity.this.startActivity(mainIntent);
                    LoginActivity.this.finish();
                } else {
                    AlertUsuario();
                }
            }

        });
    }
    public void AlertUsuario() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Error");
        builder1.setMessage("Error el usuario o la contraseña no son correctos");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        binding.txtPass.setText("");
                        binding.txtUser.setText("");
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}

