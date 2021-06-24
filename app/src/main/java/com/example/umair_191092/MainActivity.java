package com.example.umair_191092;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText in_ID , in_NAME , in_AGE , in_CITY;
    Button button_1, button_2;
    //TextView text1;
    RecyclerView RecView;
    SQlite sqLite;
    String allRecords;
    RecyclerAdapter adapter;
    List<Model> MyRecords = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        in_ID = findViewById(R.id.edtxno1);
        in_NAME = findViewById(R.id.edtxno2);
        in_AGE = findViewById(R.id.edtxno3);
        in_CITY = findViewById(R.id.edtxno4);
        button_1 = findViewById(R.id.but1);
        button_2 = findViewById(R.id.but2);
        RecView = findViewById(R.id.recycler1);

        sqLite = new SQlite(this);

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = in_ID.getText().toString();
                String name = in_NAME.getText().toString();
                String city = in_CITY.getText().toString();
                String age = in_AGE.getText().toString();

                boolean checksave = sqLite.insertstudentdata(id, name , city, age);
                if ( checksave == true ){
                    Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Dara Not saved", Toast.LENGTH_SHORT).show();
                }

            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataDisplay();
            }
        });
    }

    public void DataDisplay(){
        allRecords ="";
        Cursor record_Cursor = sqLite.getstudentdata();
        if (record_Cursor.getCount()==0)
        {
            Toast.makeText(this, "No Record found", Toast.LENGTH_SHORT).show();
        }
        while(record_Cursor.moveToNext()){
            String eachrecord;
            eachrecord = "ID " + record_Cursor.getString(0)+"\n";
            eachrecord = eachrecord + "NAME " + record_Cursor.getString(1)+"\n";
            eachrecord = eachrecord + "CITY " + record_Cursor.getString(2)+"\n";
            eachrecord = eachrecord + "AGE " + record_Cursor.getString(3)+"\n";
            allRecords = allRecords + eachrecord;
            MyRecords.add(new Model(allRecords));
        }
        adapter = new RecyclerAdapter(MyRecords,MainActivity.this );
        RecView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        RecView.setAdapter(adapter);
    }

}