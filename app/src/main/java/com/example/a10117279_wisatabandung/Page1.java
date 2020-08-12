package com.example.a10117279_wisatabandung;

//Tanggal : 8 - Agustus - 2020
//NIM : 10117279
//Nama : Muhammad Ibnu Sina
//Kelas : IF-8

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class Page1 extends Fragment {

    private ListView listView;

    private DBDataSource dataSource;

    private ArrayList<Model> values;

    ListWisataAdapter listWisataAdapter;

    View view;

    public Page1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_page1, container, false);
        listView = view.findViewById(R.id.wisataList);

        dataSource = new DBDataSource(getContext());

        dataSource.open();


        values = dataSource.getAllData();

        listWisataAdapter = new ListWisataAdapter(getActivity(), values);


        ListView listView = (ListView) view.findViewById(R.id.wisataList);
        listView.setAdapter(listWisataAdapter);

        return view;
    }
}
