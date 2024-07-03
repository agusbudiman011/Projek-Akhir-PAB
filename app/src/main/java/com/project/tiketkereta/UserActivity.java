package com.project.tiketkereta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.tiketkereta.koneksi.ApiClient;
import com.project.tiketkereta.koneksi.ApiService;
import com.project.tiketkereta.koneksi.Model.DataUser;
import com.project.tiketkereta.koneksi.Response.GetUserResponse;
import com.project.tiketkereta.koneksi.Response.UpdateUserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    TextView username, email, password;
    String dataUsername, dataEmail, dataPassword;
    Button updateUser;
    int id_user = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        username = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        updateUser = findViewById(R.id.buttonUpdate);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_ID", MODE_PRIVATE);
        id_user = sharedPreferences.getInt("user_id", 0);

        GetUser(id_user);

        updateUser.setOnClickListener(v -> {
            showAddUserDialog();
        });

    }

    private void GetUser(int id_user) {
        ApiService apiService = ApiClient.getApiService();
        Call<GetUserResponse> call = apiService.getUser(id_user);
        call.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                GetUserResponse getUserResponse = response.body();
                if (getUserResponse.getStatus().equals("success")){
                    DataUser dataUser = getUserResponse.getData();
                    if (dataUser != null){
                        dataUsername = dataUser.getUsername();
                        dataEmail = dataUser.getEmail();
                        dataPassword = dataUser.getPassword();

                        username.setText(dataUsername);
                        email.setText(dataEmail);
                        password.setText(dataPassword);
                    }
                }
                else {
                    Toast.makeText(UserActivity.this, "Data Gagal Diambil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                Toast.makeText(UserActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e("UserActivity", " "+t);
            }
        });
    }

    private void showAddUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update User");

        View view = getLayoutInflater().inflate(R.layout.update_user, null);
        final EditText editTextId = view.findViewById(R.id.editTextId);
        final EditText editTextUsername = view.findViewById(R.id.editTextUsername);
        final EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        final EditText editTextPassword = view.findViewById(R.id.editTextPassword);

        editTextUsername.setText(dataUsername);
        editTextEmail.setText(dataEmail);
        editTextPassword.setText(dataPassword);
        editTextId.setText(String.valueOf(id_user));

        builder.setView(view);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String id = editTextId.getText().toString().trim();
                String name = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validasi input
                if (id.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(UserActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                ChangeUserData(Integer.parseInt(id), name, password, email);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void ChangeUserData(int id, String name, String password, String email) {
        ApiService apiService = ApiClient.getApiService();
        Call<UpdateUserResponse> call = apiService.updateUser(id, name, password, email);
        call.enqueue(new Callback<UpdateUserResponse>() {
            @Override
            public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                UpdateUserResponse updateUserResponse = response.body();
                if (updateUserResponse.getStatus().equals("success")){
                    GetUser(id_user);
                    Toast.makeText(UserActivity.this, "Berhasil Update", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UserActivity.this, "Gagal Update", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                Toast.makeText(UserActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e("UserActivity", " "+t);
            }
        });
    }
}