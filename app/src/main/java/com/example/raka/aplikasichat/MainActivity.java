package com.example.raka.aplikasichat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    public static String preff = "file.main.message";
    RecyclerView recyclerView;
    ChatingAdapter chatingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences pref = getSharedPreferences(preff,0);
        String dataMessage = pref.getString("message","");
        try {
            JSONArray jsonArray = new JSONArray(dataMessage);
            chatingAdapter = new ChatingAdapter(jsonArray);

            recyclerView.setAdapter(chatingAdapter);
            chatingAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("JSON", dataMessage);
    }

    public void addPesan(View view) {
        startActivity(new Intent(this, tambah_chat.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
