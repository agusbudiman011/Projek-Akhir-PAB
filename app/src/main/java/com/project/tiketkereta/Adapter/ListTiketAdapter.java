package com.project.tiketkereta.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.tiketkereta.R;
import com.project.tiketkereta.koneksi.Model.DataTiket;

import java.util.List;

public class ListTiketAdapter extends RecyclerView.Adapter<ListTiketAdapter.Viewholder> {

    public List<DataTiket> dataTiketList;
    public OnItemClickData onItemClickData;
    public Context context;

    public ListTiketAdapter(Context context, List<DataTiket> list){
        this.context = context;
        this.dataTiketList = list;
    }

    public void setOnItemClickData(OnItemClickData onItemClickData) {
        this.onItemClickData = onItemClickData;
    }

    @NonNull
    @Override
    public ListTiketAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kereta_list, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTiketAdapter.Viewholder holder, int position) {
        DataTiket dataTiket = dataTiketList.get(position);
        holder.harga.setText(dataTiket.getHarga());
        holder.armada.setText(dataTiket.getArmada());
        holder.keterangan.setText(dataTiket.getKeterangan());
        holder.pemberangkatan.setText(dataTiket.getPemberangkatan());
        holder.jurusan.setText(dataTiket.getJurusan());
        holder.kelas.setText(dataTiket.getKelas());

        holder.itemView.setOnClickListener(v -> {
            onItemClickData.OnItemClicked(dataTiketList.get(holder.getAdapterPosition()));
        });

        if (dataTiket.getKelas().equals("Ekonomi")){
            holder.icon.setBackground(new ColorDrawable(Color.GRAY));
        } else if (dataTiket.getKelas().equals("Bisnis")) {
            holder.icon.setBackground(new ColorDrawable(Color.BLUE));
        }
        else if (dataTiket.getKelas().equals("Eksekutif")) {
            holder.icon.setBackground(new ColorDrawable(Color.YELLOW));
        }
    }

    @Override
    public int getItemCount() {
        return dataTiketList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView armada, jurusan, harga, pemberangkatan, kelas, keterangan;
        ImageView icon;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            armada = itemView.findViewById(R.id.textViewArmada);
            jurusan = itemView.findViewById(R.id.textViewJurusan);
            pemberangkatan = itemView.findViewById(R.id.textViewPemberangkatan);
            kelas = itemView.findViewById(R.id.textViewKelas);
            keterangan = itemView.findViewById(R.id.textViewKeterangan);
            harga = itemView.findViewById(R.id.textViewHarga);
            icon = itemView.findViewById(R.id.icon);
        }
    }

    public interface OnItemClickData {
        void OnItemClicked(DataTiket datatiket);
    }
}
