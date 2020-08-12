package com.example.a10117279_wisatabandung;

//Tanggal : 8 - Agustus - 2020
//NIM : 10117279
//Nama : Muhammad Ibnu Sina
//Kelas : IF-8

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SimpanActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    private ListView listView;

    private DBDataSource dataSource;

    private ArrayList<Model> values;

    ListWisataAdapter listWisataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpan);
        listView = findViewById(R.id.favList);

        Toolbar simpanToolbar = findViewById(R.id.simpanToolbar);
        setSupportActionBar(simpanToolbar);
        getSupportActionBar().setTitle("Wisata Favorite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = findViewById(R.id.drawer_simpan);
        NavigationView navigationView = findViewById(R.id.nav_simpan);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, simpanToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.favList);

        dataSource = new DBDataSource(this);

        dataSource.open();


        values = dataSource.getFavData();

        listWisataAdapter = new ListWisataAdapter(SimpanActivity.this, values);


        ListView listView = (ListView) findViewById(R.id.simpanList);
        listView.setAdapter(listWisataAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case  R.id.index :
                Intent intent2 = new Intent(SimpanActivity.this, HomeActivity.class);
                startActivity(intent2);
                break;

            case R.id.profile :
                Intent intent3 = new Intent(SimpanActivity.this, ProfileActivity.class);
                startActivity(intent3);
                break;

            case  R.id.favList :
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.exit :
                this.finishAffinity();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            return;
        }
    }
}