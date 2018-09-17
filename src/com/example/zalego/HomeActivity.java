package com.example.zalego;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.content.Intent;

public class HomeActivity extends Activity{
//DatabaseHelper db;
//Cursor result;
	String myUrl = "http://192.168.43.241/zalego/view.php";
	 String content[];
	TextView userNameView,firstNameView,lnameView,passwordView;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Welcome");
		//db=new DatabaseHelper (this);
	}
	//to fetch data
	
public void Viewdata(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest stringRequest = new JsonArrayRequest(myUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            		//String content[];
               //content added = new content();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);


                        firstNameView.setText((jsonObject.getString("firstName")));
                        lnameView.setText(jsonObject.getString("lastName"));
                        userNameView.setText(jsonObject.getString("user_name"));
                        passwordView.setText(jsonObject.getString("password"));


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(HomeActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }

        }
        );


        requestQueue.add(stringRequest);

    }
 
	/*
		result=db.getAllUsers();
		while (result.moveToNext())
			if(result.getCount()==0){
				showMessage("Error", "No Data Yet");
				}else{
				
				userNameView.setText(result.getString(4));
				firstNameView.setText(result.getString(2));
				lnameView.setText(result.getString(3));
				passwordView.setText(result.getString(5));
				
				String this_name=result.getString(2);
			Toast.makeText(HomeActivity.this,"Welcome"+this_name,Toast.LENGTH_SHORT).show();
			
			break;
			}*/
	

public void showMessage(String title, String message){
 	AlertDialog.Builder builder = new AlertDialog.Builder(this);
 	builder.setCancelable(true);
 	builder.setTitle(title);
 	builder.setMessage(message);
 	builder.show();
 }
}
