package com.example.to_do_list;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    SimpleCursorAdapter adapter;
    DBManager db;
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.TITLE, DatabaseHelper.DESC };

    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        db = new DBManager(this);
        db.open();
        Cursor cursor = db.cursor();

        listView = findViewById(R.id.list);

        adapter = new SimpleCursorAdapter(this, R.layout.activity_task_items, cursor, from, to, 0);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        listView.setOnItemClickListener((parent, view, position, id) -> {

            TextView _id,title,desc;

            _id = view.findViewById(R.id.id);
            title = view.findViewById(R.id.title);
            desc = view.findViewById(R.id.desc);

            String idT = _id.getText().toString();
            String titleT = title.getText().toString();
            String descT = desc.getText().toString();


            Intent i = new Intent(MainActivity.this,ModifyTask.class);
            i.putExtra("id",idT);
            i.putExtra("title",titleT);
            i.putExtra("desc",descT);
            startActivity(i);



        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add_record){
            Intent add_mem = new Intent(this,AddTask.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}