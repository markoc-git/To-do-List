package com.example.to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyTask extends AppCompatActivity {
    DBManager db;
    Button delBtn,edtBtn;
    EditText newTitleE,newDescE;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_task);

        db = new DBManager(this);
        delBtn = findViewById(R.id.delete_button);
        edtBtn = findViewById(R.id.edit_button);
        newTitleE = findViewById(R.id.newTitle);
        newDescE = findViewById(R.id.newDesc);

        setTitle("Modify Task");

        Intent i = getIntent();
        String oldTitle = i.getStringExtra("title");
        String oldDesc = i.getStringExtra("desc");
        long id = Long.parseLong(i.getStringExtra("id"));

        newTitleE.setText(oldTitle);
        newDescE.setText(oldDesc);

        db.open();

        edtBtn.setOnClickListener(v -> {

            String newTitle = newTitleE.getText().toString();
            String newDesc = newDescE.getText().toString();

            if (newTitle.isEmpty()) {
                Toast.makeText(this, "Enter data in field", Toast.LENGTH_SHORT).show();
                return;
            }
            db.update(id,newTitle,newDesc);
            returnHome();
        });

        delBtn.setOnClickListener(v -> {
            db.delete(id);
            returnHome();
        });

    }
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);

    }

}