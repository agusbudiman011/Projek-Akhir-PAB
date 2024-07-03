package com.project.tiketkereta.Adapter;

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
import com.project.tiketkereta.koneksi.Model.DataPemesanan;

import java.util.List;

public class HistoriTiketAdapter extends RecyclerView.Adapter<HistoriTiketAdapter.ViewHolder> {

    List<DataPemesanan> dataPemesananList;

    public HistoriTiketAdapter(List<DataPemesanan> list){
        this.dataPemesananList = list;
    }

    @NonNull
    @Override
    public HistoriTiketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_histori_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriTiketAdapter.ViewHolder holder, int position) {
        DataPemesanan dataPemesanan = dataPemesananList.get(position);
        holder.total.setText("Total: "+dataPemesanan.getHarga());
        holder.armada.setText(dataPemesanan.getArmada());
        holder.tanggal.setText("Tanggal Pemesanan: "+dataPemesanan.getTanggalPemesanan());
        holder.pemberangkatan.setText("Tanggal Pemberangkatan: "+dataPemesanan.getPemberangkatan());
        holder.jurusan.setText("Jurusan: "+dataPemesanan.getJurusan());
        holder.kelas.setText("Kelas: "+dataPemesanan.getKelas());
        holder.idPemesanan.setText("ID Pemesanan: "+dataPemesanan.getIdPemesanan());

        if (dataPemesanan.getKelas().equals("Ekonomi")){
            holder.icon.setBackground(new ColorDrawable(Color.GRAY));
        } else if (dataPemesanan.getKelas().equals("Bisnis")) {
            holder.icon.setBackground(new ColorDrawable(Color.BLUE));
        }
        else if (dataPemesanan.getKelas().equals("Eksekutif")) {
            holder.icon.setBackground(new ColorDrawable(Color.YELLOW));
        }
    }

    @Override
    public int getItemCount() {
        return dataPemesananList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView armada, jurusan, total, pemberangkatan, kelas, tanggal, idPemesanan;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            armada = itemView.findViewById(R.id.textViewArmada);
            idPemesanan = itemView.findViewById(R.id.textViewIdPemesanan);
            jurusan = itemView.findViewById(R.id.textViewJurusan);
            pemberangkatan = itemView.findViewById(R.id.textViewPemberangkatan);
            kelas = itemView.findViewById(R.id.textViewKelas);
            tanggal = itemView.findViewById(R.id.textViewtanggal);
            total = itemView.findViewById(R.id.textViewTotal);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
