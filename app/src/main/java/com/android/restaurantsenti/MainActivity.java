package com.android.restaurantsenti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import static com.android.restaurantsenti.LoginActivity.USER_EXISTENCE_SHARED_PREF_NAME;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFields();
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkUserExistence();
    }

    private void initializeFields() {
        sharedPreferences = getSharedPreferences(USER_EXISTENCE_SHARED_PREF_NAME, MODE_PRIVATE);
    }

    private void checkUserExistence() {
        if (!sharedPreferences.contains("username")){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}