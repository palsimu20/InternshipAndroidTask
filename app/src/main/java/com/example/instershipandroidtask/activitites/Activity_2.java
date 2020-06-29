package com.example.instershipandroidtask.activitites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instershipandroidtask.R;
import com.example.instershipandroidtask.modelclass.Data;

public class Activity_2 extends AppCompatActivity {
    private Data data;
    private TextView name,email,id,next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        id=findViewById(R.id.id);
        next=findViewById(R.id.go);
       // retrive data
        Bundle bundle=getIntent().getExtras();
        data=(Data) bundle.getSerializable("data");
        String name1=data.getName();
        name.setText(String.valueOf(name1));
        String email1=data.getEmail();
        email.setText(String.valueOf(email1));
        String id1=data.getId();
        id.setText(String.valueOf(id1));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Activity_4.class));
            }
        });

    }
}
