package com.example.a10117279_wisatabandung;

//Tanggal : 10 - Agustus - 2020
//NIM : 10117279
//Nama : Muhammad Ibnu Sina
//Kelas : IF-8

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.codesgood.views.JustifiedTextView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.mapbox.mapboxsdk.maps.MapboxMap;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private DBDataSource dataSource;

    private long id;
    private String nama, deskripsi, latitude, longitude, image;

    private TextView tv_nama, tv_deskripsi;
    private ImageView iv_image;

    private int img;

    private Button locationButton;

    private Model model;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_nama = (TextView) findViewById(R.id.detail_title);
        tv_deskripsi = (TextView) findViewById(R.id.detail_deskripsi);
        iv_image = (ImageView) findViewById(R.id.detail_image);

        dataSource = new DBDataSource(this);
        dataSource.open();

        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        nama = bun.getString("nama");
        deskripsi = bun.getString("deskripsi");
        latitude = bun.getString("latitude");
        longitude = bun.getString("longitude");
        image = bun.getString("image");
        img = bun.getInt("img");

        tv_deskripsi.setText(deskripsi);
        tv_nama.setText("Deskripsi");
        tv_deskripsi.setText(deskripsi);
        iv_image.setImageResource(img);

        locationButton = (Button) findViewById(R.id.lihatMap);
        locationButton.setOnClickListener(this);

        Toolbar menuToolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle(nama);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(DetailActivity.this, MapboxActivity.class);
        i.putExtra("id", id);
        i.putExtra("nama", nama);
        i.putExtra("deskripsi", deskripsi);
        i.putExtra("latitude", latitude);
        i.putExtra("longitude", longitude);
        i.putExtra("image", image);
        i.putExtra("img", img);
        startActivity(i);
    }
}