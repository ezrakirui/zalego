package com.example.zalego;


import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
EditText fname,lname,unamee,pass;
String firstName, lastName, userName, password;
Button Register;
String myUrl = "http://192.168.43.241/zalego/index.php";

String URL_FETCH = "https://192.168.43.241/zalego.retrieve.php";
String content[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);setTitle("Registration");
		
		fname= (EditText)findViewById(R.id.fname);
		lname = (EditText)findViewById(R.id.lnameView);
		unamee=(EditText)findViewById(R.id.unamee);
		pass = (EditText)findViewById(R.id.pass);
		Register=(Button)findViewById(R.id.register);
		
		
		insert();
	}
	public void insert(){
		Register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if ((fname.getText().toString() != null) && lname.getText().toString()!=null&& unamee.getText().toString()!=null && pass.getText().toString()!=null){
					firstName =fname.getText().toString();
					lastName=lname.getText().toString();
					userName= unamee.getText().toString();
					password=pass.getText().toString();
					
					insertData();
				}
				else{
					Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
	public boolean insertData(){

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, myUrl, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener(){

				@Override
				public void onErrorResponse(VolleyError error) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this, "My error"+error, Toast.LENGTH_SHORT).show();
					Log.i("My error",""+error);
				}   
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String>
                params = new HashMap<String, String>();
                params.put("firstName", firstName);
                params.put("lastName", lastName);
                params.put("userName", userName);
                params.put("password", password);
                
                return params;

            }
        };

       requestQueue.add(stringRequest);
		return true;
 

}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 public void showMessage(String title, String message){
	     	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	     	builder.setCancelable(true);
	     	builder.setTitle(title);
	     	builder.setMessage(message);
	     	builder.show();
	     }

}
