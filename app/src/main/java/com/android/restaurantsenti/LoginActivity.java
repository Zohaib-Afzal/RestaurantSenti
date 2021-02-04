package com.android.restaurantsenti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.restaurantsenti.database.DatabaseHandler;
import com.android.restaurantsenti.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText email, password;
    private Button loginButton;
    private TextView signupTextView;
    DatabaseHandler databaseHandler = new DatabaseHandler();
    private ArrayList<User> usersList = new ArrayList<>();
    public static final String USER_EXISTENCE_SHARED_PREF_NAME = "checkUserExistence";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Validation validate = new Validation();
    User currentUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getDataFromDatabase();
        initializeFields();

        signupTextView.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            getUserData();
            if(validate()){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
               // finish();
            }
        });
    }

    private boolean validate() {
        if (TextUtils.isEmpty(email.getText())){
            email.setError("Email is required.");
            return false;
        }
        if (TextUtils.isEmpty(password.getText())){
            password.setError("Password is required.");
            return false;
        }
        if(validate.loginCheckUser(usersList, currentUser)){
            return true;
        } else {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void initializeFields() {
        email = (TextInputEditText)findViewById(R.id.email);
        password = (TextInputEditText)findViewById(R.id.passwordLogin);
        loginButton = (Button)findViewById(R.id.buttonLogin);
        signupTextView = (TextView)findViewById(R.id.signUpText);
        sharedPreferences = getSharedPreferences(USER_EXISTENCE_SHARED_PREF_NAME, MODE_PRIVATE);
    }

    private void getDataFromDatabase(){
        usersList = databaseHandler.getAllData();
    }

    private void getUserData(){
        currentUser.setEmail(email.getText().toString());;
       currentUser.setPassword(password.getText().toString());
    }
}