package com.project.tiketkereta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.tiketkereta.R;
import com.project.tiketkereta.koneksi.ApiClient;
import com.project.tiketkereta.koneksi.ApiService;
import com.project.tiketkereta.koneksi.Response.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password;
    Button Register, Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Register = findViewById(R.id.register);
        Login = findViewById(R.id.btnLogin);

        Login.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

        Register.setOnClickListener(v -> {
            String inputUsername = username.getText().toString().trim();
            String inputEmail = email.getText().toString().trim();
            String inputPasword = password.getText().toString().trim();

            RegisterUser(inputUsername, inputEmail, inputPasword);
        });
    }

    private void RegisterUser(String inputUsername, String inputEmail, String inputPasword) {
        ApiService apiService = ApiClient.getApiService();
        Call<RegisterResponse> call = apiService.registerUser(inputUsername, inputEmail, inputPasword);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if (registerResponse.getStatus().equals("success")){
                    Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Register gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e("RegisterActivity", " "+t);
            }
        });
    }
}