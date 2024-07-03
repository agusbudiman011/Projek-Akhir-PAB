package com.project.tiketkereta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.tiketkereta.R;
import com.project.tiketkereta.koneksi.ApiClient;
import com.project.tiketkereta.koneksi.ApiService;
import com.project.tiketkereta.koneksi.Model.DataTiket;
import com.project.tiketkereta.koneksi.Response.PesanTiketResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesanTiketActivity extends AppCompatActivity {

    TextView armada, jurusan, harga, pemberangkatan, kelas, keterangan;
    EditText nama, jumlah, hp;
    Button pesan;
    DataTiket dataTiket;
    String idkereta = "";

    int id_user = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_tiket);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_ID", MODE_PRIVATE);
        id_user = sharedPreferences.getInt("user_id", 0);

        armada = findViewById(R.id.textViewDetailArmada);
        jurusan = findViewById(R.id.textViewDetailJurusan);
        harga = findViewById(R.id.textViewDetailHarga);
        pemberangkatan = findViewById(R.id.textViewDetailPemberangkatan);
        kelas = findViewById(R.id.textViewDetailKelas);
        keterangan = findViewById(R.id.textViewDetailKeterangan);
        nama = findViewById(R.id.editTextNama);
        jumlah = findViewById(R.id.editTextjumlah);
        hp = findViewById(R.id.editTextNoHp);
        pesan = findViewById(R.id.buttonPesan);

        if (getIntent().hasExtra("datatiket")){
            dataTiket = getIntent().getParcelableExtra("datatiket");
            if (dataTiket != null){
                armada.setText("Armada: "+dataTiket.getArmada() + " "+ dataTiket.getIdTiket());
                jurusan.setText("Jurusan: "+dataTiket.getJurusan());
                harga.setText("Harga: "+dataTiket.getJurusan());
                pemberangkatan.setText("Pemberangkatan: "+dataTiket.getPemberangkatan());
                kelas.setText("Kelas: "+dataTiket.getKelas());
                keterangan.setText("Keterangan: "+dataTiket.getKeterangan());
                idkereta = dataTiket.getIdTiket();

            }
        }

        pesan.setOnClickListener(v -> {
            PesanTiket();
        });


    }

    private void PesanTiket() {
        String inputNama = nama.getText().toString().trim();
        String inputjumlah = jumlah.getText().toString().trim();
        String inputhp = hp.getText().toString().trim();

        ApiService apiService = ApiClient.getApiService();
        Call<PesanTiketResponse> call = apiService.pesanTiket(dataTiket.getIdTiket(), inputNama, Integer.parseInt(inputjumlah), inputhp, id_user);
        call.enqueue(new Callback<PesanTiketResponse>() {
            @Override
            public void onResponse(Call<PesanTiketResponse> call, Response<PesanTiketResponse> response) {
                PesanTiketResponse pesanTiketResponse = response.body();
                if (pesanTiketResponse.getStatus().equals("success")){
                    Toast.makeText(PesanTiketActivity.this, "Pesan Tiket Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PesanTiketActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(PesanTiketActivity.this, "Pesan Tiket gagal "+pesanTiketResponse.getStatus(), Toast.LENGTH_SHORT).show();
                    Log.e("PesanTiketActivity", " "+pesanTiketResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<PesanTiketResponse> call, Throwable t) {
                Toast.makeText(PesanTiketActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e("PesaTiketnActivity", " "+t);
            }
        });
    }
}