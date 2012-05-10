/*
 * First Activity Loaded in PhyzWizard Application.
 */

package com.cse694.phyzwizard;

import com.cse694.phyzwizard.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PhyzwizardActivity extends Activity implements OnClickListener{
    private static final int REQUEST_EXIT = 0;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button newGame = (Button)findViewById(R.id.bNew);
        Button exit = (Button)findViewById(R.id.bExit);
        newGame.setOnClickListener(this);
        exit.setOnClickListener(this);
    }
    
    public void onClick(View view) {
        //check which button was clicked with its id
        switch(view.getId()) {
            case R.id.bExit:
                finish();
                break;
            case R.id.bNew:
            	Intent config = new Intent(PhyzwizardActivity.this,Planets.class); 
            	startActivityForResult(config,REQUEST_EXIT);
		}
    }
    
    //Called when  the child activity calls finish();
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_EXIT) {
             if (resultCode == RESULT_OK) {
                System.exit(0);

             }
         }
    }

}