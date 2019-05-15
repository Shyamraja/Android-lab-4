package com.example.stockmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//importing volley dependencies here
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
//importing for Json objects
import org.json.JSONObject;
//import array list
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> stocks;
    ArrayAdapter<String> adapter;
    RequestQueue queue;
//volley request are made in queue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        //create a request in queue to manage volley requests
        queue = Volley.newRequestQueue(this);
        stocks = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stocks); //Pasing the context, the row layout  and the resource?
        listView.setAdapter(adapter); //Setting to the listview the arrayadapter that returns the view from the arraylist

        addStock("AAPL", "Apple");
        addStock("GOOGL","Alphabet(Google)");
        addStock("FB","Facebook");
        addStock("NOK","Nokia");
        addStock("RHT","Red Hat");
        addStock("INTL","INTEL");
                 }

    private void addStock(final String stockID, final String stockName) {

        String url = "https://financialmodelingprep.com/api/company/price/" +stockID+"?datatype=json";

        //Make request for objects
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

//this snippet fetches a JSON feed and displays as text in UI

                    @Override
                    public void onResponse(JSONObject response) {
                        //do something with response blocks of codes to be working
                        try          {
                            JSONObject value = response.getJSONObject(stockID);//check for object with id
                            Log.i("MAIN", "response: " + response.toString());
                            String price = value.getString("price");//get price
                            String Linestock = stockName+ ":"+price+"$";//get name and price after checking stockid
                            stocks.add(Linestock);//Add stock  it to the list of array
                            adapter.notifyDataSetChanged(); //And to the view
                              } catch (Exception e){
                            //block of codes to handle errors
                            System.out.println("Something went wrong.");
                                }
                                }
                   }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }

                });
        queue.add(jsonObjectRequest);
    }
}




