package com.project.tiketkereta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.project.tiketkereta.Adapter.ListTiketAdapter;
import com.project.tiketkereta.koneksi.ApiClient;
import com.project.tiketkereta.koneksi.ApiService;
import com.project.tiketkereta.koneksi.Model.DataTiket;
import com.project.tiketkereta.koneksi.Response.GetTiketResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListKeretaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListTiketAdapter listTiketAdapter;
    List<DataTiket> dataTiketList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kereta);

        recyclerView = findViewById(R.id.recyclerViewKereta);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        GetTiket();

    }

    private void GetTiket() {
        ApiService apiService = ApiClient.getApiService();
        Call<GetTiketResponse> call = apiService.getDataTiket();
        call.enqueue(new Callback<GetTiketResponse>() {
            @Override
            public void onResponse(Call<GetTiketResponse> call, Response<GetTiketResponse> response) {
                Toast.makeText(ListKeretaActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                GetTiketResponse getTiketResponse = response.body();
                if (getTiketResponse.getStatus().equals("success")){
                    dataTiketList = getTiketResponse.getData();
                    listTiketAdapter = new ListTiketAdapter(ListKeretaActivity.this, dataTiketList);
                    recyclerView.setAdapter(listTiketAdapter);

                    listTiketAdapter.setOnItemClickData(datatiket -> {
                        Intent intent = new Intent(ListKeretaActivity.this, PesanTiketActivity.class);
                        intent.putExtra("datatiket", datatiket);
                        startActivity(intent);
                    });
                }
                else {
                    Toast.makeText(ListKeretaActivity.this, "Data Gagal Diambil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetTiketResponse> call, Throwable t) {
                Toast.makeText(ListKeretaActivity.this, "Error: "+t, Toast.LENGTH_SHORT).show();
                Log.e("Error", " "+t);
            }
        });
    }
}