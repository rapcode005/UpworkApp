package com.upworkapp.upworkapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bDriver,bCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bDriver = findViewById(R.id.buttonDriver);
        bCustomer = findViewById(R.id.buttonCustomer);

        bDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intDriver = new Intent(MainActivity.this,DriverResLoginActivity.class);
                startActivity(intDriver);
            }
        });

        bCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intDriver = new Intent(MainActivity.this,CustomerLoginActivity.class);
                startActivity(intDriver);
            }
        });
    }
}
