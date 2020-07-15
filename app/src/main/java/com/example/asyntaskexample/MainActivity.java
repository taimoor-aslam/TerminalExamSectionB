package com.example.asyntaskexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText titleedt,taskedt,deadlinedt;
Button addbtn,showbtn;
DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleedt=(EditText) findViewById(R.id.title);
        taskedt=(EditText) findViewById(R.id.taskdetails);
        deadlinedt=(EditText) findViewById(R.id.deadline);
        addbtn=(Button) findViewById(R.id.addbtn);
        showbtn=(Button) findViewById(R.id.showbtn);
        db=new DataBaseHelper(this);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleedt.getText().toString();
                String task=taskedt.getText().toString();
                String deasline=deadlinedt.getText().toString();
                try {
                    db.insert(title, task, deasline);
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });
        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,UserData.class);
                startActivity(it);
            }
        });




    }

}
