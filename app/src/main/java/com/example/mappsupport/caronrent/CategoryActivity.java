package com.example.mappsupport.caronrent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.CategoryAdapter;
import model.CategoryModel;

public class CategoryActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<CategoryModel> carList=new ArrayList<>();
    CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView=(RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);

        loadSeatDetals();
    }


    private void loadSeatDetals() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.myjson.com/bins/x8o0b",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray catarry = obj.getJSONArray("carcategory");
                            JSONObject cardetails=null;
                            for (int i = 0; i < catarry.length(); i++) {
                                cardetails = catarry.getJSONObject(i);
                                System.out.println("-------------"+cardetails.getString("name"));
                                CategoryModel categoryModel=new CategoryModel();
                                categoryModel.setName(cardetails.getString("name"));
                                categoryModel.setImage(cardetails.getString("image"));
                                System.out.println("-----image--------"+cardetails.getString("image"));
                                carList.add(categoryModel);
                            }
                            categoryAdapter=new CategoryAdapter(CategoryActivity.this,carList);
                            recyclerView.setAdapter(categoryAdapter);
                            progressBar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
