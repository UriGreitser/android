package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private AppDB db;
    private UserDao UserDao;
    private boolean flag = false;


    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})").matcher(password);
        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = AppDB.getDatabase(getApplicationContext());
        UserDao = db.UserDao();
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v-> {
            EditText etName = findViewById(R.id.etName);
            TextView PasswordsDontMatch = findViewById(R.id.PasswordsMatch);
            TextView InvalidPassword = findViewById(R.id.InvalidPassword);
            TextView UsernameTaken = findViewById(R.id.UsernameTaken);

            UsernameTaken.setVisibility(TextView.INVISIBLE);
            PasswordsDontMatch.setVisibility(TextView.INVISIBLE);
            InvalidPassword.setVisibility(TextView.INVISIBLE);

            EditText etUserName = findViewById(R.id.etUserName);
            EditText etPassword = findViewById(R.id.etPassword);
            EditText etConfirmPassword = findViewById(R.id.etConfirmPassword);
            String name = etName.getText().toString();
            String userName = etUserName.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();
            User u = UserDao.getName(name);
            if (u != null) {
                flag = true;
                UsernameTaken.setVisibility(TextView.VISIBLE);
            }
            if (!password.equals(confirmPassword)) {
                flag = true;
                PasswordsDontMatch.setVisibility(TextView.VISIBLE);
            }

            if (!isValidPassword(etPassword.getText().toString())) {
                flag = true;
                InvalidPassword.setVisibility(TextView.VISIBLE);
                //testvzdfv
            }
            if (!flag) {
                User user = new User();
                user.setName(name);
                user.setNickname(userName);
                user.setPassword(password);
                user.setServer("server");
                user.setPicture("picture");
                UserDao.insert(user);
                flag = false;
                TextView good = findViewById(R.id.good);
                good.setVisibility(View.VISIBLE);
                startActivity(new Intent(Register.this, MainActivity.class));

            }


        });


    }
}