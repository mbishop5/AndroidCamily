package edu.murraystate.androidcamilydashboard.Useless.ToDo;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.murraystate.androidcamilydashboard.R;

public class EditFieldClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.example_item );
    }
public void saveButtonClicked(View v){
        String massageText = ((EditText)findViewById ( R.id.massage )).getText ().toString ();
        if(massageText.equals ( "" )){

        }
else {
            Intent intent = new Intent (  );
            intent.putExtra ( Intent_Constants.INTENT_MASSAGE_FIELD,massageText );
            setResult ( Intent_Constants.INTENT_RESULT_CODE,intent );
            finish ();
        }
}


}