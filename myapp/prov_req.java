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

public class prov_req extends AppCompatActivity {
    Button submit,addl;
    EditText com_name,owner_name,mob_no;
    String street,district,state;
   // String company,owner,number;
    TextView setLoc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_prov_req);


        com_name = (EditText) findViewById(R.id.comptext);
        owner_name = (EditText) findViewById(R.id.owntext);
        mob_no = (EditText) findViewById(R.id.mobtext);
        addl = (Button) findViewById(R.id.loc1);
        submit = (Button) findViewById(R.id.breq1);



        Intent intent = getIntent();
        street = intent.getStringExtra("street");
        district = intent.getStringExtra("district");
        state = intent.getStringExtra("state");
     /*   company = intent.getStringExtra("company");
        owner = intent.getStringExtra("owner");
        number = intent.getStringExtra("number");
*/
        setLoc = (TextView) findViewById(R.id.hide);

        if(street == null || district == null || state == null){
        setLoc.setText("LOCATION NOT SET");
  /*          com_name.setText(company);
            owner_name.setText(owner);
            mob_no.setText(number);
*/
        }
        else {
            String mess = "Your service location is:" + "\n" + street + "," + district + "," + state +
                    "\n\nTo add this location as your service location click 'SUBMIT REQ.'";
            setLoc.setText(mess);
    /*      com_name.setText(company);
            owner_name.setText(owner);
            mob_no.setText(number);

*/
        }

        addl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addintent = new Intent(prov_req.this, MapsActivity.class);
          /*      addintent.putExtra("company",company);
                addintent.putExtra("owner",owner);
                addintent.putExtra("number",number);
*/
                prov_req.this.startActivity(addintent);


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(com_name.length() == 0 || owner_name.length() == 0 || mob_no.length() == 0 ){
                        AlertDialog.Builder builder = new AlertDialog.Builder(prov_req.this);
                        builder.setMessage("Request Failed\n\nall the above mentioned field are necessary to register")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                   else if(street == null || district == null || state == null){
                        AlertDialog.Builder builder = new AlertDialog.Builder(prov_req.this);
                        builder.setMessage("Request Failed\n\nAdd you service location to submit your request")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                    else {
                        if (mob_no.length() == 10) {

                            final String com_nam = com_name.getText().toString();
                            final String owner_nam = owner_name.getText().toString();
                            final String mob_n = mob_no.getText().toString();
                            final String streetn = street;
                            final String districtn = district;
                            final String staten = state;


                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject JsonResponse = new JSONObject(response);
                                        boolean success = JsonResponse.getBoolean("success");

                                        if (success) {
                                            Intent subintent = new Intent(prov_req.this, MainActivity.class);
                                            prov_req.this.startActivity(subintent);
                                        } else {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(prov_req.this);
                                            builder.setMessage("Request Failed")
                                                    .setNegativeButton("Retry", null)
                                                    .create()
                                                    .show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            ProviderRequest providerreq = new ProviderRequest(com_nam, owner_nam, mob_n, streetn, districtn, staten,
                                                                                responseListener);
                            RequestQueue queue = Volley.newRequestQueue(prov_req.this);
                            queue.add(providerreq);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(prov_req.this);
                            builder.setMessage("Request Failed\n\nplease enter a 10-digit mobile number")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    }

                }
            });
    }
  /*  public void saveMe(View view){
        company = com_name.getText().toString();
        owner = owner_name.getText().toString();
        number = mob_no.getText().toString();

    }
*/

}
