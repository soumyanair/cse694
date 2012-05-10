/*
 * Loads appropriate game view.
 * Stores user choices for the game
 */
package com.cse694.phyzwizard;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.*;

public class Session extends Activity{
	
	private static final int REQUEST_EXIT = 0;
	double friction,gravitation;
	int level;
	PhysicsWorldLevelOne viewOne;
	PhysicsWorldLevelTwo viewTwo;
	Handler mHandler;
	Handler handler;
	EditText force_value;
	boolean killme = false;
	
	public void onCreate(Bundle bundle){
	super.onCreate(bundle);
	Intent intent = getIntent();
	Bundle extras = intent.getExtras();
	int texture = extras.getInt("surfacetype");
	int planet = extras.getInt("planet");
	level = extras.getInt("level");
	
	
	Mappings map = Mappings.getMappings();
	
	friction = map.getFriction(texture);
	gravitation = map.getGravity(planet);
	
	if(level == 1)
	{
		viewOne = new PhysicsWorldLevelOne(this,this,(float)gravitation,(float)friction);
		
		RelativeLayout myLayout = new RelativeLayout(this);
        myLayout.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        
        force_value=new EditText(this);
        force_value.setHeight(50);
        force_value.setWidth(200);
        force_value.setX(20);
        force_value.setY(100);
              
        Button runButton = new Button(this);
        runButton.setText("Run");
        runButton.setX(20);
        runButton.setY(150);
        runButton.setHeight(40);
        runButton.setWidth(50);
        runButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Float force = new Float(force_value.getText().toString());
            	viewOne.applyForce(force);
            	handler = new Handler();  
        	    handler.post(updateOne);
            }
        });
        
        TextView enter_force = new TextView(this);
        enter_force.setText("Enter the value of force needed for the ball to reach the destination: ");
        enter_force.setHeight(50);
        enter_force.setWidth(400);
        enter_force.setX(20);
        enter_force.setY(50);
        
        myLayout.addView(viewOne);
        myLayout.addView(runButton);
        myLayout.addView(enter_force);
        myLayout.addView(force_value);

        runButton.bringToFront();
		
		setContentView(myLayout);
		
	}
	else
	{
		viewTwo = new PhysicsWorldLevelTwo(this,(float)gravitation);
		setContentView(viewTwo);

		 mHandler = new Handler();  
	      mHandler.post(update);
	}
	
	 
	
	}
	
	//Time scheduled thread for calling update function in Level one
	 private Runnable updateOne = new Runnable() {  
	        public void run() {  
	        	if(killme)return;
	            viewOne.update();  
	            handler.postDelayed(updateOne, (long) (viewOne.timeStep*10));  
	        }  
	    }; 

		//Time scheduled thread for calling update function in Level Two
	 private Runnable update = new Runnable() {  
	        public void run() {  
	        	if(killme)return;
	            viewTwo.update();  
	        	setContentView(viewTwo);
	            mHandler.postDelayed(update, (long) (viewTwo.timeStep*10));  
	        }  
	    }; 
	    
	   
	    //Pops up message asking user to end game or continue
	    public void endGame()
	    {
	    	 new AlertDialog.Builder(this)
	 		.setTitle("Exit")
	 		.setMessage("Exit the game?")
	 		.setIcon(android.R.drawable.ic_dialog_alert)
	 		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	 			  public void onClick(DialogInterface dialog, int which) {
	 			     setResult(RESULT_OK, null);
	 			     killme = true;
	 				  finish();
	 				  	 				  
	 			  }
	 		 })
	 		.setNegativeButton("No", new DialogInterface.OnClickListener() {
	 			public void onClick(DialogInterface dialog, int which) { 
	 				  startActivityForResult(getIntent(),REQUEST_EXIT);
	 				
	 			}
	 		 })
	 		.show();
	 	    }
	    
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	        if (requestCode == REQUEST_EXIT) {
	             if (resultCode == RESULT_OK) {
	            	 setResult(RESULT_OK, null);
	        		  finish();
		 			  killme = true;
	        		  this.finish();
	        		

	             }
	         }
	    }
	    
	    	
	    }

