package com.android.restaurantsenti;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
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
    private TextView signUpTextView;
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

        signUpTextView.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            clearAllFields();
        });

        loginButton.setOnClickListener(v -> {
            //getDataFromDatabase();
            getUserData();
            if(validate()){

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                //to set value of user login boolean to true in Shared Preference so that we can check
                // on app resume whether the user has already logged in or not
                setUserLoginSharedPreference();
            }
        });
    }

    private void setUserLoginSharedPreference(){
        sharedPreferences = this.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("isUserLoggedIn",true).apply();
    }

    private void clearAllFields(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                email.setText("");
                password.setText("");
            }
        },2000);

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
        if(isNetworkAvailable()){
            if(validate.loginCheckUser(usersList, currentUser)){
                return true;
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Internet Connection not Available", Toast.LENGTH_SHORT).show();

        }

        return false;
    }

    private void initializeFields() {
        email = (TextInputEditText)findViewById(R.id.email);
        password = (TextInputEditText)findViewById(R.id.passwordLogin);
        loginButton = (Button)findViewById(R.id.buttonLogin);
        signUpTextView = (TextView)findViewById(R.id.signUpText);
    }

    private void getDataFromDatabase(){
        usersList = databaseHandler.getAllData();
    }

    private void getUserData(){
        currentUser.setEmail(email.getText().toString());;
       currentUser.setPassword(password.getText().toString());
    }
    //checking if user is connected to any internet source
    private boolean isNetworkAvailable() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfoArray = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo networkInfo : networkInfoArray) {
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (networkInfo.isConnected())
                    haveConnectedWifi = true;
            if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (networkInfo.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}