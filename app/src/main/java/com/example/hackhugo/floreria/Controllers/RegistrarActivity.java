package com.example.hackhugo.floreria.Controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hackhugo.floreria.Modelos.Usuarios;
import com.example.hackhugo.floreria.R;
import com.example.hackhugo.floreria.databinding.ActivityRegistrarBinding;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by hackhugo on 04/12/2016.
 */

public class RegistrarActivity extends AppCompatActivity implements Validator.ValidationListener {
    ActivityRegistrarBinding binding;
    Validator oValidator;
    @NotEmpty(message = "El campo es requerido")
    EditText txtnombre;
    @NotEmpty(message = "El campo es requerido")
    EditText txtcorreo;
    @Password(min = 6, scheme = Password.Scheme.ANY)
    EditText txtpass;
    @ConfirmPassword(message = "Repetir correctamente la Contraseña")
    EditText txtrepitpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_registrar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        oValidator = new Validator(this);
        oValidator.setValidationListener(this);
        txtnombre = binding.txtNombre;
        txtcorreo = binding.txtCorreo;
        txtpass = binding.txtContrasena;
        txtrepitpass = binding.txtRepetirCon;
        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oValidator.validate();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent mainIntent = new Intent(RegistrarActivity.this, LoginActivity.class);
                RegistrarActivity.this.startActivity(mainIntent);
                RegistrarActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onValidationSucceeded() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Usuarios> query = realm.where(Usuarios.class);
        Usuarios oUsuario = new Usuarios();
        if (query.count() == 0) {
            oUsuario.setId(1);
        } else {
            oUsuario.setId(query.max("id").intValue() + 1);

        }
        oUsuario.setNombre(binding.txtNombre.getText().toString());
        oUsuario.setCorreo(binding.txtCorreo.getText().toString());
        oUsuario.setContrasena(binding.txtContrasena.getText().toString());
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(oUsuario);
        realm.commitTransaction();
        AlertGuardar();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void AlertGuardar() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Tus datos se guardaron correctamente");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent mainIntent = new Intent(RegistrarActivity.this, LoginActivity.class);
                        RegistrarActivity.this.startActivity(mainIntent);
                        RegistrarActivity.this.finish();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
