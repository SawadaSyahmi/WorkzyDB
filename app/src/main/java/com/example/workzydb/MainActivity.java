package com.example.workzydb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new FragmentOne());

        findViewById(R.id.btn_one).setOnClickListener(v -> loadFragment(new FragmentOne()));
        findViewById(R.id.btn_two).setOnClickListener(v -> loadFragment(new FragmentTwo()));
        findViewById(R.id.btn_three).setOnClickListener(v -> loadFragment(new FragmentThree()));

        Button btnForum = findViewById(R.id.btn_forum);
        btnForum.setOnClickListener(v -> startActivity(new Intent(this, ListActivity.class)));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }
}