package edu.murraystate.androidcamilydashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import edu.murraystate.androidcamilydashboard.Useless.ToDo.Activity_ToDoList;

public class MainActivity extends AppCompatActivity {
    LinearLayout rellay_todolist;
    LinearLayout rellay_personal;
    LinearLayout rellay_kitchen;
    LinearLayout rellay_familylocator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        rellay_todolist = findViewById(R.id.rellay_todolist);
        rellay_personal = findViewById(R.id.rellay_personal);
        rellay_kitchen = findViewById(R.id.rellay_kitchen);
        rellay_familylocator = findViewById(R.id.rellay_familylocator);

        rellay_todolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_ToDoList.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        rellay_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_personal.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        rellay_kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_kitchen.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        rellay_familylocator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_familylocator.class);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}
