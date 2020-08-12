package com.example.a10117279_wisatabandung;

//Tanggal : 8 - Agustus - 2020
//NIM : 10117279
//Nama : Muhammad Ibnu Sina
//Kelas : IF-8

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class Page2 extends Fragment {

    View view;

    public Page2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_page2, container, false);
        return view;
    }
}
