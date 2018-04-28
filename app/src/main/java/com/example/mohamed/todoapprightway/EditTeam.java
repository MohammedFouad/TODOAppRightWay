package com.example.mohamed.todoapprightway;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditTeam extends AppCompatActivity {

    EditText editTaskTitle,editTaskDetails;

   MyDatabaseOpenHelper taskDatabasseHelper;
    SQLiteDatabase sqLiteDatabase;
    String id;
    String position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);

        editTaskTitle=findViewById(R.id.editTaskTitle);
        editTaskDetails=findViewById(R.id.editTaskDetails);

        Intent i = getIntent();
  id = i.getStringExtra("id");
       position = i.getStringExtra("position");

        taskDatabasseHelper=new MyDatabaseOpenHelper(this);
        sqLiteDatabase=taskDatabasseHelper.getReadableDatabase();

        Cursor cursor= sqLiteDatabase.rawQuery("select * from team where name=?",
                new String[]{id});
        if( cursor.moveToFirst()){
            editTaskTitle.setText(cursor.getString(1));
            editTaskDetails.setText(cursor.getString(3));

        }


    }
    public void updateTask(){
        taskDatabasseHelper=new MyDatabaseOpenHelper(this);
        sqLiteDatabase=taskDatabasseHelper.getWritableDatabase();

        sqLiteDatabase.execSQL("update team set name=?,city=? where name=?",
                new String[]{
                        editTaskTitle.getText().toString(),
                        editTaskDetails.getText().toString(),id
                });

        Toast.makeText(this," Data Saved   " + position, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.item_update){
            updateTask();
            Intent i = new Intent(EditTeam.this,MainActivity.class);
            startActivity(i);
            finish();

        }
        if(item.getItemId()==R.id.item_cancel){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



}
