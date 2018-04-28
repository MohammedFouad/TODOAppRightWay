package com.example.mohamed.todoapprightway;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDatabaseOpenHelper myDatabaseOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    ListView taskListView;
    String nameItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddTeamMembers.class);
                startActivity(i);
            }
        });


        taskListView = findViewById(R.id.taskListView);


        myDatabaseOpenHelper = new MyDatabaseOpenHelper(this);
        sqLiteDatabase = myDatabaseOpenHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from team ", null);

        cursor.moveToFirst();

        ArrayList<String> arrayList = new ArrayList<>();

        while (!cursor.isAfterLast()) {

            arrayList.add(cursor.getString(1));
            cursor.moveToNext();
        }
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        taskListView.setAdapter(arrayAdapter);

        cursor.close();

        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, ReadTeamMember.class);

                i.putExtra("id", arrayAdapter.getItem(position).toString());
                nameItem = arrayAdapter.getItem(position).toString();
                i.putExtra("position", String.valueOf(position));
                startActivity(i);

            }
        });


        taskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                myDatabaseOpenHelper = new MyDatabaseOpenHelper(MainActivity.this);
                sqLiteDatabase = myDatabaseOpenHelper.getWritableDatabase();

                sqLiteDatabase.execSQL("DELETE FROM team where name=? ",
                        new String[]{arrayAdapter.getItem(position).toString()});

                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return true;
            }
        });


    }

}

