package com.example.nightmare.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText e_mail, pass;
    TextView reg,admin,req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        e_mail = (EditText) findViewById(R.id.t2);
        pass = (EditText) findViewById(R.id.t4);

        admin = (TextView) findViewById(R.id.admin1);
        req = (TextView) findViewById(R.id.pr1);
        login = (Button) findViewById(R.id.b2);
        reg = (TextView) findViewById(R.id.l6);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( pass.length() == 0 || e_mail.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Login Failed\n\nto login please enter above mentioned field")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
                else {
                    final String email = e_mail.getText().toString();
                    final String password = pass.getText().toString();


                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {

                                    Intent intent = new Intent(MainActivity.this, Logout.class);
                                    MainActivity.this.startActivity(intent);
                                }
                                else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("Login Failed\n\nWrong e-mail or password")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(loginRequest);
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, Register.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reqintent = new Intent(MainActivity.this, prov_req.class);
                MainActivity.this.startActivity(reqintent);

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adintent = new Intent(MainActivity.this, check_req.class);
                MainActivity.this.startActivity(adintent);

            }
        });

    }

}
