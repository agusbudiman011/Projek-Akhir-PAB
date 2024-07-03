package com.project.tiketkereta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.project.tiketkereta.Adapter.HistoriTiketAdapter;
import com.project.tiketkereta.koneksi.ApiClient;
import com.project.tiketkereta.koneksi.ApiService;
import com.project.tiketkereta.koneksi.Model.DataPemesanan;
import com.project.tiketkereta.koneksi.Response.GetPemesananResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriActivity extends AppCompatActivity {
    
    RecyclerView recyclerView;
    HistoriTiketAdapter historiTiketAdapter;
    List<DataPemesanan> dataPemesananList;
    int id_user = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_ID", Context.MODE_PRIVATE);
        id_user = sharedPreferences.getInt("user_id", 0);
        
        recyclerView = findViewById(R.id.recyclerViewKereta);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        GetPesanan(id_user);
    }

    private void GetPesanan(int id_user) {
        ApiService apiService = ApiClient.getApiService();
        Call<GetPemesananResponse> call = apiService.getPesananTiket(String.valueOf(id_user));
        call.enqueue(new Callback<GetPemesananResponse>() {
            @Override
            public void onResponse(Call<GetPemesananResponse> call, Response<GetPemesananResponse> response) {
                GetPemesananResponse getPemesananResponse = response.body();
                if (getPemesananResponse.getStatus().equals("success")){
                    dataPemesananList = getPemesananResponse.getData();
                    historiTiketAdapter = new HistoriTiketAdapter(dataPemesananList);
                    recyclerView.setAdapter(historiTiketAdapter);
                }
                else {
                    Toast.makeText(HistoriActivity.this, "Tidak ada Data pemesanan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetPemesananResponse> call, Throwable t) {
                Toast.makeText(HistoriActivity.this, "Data Gagal Diambil", Toast.LENGTH_SHORT).show();
                Log.e("HistoriActivity", " "+t);
            }
        });
    }
}