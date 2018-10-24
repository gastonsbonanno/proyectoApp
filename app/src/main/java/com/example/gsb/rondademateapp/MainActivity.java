package com.example.gsb.rondademateapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        ArrayList<Category> category = new ArrayList<Category>();
        category.add(new Category("categoria1"));
        category.add(new Category("categoria2"));
        category.add(new Category("categoria3"));
        category.add(new Category("categoria4"));
        category.add(new Category("categoria5"));

        ListView lv = (ListView) findViewById(R.id.lo_lv_personas);

        AdapterItem adapter = new AdapterItem(this, category);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;


            }
        });


    }
}
