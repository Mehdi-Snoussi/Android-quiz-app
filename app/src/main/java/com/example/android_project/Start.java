package com.example.android_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Start extends AppCompatActivity {
    private TextView WelcomeTV;
    private Button startBtn,insert,callBtn;
    private DB DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

         DB = new DB(this);

        startBtn = (Button) findViewById(R.id.btnGoHome);

        WelcomeTV = findViewById(R.id.WelcomeUsername);
        Bundle get = getIntent().getExtras();
        String nom = get.getString("name");
        WelcomeTV.setText("Welcome Back "+nom);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        insert = findViewById(R.id.btnGoRate);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        callBtn = findViewById(R.id.btnCall);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 93722113"));
                startActivity(call);
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(Start.this);
        View view=getLayoutInflater().inflate(R.layout.insert_model,null);
        al.setView(view);
        final EditText input=view.findViewById(R.id.rateText1);
        Button btn=view.findViewById(R.id.BtnAdd);
        final AlertDialog alertDialog=al.show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.addRate(new RateUs(input.getText().toString()));
                Toast.makeText(Start.this, "Sent", Toast.LENGTH_SHORT).show();

                List<RateUs> rateUs = DB.getAllRate();
                for (RateUs rt: rateUs){
                    String log = "Id: " + rt.getId() + ",Score: " + rt.getRateUs();
                    Log.d("Rate us: ",log);
                }
                alertDialog.dismiss();

            }
        });
    }

}