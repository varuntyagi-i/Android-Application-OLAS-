package com.example.nightmare.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class admin extends AppCompatActivity {

    Button done,view_req;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        done = (Button) findViewById(R.id.bdone1);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dointent = new Intent(admin.this,MainActivity.class);
                admin.this.startActivity(dointent);
            }
        });
    }

    public void onMe(View view){
        id = (EditText) findViewById(R.id.tpass1);
        view_req = (Button) findViewById(R.id.bvr1);
        final String str;
        str = id.getText().toString();

        if(str.equals("1")){
            view_req.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(admin.this, check_req.class);
                    admin.this.startActivity(intent);
                }
            }));
        }

    }
}
