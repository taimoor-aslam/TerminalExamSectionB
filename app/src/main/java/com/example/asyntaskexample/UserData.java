package com.example.asyntaskexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class UserData extends AppCompatActivity {
    RecyclerView recyclerView;
    View view;
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        db=new DataBaseHelper(this);
        try {
            ArrayList<UserInfo> mylist = db.show();
            //ArrayList<MyListData> mylist = new ArrayList<>();
            MyListAdapter myListAdapter = new MyListAdapter(mylist);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(myListAdapter);
        }catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
