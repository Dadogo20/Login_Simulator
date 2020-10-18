package com.example.loginsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    private EditText enter_first_name, enter_last_name, enter_birthdate, enter_email, enter_password;
    private Button confirm_registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.registration);
        enter_first_name = findViewById(R.id.e_first_name);
        enter_last_name = findViewById(R.id.e_last_name);
        enter_birthdate = findViewById(R.id.e_birthdate);
        enter_email = findViewById(R.id.e_email);
        enter_password = findViewById(R.id.e_password);
        confirm_registration = findViewById(R.id.confirm_registration);
        confirm_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To check if the formatting of the first name is incorrect
                if (enter_first_name.getText().toString().isEmpty()){
                    enter_first_name.setError("First name field should not be empty!");
                }
                else if (!Pattern.compile("^[A-Z][a-z]{2,29}$").matcher(enter_first_name.getText().toString()).matches()){
                    enter_first_name.setError("First name requires 3 or more characters and must start with a capital letter follow by a series of lower case letters! MAX CHARACTERS: 30");
                }
                //To check if the formatting of the last name is incorrect
                if (enter_last_name.getText().toString().isEmpty()){
                    enter_last_name.setError("Last name field should not be empty!");
                }
                else if (!Pattern.compile("^[A-Z][a-z]{2,29}$").matcher(enter_last_name.getText().toString()).matches()){
                    enter_last_name.setError("Last name requires 3 or more characters and must start with a capital letter follow by a series of lower case letters! MAX CHARACTERS: 30");
                }
                //To check if the formatting of the birthdate is incorrect
                if (enter_birthdate.getText().toString().isEmpty()){
                    enter_birthdate.setError("Date field should not be empty!");
                }
                else if (!Pattern.compile("^([0]\\d||[1][0-2])\\/([0-2]\\d||[3][0-1])\\/\\d{4}").matcher(enter_birthdate.getText().toString()).matches()){
                    enter_birthdate.setError("Date field format: MM/DD/YYYY");
                }
                //To check if the formatting of the e-mail is incorrect
                if (enter_email.getText().toString().isEmpty()){
                    enter_email.setError("E-mail field should not be empty!");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(enter_email.getText().toString()).matches()){
                    enter_email.setError("This is not a valid e-mail address!");
                }
                //To check if formatting of the password is incorrect
                if (enter_password.getText().toString().isEmpty()){
                    enter_password.setError("Password field should not be empty!");
                }
                else if (!Pattern.compile("^.*(?=.{6,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).*$").matcher(enter_password.getText().toString()).matches()){
                    enter_password.setError("Password requires 6 or more characters, containing at least a letter, number, and symbol!");
                }
                //If all fields are correct, it will pass all values of each field to their respective location within the arraylist
                if(Pattern.compile("^[A-Z][a-z]{2,29}$").matcher(enter_first_name.getText().toString()).matches() && Pattern.compile("^[A-Z][a-z]{2,29}$").matcher(enter_last_name.getText().toString()).matches() && Pattern.compile("^([0]\\d||[1][0-2])\\/([0-2]\\d||[3][0-1])\\/\\d{4}").matcher(enter_birthdate.getText().toString()).matches() && Patterns.EMAIL_ADDRESS.matcher(enter_email.getText().toString()).matches() && Pattern.compile("^.*(?=.{6,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).*$").matcher(enter_password.getText().toString()).matches()){
                    LogIn.users.add(new ArrayList(2));
                    LogIn.users.get(LogIn.position).add(new ArrayList(Arrays.asList(enter_email.getText().toString(), enter_password.getText().toString())));
                    LogIn.users.get(LogIn.position).add(new ArrayList(Arrays.asList(enter_first_name.getText().toString(), enter_last_name.getText().toString(), enter_birthdate.getText().toString())));
                    LogIn.position += 1;
                    Intent to_login = new Intent(Registration.this, LogIn.class);
                    startActivity(to_login);
                }
            }
        });
    }
}