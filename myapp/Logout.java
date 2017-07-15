package com.example.nightmare.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Logout extends AppCompatActivity implements View.OnClickListener{
    EditText name;


    Button logout,need;
    String c1,c2,c3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        name = (EditText) findViewById(R.id.t1);
        logout = (Button) findViewById(R.id.b3);
        need = (Button) findViewById(R.id.bneed1);

        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String dis = name.getText().toString();
               // roug.setText(dis);
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonobject = new JSONObject(response);

                                    JSONArray array  = jsonobject.getJSONArray("response");
                                    String[] num = new String[array.length()];

                            if(array.length() != 0) {
                                for (int i = 0; i < array.length(); i++) {
                                    num[i] = array.getString(i);
                                }

                                ListAdapter infoAdapter = new ArrayAdapter<String>(Logout.this, android.R.layout.simple_list_item_1, num);
                                ListView infoList = (ListView) findViewById(R.id.lv1);
                                infoList.setAdapter(infoAdapter);

                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Logout.this);
                                builder.setMessage("Sorry no Provider is available at the moment")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                };
                LogoutRequest logoutRequest = new LogoutRequest(dis, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Logout.this);
                queue.add(logoutRequest);
            }

        });

        /*
        Intent intent = getIntent();
        String usrname = intent.getStringExtra("username");
        name.setText(usrname);
        */
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b3:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }

}
