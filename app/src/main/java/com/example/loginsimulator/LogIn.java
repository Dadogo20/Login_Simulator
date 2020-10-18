package com.example.loginsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class LogIn extends AppCompatActivity {
    private EditText input_email, input_password;
    private Button log_in, register;
    public static ArrayList<ArrayList<ArrayList>> users = new ArrayList<>();
    public static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.login);
        input_email = findViewById(R.id.email);
        input_password = findViewById(R.id.password);
        log_in = findViewById(R.id.login);
        register = findViewById(R.id.confirm_registration);
        log_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Upon being clicked, the app will attempt to find entry that matches the users input from the fields
                if (!users.isEmpty() && (input_email.getText().toString().isEmpty() || input_password.getText().toString().isEmpty())){
                    //To confirm if any field is empty
                    if (input_email.getText().toString().isEmpty()){
                        input_email.setError("E-mail field should not be empty!");
                    }
                    if (input_password.getText().toString().isEmpty()){
                        input_password.setError("Password field should not be empty!");
                    }
                }
                else if (!users.isEmpty() && !input_email.getText().toString().isEmpty() && !input_password.getText().toString().isEmpty()){
                    //If all fields aren't empty, it will loop through the whole list to check if the value of fields match any existing entry, if it does the loop immediately terminates and provides confirmation to the user
                    for (int x = 0; x < users.size(); x++){
                        if (users.get(x).get(0).contains(input_email.getText().toString()) && users.get(x).get(0).contains(input_password.getText().toString())){
                            input_email.setError(null);
                            input_password.setError(null);
                            Toast.makeText(LogIn.this, "Welcome " + users.get(x).get(1).get(0) + " " + users.get(x).get(1).get(1), Toast.LENGTH_SHORT).show();
                            break;
                        }
                        else if (!users.get(x).get(0).contains(input_email.getText().toString()) && users.get(x).get(0).contains(input_password.getText().toString())){
                            input_email.setError("E-mail is incorrect!");
                            input_password.setError(null);
                            break;
                        }
                        else if (users.get(x).get(0).contains(input_email.getText().toString()) && !users.get(x).get(0).contains(input_password.getText().toString())){
                            input_email.setError(null);
                            input_password.setError("Password is incorrect!");
                            break;
                        }
                        else {
                            input_email.setError("E-mail is incorrect!");
                            input_password.setError("Password is incorrect!");
                        }
                    }
                }
                else {
                    //To to inform the user that there are no entries to find
                    Toast.makeText(LogIn.this, "NO ENTRIES!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            //To send the user to the registration view upon being clicked
            @Override
            public void onClick(View view) {
                Intent to_registration = new Intent(LogIn.this, Registration.class);
                startActivity(to_registration);
            }
        });
    }
}