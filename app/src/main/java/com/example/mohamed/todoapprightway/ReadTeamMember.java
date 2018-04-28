package com.example.mohamed.todoapprightway;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReadTeamMember extends AppCompatActivity {

    TextView contactID, contactName, contactPhoneNumber;
    Button editContactData;
    MyDatabaseOpenHelper contactsDatabase;
    SQLiteDatabase sqLiteDatabase;
    String id, position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_team_member);

        // contactID=findViewById(R.id.contactID);
        contactName = findViewById(R.id.contactName);
        contactPhoneNumber = findViewById(R.id.phone);

        Intent i = getIntent();
      id = i.getStringExtra("id");
         position = i.getStringExtra("position");
        //int teamID= Integer.valueOf(id);
        //  contactID.setText(id);

        contactsDatabase = new MyDatabaseOpenHelper(this);
        sqLiteDatabase = contactsDatabase.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from team where name=?",
                new String[]{id});
        if (cursor.moveToFirst()) {
            contactName.setText(cursor.getString(1));
            contactPhoneNumber.setText(cursor.getString(3));
        }

    }
    // delete method
    public void deleteTask(){

        contactsDatabase=new MyDatabaseOpenHelper(this);
        sqLiteDatabase=contactsDatabase.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM team where name=? ",
                new String[]{contactName.getText().toString()});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.read_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.edit_task) {
            // write statement to edit task

            Intent i = new Intent(ReadTeamMember.this, EditTeam.class);
            i.putExtra("id", id);
            i.putExtra("position", String.valueOf(position));
            startActivity(i);


        }

        if (item.getItemId() == R.id.delete_task) {
            // delete task from menu
            // update tasks list in main activity
            deleteTask();
            Intent i = new Intent(ReadTeamMember.this,MainActivity.class);

            startActivity(i);
            finish();

        }


        return super.onOptionsItemSelected(item);
    }
}
