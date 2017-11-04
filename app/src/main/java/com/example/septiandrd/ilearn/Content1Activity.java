package com.example.septiandrd.ilearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Content1Activity extends AppCompatActivity {

    Button btnStart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_content1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Programming Basics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnStart1 = (Button)findViewById(R.id.btn_start_content1);
        btnStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sorry, this feature is not available yet.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
