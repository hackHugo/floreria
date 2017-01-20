package com.example.hackhugo.floreria.Utils;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by hackhugo on 26/11/2016.
 */

public class RealmConfiguracion {
    public void Config(Context context) {

        // The Realm file will be located in Context.getFilesDir() with name "default.realm"
        Realm.init(context);
        RealmConfiguration Config = new RealmConfiguration.Builder()
                .name("floreria.realm")
                .schemaVersion(2)
                .build();
        //Log.d("Ruta Realm: ",context.getFilesDir().toString());
        Realm.setDefaultConfiguration(Config);
    }
}
