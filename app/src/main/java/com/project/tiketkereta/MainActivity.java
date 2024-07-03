package com.project.tiketkereta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.project.tiketkereta.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnPesan).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListKeretaActivity.class));
        });
        findViewById(R.id.btnHistori).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HistoriActivity.class));
        });
        findViewById(R.id.btnUser).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, UserActivity.class));
        });
        findViewById(R.id.btnAbout).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        });
        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

    }

}