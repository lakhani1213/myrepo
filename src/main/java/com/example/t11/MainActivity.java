package com.example.t11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UsersAdapter adapter = null;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        mRequestQueue = Volley.newRequestQueue(this);
        sendAndRequestResponse();
    }

    private void sendAndRequestResponse() {
        isNetworkConnected();
        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("please wait");
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, MyUtil.url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("records");
                            adapter = new UsersAdapter(jsonArray);
                            recyclerView.setAdapter(adapter);
                            dialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        if (dialog.isShowing())
                            dialog.dismiss();
                    }
                });
        mRequestQueue.add(request);
    }
    private void isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        if (nf != null && nf.isConnectedOrConnecting()) {
            Toast.makeText(this, "You are online", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "please check your internet connectivity", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.btnAbt:
                startActivity(new Intent(MainActivity.this, AboutUs.class));
                finish();
                break;


            case R.id.btnFeedback:
                startActivity(new Intent(MainActivity.this,Feedback.class));
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}

