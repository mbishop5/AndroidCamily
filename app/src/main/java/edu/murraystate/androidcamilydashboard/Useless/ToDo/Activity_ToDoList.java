/*package edu.murraystate.androidcamilydashboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Activity_ToDoList extends  AppCompatActivity{
    ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        buildRecyclerView();
        setInsertButton();

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();


        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
         mRecyclerView =  findViewById ( R.id.recyclerview );
            mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager ( this );
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager( mLayoutManager );
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setInsertButton() {
        Button buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText line1 = findViewById(R.id.edittext_line_1);
                EditText line2 = findViewById(R.id.edittext_line_2);
                insertItem(line1.getText().toString(), line2.getText().toString());
            }
        });
    }

    private void insertItem(String line1, String line2) {
        mExampleList.add(new ExampleItem(line1, line2));
        mAdapter.notifyItemInserted(mExampleList.size());
    }
}
*/

package edu.murraystate.androidcamilydashboard.Useless.ToDo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import edu.murraystate.androidcamilydashboard.R;

public class  Activity_ToDoList extends AppCompatActivity {
ListView listView;
ArrayList<String> arrayListToDo;
ArrayAdapter<String> arrayAdapterToDo;
String massageText;
int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_todolist );
        listView = (ListView) findViewById ( R.id.Listview );
        arrayListToDo = new ArrayList <> ();
        arrayAdapterToDo = new ArrayAdapter <String> ( this , android.R.layout.simple_list_item_1 , arrayListToDo );
        listView.setAdapter ( arrayAdapterToDo );

        listView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {

            @Override
            public void onItemClick(AdapterView <?> parent , View view , int position , long id) {
                Intent intent=new Intent ();
                intent.setClass ( Activity_ToDoList.this, EditMessageClass.class );
                intent.putExtra ( Intent_Constants.INTENT_MESSAGE_DATA,arrayListToDo.get(position).toString () );
                intent.putExtra (Intent_Constants.INTENT_ITEM_POSITION,position  );
                startActivityForResult (intent,Intent_Constants.INTENT_REQUEST_CODE_TWO );

            }
        } );
        try {

            Scanner sc = new Scanner ( openFileInput ( "Todo.txt" ) );
            while (sc.hasNextLine ()) {
                String data = sc.nextLine ();
                arrayAdapterToDo.add ( data );
            }
            sc.close ();
        }
        catch (FileNotFoundException e){
            e.printStackTrace ();
        }

    }

@Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.setHeaderTitle ( "what would you like to do" );
        String options[]={"Delete","Cancel"};
        for (String option : options){
            menu.add ( option );
        }
}
@Override
public void onBackPressed(){
       try {
           PrintWriter pw = new PrintWriter ( openFileOutput ( "Todo.txt" , Context.MODE_PRIVATE ) );
           for (String data : arrayListToDo) {
               pw.println ( data );
           }
           pw.close ();
       }catch(FileNotFoundException e){
               e.printStackTrace ();

           }
       finish ();
       }

    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setClass (Activity_ToDoList.this, EditFieldClass.class );
        startActivityForResult (intent,Intent_Constants.INTENT_REQUEST_CODE  );

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Intent_Constants.INTENT_REQUEST_CODE) {
    massageText=data.getStringExtra ( Intent_Constants.INTENT_MASSAGE_FIELD  );
    arrayListToDo.add ( massageText );
    arrayAdapterToDo.notifyDataSetChanged ();
        }
        else if(resultCode==Intent_Constants.INTENT_REQUEST_CODE_TWO ) {
            massageText=data.getStringExtra(Intent_Constants.INTENT_CHANGED_MESSAGE );
            position = data.getIntExtra ( Intent_Constants.INTENT_ITEM_POSITION,-1 );
            arrayListToDo.remove (position );
            arrayListToDo.add ( position,massageText );
            arrayAdapterToDo.notifyDataSetChanged ();


        }
    }
}

/*
package edu.murraystate.androidcamilydashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity_ToDoList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
    }

    public void onClick(View view) {
    }
}
*/