package com.example.stockmonitorv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    EditText typestockName;
    EditText typestockID;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        queue = Volley.newRequestQueue(this);
        stocks = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stocks); //Pasing the context, the row layout  and the resource?
        listView.setAdapter(adapter); //Setting to the listview the arrayadapter that returns the view from the arraylist
        typestockName = findViewById(R.id.stockName);
        typestockID = findViewById(R.id.stockId);
        add = findViewById(R.id.button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStock();
                 }
               });

        addStock("AAPL", "Apple");
        addStock("GOOGL","Alphabet(Google)");
        addStock("FB","Facebook");
        addStock("NOK","Nokia");
        addStock("RHT","Red Hat");
        addStock("INTL","INTEL");
              }

        public void addStock() {
        String typename = typestockName.getText().toString();
        String typeID = typestockID.getText().toString();
        addStock(typeID, typename);

       }

    private void addStock(final String stockID, final String stockName) {

        String url = "https://financialmodelingprep.com/api/company/price/" +stockID+"?datatype=json";

        //Make request for objects
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {



                    @Override
                    public void onResponse(JSONObject response) {
                        try          {
                            JSONObject value = response.getJSONObject(stockID);//search for stock Id
                            Log.i("MAIN", "response: " + response.toString());
                            String price = value.getString("price");
                            String Linestock = stockName+ ":"+price+"$";//check stoch name from id and get name with price
                            stocks.add(Linestock);//Add stock  it to the list of array
                            adapter.notifyDataSetChanged(); //And to the view
                        } catch (Exception e){

                          }
                      }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }

                });
        queue.add(jsonObjectRequest);//adding stocks to get resonse in queue
    }
}







