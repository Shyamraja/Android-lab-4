package com.example.lab42;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


//These are dependiencis from volly for build.gradle in app module
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn;
    EditText EditText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btn = findViewById(R.id.btn);
        EditText1 = findViewById(R.id.EditText1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openweb();
            }
             });
              }
    private void openweb() {

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://" + EditText1.getText().toString();

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
