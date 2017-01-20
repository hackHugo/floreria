package com.example.hackhugo.floreria.Controllers;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.hackhugo.floreria.Controllers.Fragments.EnviaFoto;
import com.example.hackhugo.floreria.Controllers.Fragments.FragmentEventos;
import com.example.hackhugo.floreria.Controllers.Fragments.FragmentFlores;
import com.example.hackhugo.floreria.Controllers.Fragments.ListaFecha;
import com.example.hackhugo.floreria.Controllers.Fragments.informacion;
import com.example.hackhugo.floreria.R;
import com.example.hackhugo.floreria.Utils.DatosStaticos;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager oFragment = getFragmentManager();
    String Flag = null;
    TextView lblCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();


        Flag = intent.getStringExtra("FlagFecha");
        if(Flag != null){
            oFragment.beginTransaction().replace(R.id.Conten_frame, new ListaFecha()).commit();
        }else {
            oFragment.beginTransaction().replace(R.id.Conten_frame, new FragmentEventos()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        lblCorreo = (TextView) header.findViewById(R.id.lblcorreo);
        lblCorreo.setText(DatosStaticos.correo);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Eventos) {
            oFragment.beginTransaction().replace(R.id.Conten_frame,new FragmentEventos()).commit();
        } else if (id == R.id.nav_Flores) {
            oFragment.beginTransaction().replace(R.id.Conten_frame,new FragmentFlores()).commit();
        } else if (id == R.id.nav_Foto) {
            oFragment.beginTransaction().replace(R.id.Conten_frame,new EnviaFoto()).commit();
        } else if (id == R.id.nav_Informacion) {
            oFragment.beginTransaction().replace(R.id.Conten_frame, new informacion()).commit();
        }else if (id == R.id.nav_Fecha) {
            oFragment.beginTransaction().replace(R.id.Conten_frame,new ListaFecha()).commit();
        } else if (id == R.id.nav_Salir) {
            Intent mainIntent = new Intent(MenuActivity.this, LoginActivity.class);
            MenuActivity.this.startActivity(mainIntent);
            MenuActivity.this.finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
