package com.android.restaurantsenti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText username, password;
    private Button loginButton;
    private TextView signupTextView;

    public static final String USER_EXISTENCE_SHARED_PREF_NAME = "checkUserExistence";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeFields();

        signupTextView.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            if(validate()){
                storeDataToSharedPref();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void storeDataToSharedPref() {
        editor.putString("username", username.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.apply();
        editor.commit();
    }

    private boolean validate() {
        if (TextUtils.isEmpty(username.getText())){
            username.setError("Username is required.");
            return false;
        }
        if (TextUtils.isEmpty(password.getText())){
            password.setError("Password is required.");
            return false;
        }
        return true;
    }

    private void initializeFields() {
        username = (TextInputEditText)findViewById(R.id.username);
        password = (TextInputEditText)findViewById(R.id.passwordLogin);
        loginButton = (Button)findViewById(R.id.buttonLogin);
        signupTextView = (TextView)findViewById(R.id.signUpText);

        sharedPreferences = getSharedPreferences(USER_EXISTENCE_SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}