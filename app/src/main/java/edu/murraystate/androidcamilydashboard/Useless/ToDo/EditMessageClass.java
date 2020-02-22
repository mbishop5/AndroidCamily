package edu.murraystate.androidcamilydashboard.Useless.ToDo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import edu.murraystate.androidcamilydashboard.R;

public class EditMessageClass extends AppCompatActivity {
    String messageText;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.example_item );
        Intent intent= getIntent ();
        messageText=intent.getStringExtra ( Intent_Constants.INTENT_MESSAGE_DATA );
        position = intent.getIntExtra ( Intent_Constants.INTENT_ITEM_POSITION,-1 );
        EditText messageData = (EditText) findViewById ( R.id.massage );
        messageData.setText ( messageText );



    }
    public void saveButtonClicked(View v){
        String changedMassageText = ((EditText) findViewById (R.id.massage ) ).getText().toString();
        Intent intent= new Intent (  );
        intent.putExtra (Intent_Constants.INTENT_CHANGED_MESSAGE,changedMassageText );
        intent.putExtra ( Intent_Constants.INTENT_ITEM_POSITION,position );
        setResult ( Intent_Constants.INTENT_RESULT_CODE_TWO, intent );
        finish ();
    }

}
