package com.example.a10117279_wisatabandung;

//Tanggal : 10 - Agustus - 2020
//NIM : 10117279
//Nama : Muhammad Ibnu Sina
//Kelas : IF-8

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListWisataAdapter extends BaseAdapter {

    Context context;
    ArrayList<Model> model;
    private Model ubah;
    protected DBDataSource dataSource;

    public ListWisataAdapter(Context context, ArrayList<Model> model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int i) {
        return model.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.card_wisata, null);
            holder.tv_title = view.findViewById(R.id.card_title);
            holder.tv_text = view.findViewById(R.id.card_text);
            holder.imageView = view.findViewById(R.id.card_image);
            holder.button = view.findViewById(R.id.action_button);
            holder.imageButton = view.findViewById(R.id.fav_button);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Model m = model.get(i);
        holder.tv_title.setText(m.getNama());
        holder.tv_text.setText(m.getDeskripsi());
        String imgName = m.getImage();
        int resId = ((Activity) context).getResources()
                .getIdentifier(imgName,"drawable", ((Activity) context).getPackageName());
        holder.imageView.setImageResource(resId);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", m.getId());
                intent.putExtra("nama", m.getNama());
                intent.putExtra("deskripsi", m.getDeskripsi());
                intent.putExtra("latitude", m.getLatitude());
                intent.putExtra("longitude", m.getLongitude());
                intent.putExtra("image", m.getImage());
                intent.putExtra("img", resId);
                context.startActivity(intent);
            }
        });

        if (m.getStatus().equals("0")) {
            holder.imageButton.setBackgroundResource(R.drawable.fav_grey);
        } else {
            holder.imageButton.setBackgroundResource(R.drawable.fav_red);
        }

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSource = new DBDataSource(context);
                dataSource.open();
                ubah = new Model();
                ubah.setId(m.getId());
                ubah.setNama(m.getNama());
                ubah.setDeskripsi(m.getDeskripsi());
                ubah.setLatitude(m.getLatitude());
                ubah.setLongitude(m.getLongitude());
                ubah.setImage(m.getImage());

                if (m.getStatus().equals("0")) {
                    ubah.setStatus("1");
                    holder.imageButton.setBackgroundResource(R.drawable.fav_red);
                } else {
                    holder.imageButton.setBackgroundResource(R.drawable.fav_grey);
                    ubah.setStatus("0");
                }

                dataSource.updateFav(ubah);
                dataSource.close();
            }
        });
        return view;
    }

    class ViewHolder {
        ImageView imageView;
        TextView tv_title, tv_text;
        Button button;
        ImageButton imageButton;
    }
}
