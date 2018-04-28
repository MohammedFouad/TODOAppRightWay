package com.example.mohamed.todoapprightway;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.mohamed.todoapprightway.MyDatabaseOpenHelper.TABLE_TEAM;

public class AddTeamMembers extends AppCompatActivity {

    EditText memberName,memberMascot,memberCity;
    MyDatabaseOpenHelper myDatabaseOpenHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team_members);

        memberName=findViewById(R.id.nameMember);
        memberMascot=findViewById(R.id.mascotMember);
        memberCity=findViewById(R.id.cityMember);

    }

// method for saving data for a team member


    public void saveData(){
       myDatabaseOpenHelper=new MyDatabaseOpenHelper(this);
        sqLiteDatabase=myDatabaseOpenHelper.getWritableDatabase();

        sqLiteDatabase.execSQL("insert into team(name,mascot,city) values(?,?,?)",
                new String[]{
                        memberName.getText().toString(),
                        memberMascot.getText().toString(),
                        memberCity.getText().toString()
                });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.title_save) {

            saveData();
            Toast.makeText(AddTeamMembers.this, " Task saved", Toast.LENGTH_LONG).show();
            Intent i = new Intent(AddTeamMembers.this,MainActivity.class);
            startActivity(i);
            finish();

        }
        if (item.getItemId()==R.id.title_cancel){

            Toast.makeText(AddTeamMembers.this," canceled",Toast.LENGTH_SHORT).show();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
