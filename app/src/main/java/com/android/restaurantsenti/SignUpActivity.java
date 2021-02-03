package com.android.restaurantsenti;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.restaurantsenti.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    private TextView alreadyMember;
    private TextInputEditText fullName, email, userName, password, confirmPassword;
    private Button signUpButton;
    private User currentUser = new User();
    private Validation validate = new Validation();
    String confirmPasswordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initializeFields();

        alreadyMember.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        signUpButton.setOnClickListener(v -> {

            getUserData();
            if (validateUser()){
                signUpUser();
            }
        });
    }

    private void signUpUser() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void getUserData(){
            currentUser.setEmail(email.getText().toString());
            currentUser.setPassword(password.getText().toString());
            currentUser.setFullName(fullName.getText().toString());
            currentUser.setUserName(userName.getText().toString());
            confirmPasswordValue = confirmPassword.getText().toString();
    }
    private void initializeFields() {
        alreadyMember = (TextView)findViewById(R.id.loginText);
        signUpButton = (Button)findViewById(R.id.buttonSignUp);
        fullName = (TextInputEditText)findViewById(R.id.fullname);
        email = (TextInputEditText)findViewById(R.id.email);
        userName = (TextInputEditText)findViewById(R.id.username);
        password = (TextInputEditText)findViewById(R.id.password);
        confirmPassword = (TextInputEditText)findViewById(R.id.confirm_password);
    }


    private boolean validateUser() {
        if(validate.signUpCheckEmpty(currentUser)){
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!validate.validateEmail(currentUser.getEmail())){
          //  Toast.makeText(this, "Email invalid", Toast.LENGTH_SHORT).show();
            email.setError("Email invalid.");
            return false;
        }
        if (TextUtils.isEmpty(fullName.getText())){
            fullName.setError("Your Full Name is required.");
            return false;
        }
        if (TextUtils.isEmpty(userName.getText())){
            userName.setError("Username is required");
            return false;
        }
        if (TextUtils.isEmpty(password.getText())){
            password.setError("Password is required");
            return false;
        }
        if (TextUtils.isEmpty(confirmPassword.getText())){
            confirmPassword.setError("Password is required");
            return false;
        }
        if (fullName.length() > 30){
            fullName.setError("Full name exceed the standard length.");
            return false;
        }
        if (userName.length() > 15){
            fullName.setError("Username exceed the standard length.");
            return false;
        }
        if (password.length() > 30){
            password.setError("Password exceed the standard length.");
            return false;
        }
        if (password.length() < 8){
            password.setError("Password is too small for the standard length.");
            return false;
        }

//        if (usersList.size() > 0) {
//            if (!validate.signUpCheckUser(usersList, currentUser)) {
//                if (currentUser.getPassword().compareTo(confirmPasswordValue) == 0) {
//                    Toast.makeText(SignUpActivity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
//                    return true;
//                } else {
//                    Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(SignUpActivity.this, "User already registered with this email", Toast.LENGTH_SHORT).show();
//            }
//
//        } else {
            if (currentUser.getPassword().compareTo(confirmPasswordValue) == 0) {
                Toast.makeText(SignUpActivity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                return false;
            }
       // }

    }
}