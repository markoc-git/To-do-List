package com.example.to_do_list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {
    EditText titleE , descE;
    Button addTask;
    DBManager db ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        setTitle("Add Task");

        db = new DBManager(this);

        addTask = findViewById(R.id.add);
        titleE = findViewById(R.id.name);
        descE = findViewById(R.id.description);

        db.open();

        addTask.setOnClickListener(v -> {
            String title = titleE.getText().toString();
            String descr = descE.getText().toString();

            if(title.isEmpty()){
                Toast.makeText(this, "Enter data in field", Toast.LENGTH_SHORT).show();
                return;
            }

            db.insert(title,descr);

            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        });

    }


}