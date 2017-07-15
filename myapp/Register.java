package com.example.nightmare.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Register extends AppCompatActivity{
    Button register;
    EditText name,e_mail,mob,pass,re_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.t1);
        e_mail = (EditText) findViewById(R.id.t2);
        mob = (EditText) findViewById(R.id.t3);
        pass = (EditText) findViewById(R.id.t4);
        re_pass = (EditText) findViewById(R.id.t5);
        register = (Button) findViewById(R.id.b1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = name.getText().toString();
                final String email = e_mail.getText().toString();
                final String number = mob.getText().toString();
                final String password = pass.getText().toString();
                final String re_password = re_pass.getText().toString();

                int count = 0;

                for(int i = 0 ; i < email.length() ; i++){

                    if(email.charAt(i) == '@')
                        count++;
                }

                if(name.length() == 0 ||e_mail.length() == 0 ||mob.length() == 0 ||pass.length() == 0 ||re_pass.length() == 0  ){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setMessage("Registration Failed\n\nfill all the above mentioned field to register")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }

                else{
                    if(mob.length() != 10){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage("Registration Failed\n\nmobile number must of 10 digit")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                    else if(count != 1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage("Registration Failed\n\nenter valid e-mail id")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();

                    }

                    else {
                        if(password.equals(re_password)) {
                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        boolean success = jsonResponse.getBoolean("success");

                                        if (success) {
                                            Intent intent = new Intent(Register.this, MainActivity.class);
                                            Register.this.startActivity(intent);
                                        } else {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                            builder.setMessage("Register Failed")
                                                    .setNegativeButton("Retry", null)
                                                    .create()
                                                    .show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            RegisterRequest registerRequest = new RegisterRequest(username, email, number, password, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(Register.this);
                            queue.add(registerRequest);
                        }
                        else{

                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("Registration Failed\n\npassword and re-password must match")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                        }
                    }
                }
            }

        });
        }
}