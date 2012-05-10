/*
 * This activity asks the user to choose Planet/Level
 */
package com.cse694.phyzwizard;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Planets extends Activity implements OnClickListener{
	
	enum planets {Earth,Moon,Saturn,Mars}

	private static final int REQUEST_EXIT = 0;;
	
	Intent sType;
	@Override
	 public void onCreate(Bundle bundle) {
	        super.onCreate(bundle);
	        setContentView(R.layout.planets);
	        Button earth = (Button)findViewById(R.id.bEarth);
	        Button moon = (Button)findViewById(R.id.bMoon);
	        Button saturn = (Button)findViewById(R.id.bSaturn);
	        Button mars = (Button)findViewById(R.id.bMars);
	        earth.setOnClickListener(this);
	        moon.setOnClickListener(this);
	        saturn.setOnClickListener(this);
	        mars.setOnClickListener(this);
	        sType = new Intent(Planets.this,SurfaceType.class);

	        
	        String[] levels = {"Level 1", "Level 2"};
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(getString(R.string.levelSelect));
			builder.setItems(levels, new DialogInterface.OnClickListener() {		//Called when user selects the level
			public void onClick(DialogInterface dialog, int item) {
			
			sType.putExtra("level", item+1);			
				    
			    	
			    }
			});
	        
	        AlertDialog alert = builder.create();
	    	alert.show();
	    	
	    
	    }
	
	//Called when user selects planet
	public void onClick(View view) {
        //check which button was clicked with its id
        switch(view.getId()) {
            case R.id.bEarth:
            	sType.putExtra("planet", 1);
                break;
            case R.id.bMoon:
            	sType.putExtra("planet", 2);
                break;
            case R.id.bSaturn:
            	sType.putExtra("planet", 3);
                break;
            case R.id.bMars:
            	sType.putExtra("planet", 4);
               
		}
        
        startActivityForResult(sType,REQUEST_EXIT);
    }
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	        if (requestCode == REQUEST_EXIT) {
	             if (resultCode == RESULT_OK) {
	            	 setResult(RESULT_OK, null);
	 				  finish();

	             }
	         }
	    }
}
