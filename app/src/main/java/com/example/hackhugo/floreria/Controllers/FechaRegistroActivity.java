package com.example.hackhugo.floreria.Controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hackhugo.floreria.Modelos.Fechas;
import com.example.hackhugo.floreria.R;
import com.example.hackhugo.floreria.databinding.ActivityFechaRegistroBinding;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

public class FechaRegistroActivity extends AppCompatActivity implements Validator.ValidationListener{
    ActivityFechaRegistroBinding binding;
    String fecha = "";
    Validator oValidator;
    @NotEmpty(message = "El campo es requerido")
    EditText txtdescrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fecha_registro);
        oValidator = new Validator(this);
        oValidator.setValidationListener(this);
        txtdescrip = binding.editText2;
        binding.calendarView3.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
               // dateDisplay.setText("Date: " + i2 + " / " + i1 + " / " + i);
                fecha = i2+"-"+i1+"-"+i;
                Toast.makeText(getApplicationContext(), "Seleccionaste la fecha:\n" + "Dia = " + i2 + "\n" + "Mes = " + i1 + "\n" + "Año = " + i, Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fecha != "") {
                    oValidator.validate();
                }else{
                    validarFecha();
                }
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Fechas> query = realm.where(Fechas.class);
        Fechas oFechas = new Fechas();
        if (query.count() == 0) {
            oFechas.setId(1);
        } else {
            oFechas.setId(query.max("id").intValue() + 1);

        }
        oFechas.setDescripcion(binding.editText2.getText().toString());
        oFechas.setFecha(fecha);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(oFechas);
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
                        Intent mainIntent = new Intent(FechaRegistroActivity.this, MenuActivity.class);
                        mainIntent.putExtra("FlagFecha","fecha");
                        FechaRegistroActivity.this.startActivity(mainIntent);
                        FechaRegistroActivity.this.finish();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void validarFecha(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Se requiere que selecciones una fecha");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
