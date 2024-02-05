package com.example.palette;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.TarjViewHolder> {

    private ArrayList<Tarjeta> items;
    private View.OnClickListener onClickListener;

    public CardsAdapter(ArrayList<Tarjeta> items, View.OnClickListener onClickListener) {
        this.items = items;
        this.onClickListener = onClickListener;
    }

    public static class TarjViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;

        public TarjViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.image1);
        }

    }

    @Override
    public TarjViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cards, viewGroup, false);
        itemView.setOnClickListener(onClickListener); // Establecer el OnClickListener aquí
        return new TarjViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TarjViewHolder viewHolder, int pos) {
        Tarjeta item = items.get(pos);
        viewHolder.imagen.setImageResource(item.getImagen());
        viewHolder.itemView.setTag(item); // Guardar el objeto Tarjeta en el tag del itemView
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
