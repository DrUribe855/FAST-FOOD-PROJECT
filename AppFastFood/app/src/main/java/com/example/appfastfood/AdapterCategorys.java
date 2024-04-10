package com.example.appfastfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterCategorys extends RecyclerView.Adapter<AdapterCategorys.ViewHolder> {
    List<Categorys> listCategorys;

    public AdapterCategorys(List<Categorys> listCategorys){this.listCategorys = listCategorys;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categorys, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categorys temporal = this.listCategorys.get(position);
        holder.cargarDatos(temporal, position);
    }

    @Override
    public int getItemCount() {
        return listCategorys.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        Context contexto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.contexto = itemView.getContext();

        }
        public void cargarDatos(Categorys datos, int position){

        }
    }
}
