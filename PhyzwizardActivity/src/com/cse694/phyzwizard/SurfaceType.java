/*
 * Gets Users choice for texture
 */

package com.cse694.phyzwizard;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SurfaceType extends Activity  implements OnClickListener{
	
	
	private static final int REQUEST_EXIT = 0;
	Intent session;
	
	@Override
	 public void onCreate(Bundle bundle) {
	        super.onCreate(bundle);
			Intent intent = getIntent();
			Bundle extras = intent.getExtras();
			int planet = Integer.parseInt(extras.get("planet").toString());
			int level = Integer.parseInt(extras.get("level").toString());
			session = new Intent(SurfaceType.this,Session.class);
			session.putExtra("level", level);
			session.putExtra("planet", planet);
			Button surface1,surface2;
			
			switch(planet)
			{
			case 1 :
				setContentView(R.layout.earthsurface);
				surface1 = (Button)findViewById(R.id.bIce);
				surface2 = (Button)findViewById(R.id.bWood);
				surface1.setOnClickListener(this);
				surface2.setOnClickListener(this);
			
			break;
						
			case 2:
				setContentView(R.layout.moonsurface);
				surface1 = (Button)findViewById(R.id.bMaria);
				surface2 = (Button)findViewById(R.id.bCrater);
				surface1.setOnClickListener(this);
		        surface2.setOnClickListener(this);
			
			
			break;
			
			case 3:
				setContentView(R.layout.saturnsurface);
				 surface1 = (Button)findViewById(R.id.bTitan);
				 surface2 = (Button)findViewById(R.id.bEnceladus);
				 surface1.setOnClickListener(this);
				 surface2.setOnClickListener(this);			
			break;
			
			case 4:
				setContentView(R.layout.marssurface);
				surface1 = (Button)findViewById(R.id.bPlane);
				surface2 = (Button)findViewById(R.id.bRock);
				surface1.setOnClickListener(this);
				surface2.setOnClickListener(this);
			
			}
			}
		
			
	
	                 
	       
	public void onClick(View view) {
        //check which button was clicked with its id
        switch(view.getId()) {
            case R.id.bIce:
            	session.putExtra("surfacetype", 1);
                break;
            case R.id.bWood:
            	session.putExtra("surfacetype", 2);
                break;
            case R.id.bPlane:
            	session.putExtra("surfacetype", 3);
                break;
            case R.id.bRock:
            	session.putExtra("surfacetype", 4);
                break;
            case R.id.bMaria:
            	session.putExtra("surfacetype", 5);
                break; 
            case R.id.bCrater:
            	session.putExtra("surfacetype", 6);
                break;
            case R.id.bTitan:
            	session.putExtra("surfacetype", 7);
                break;
            case R.id.bEnceladus:
            	session.putExtra("surfacetype", 8);
               
		}
        
        startActivityForResult(session,REQUEST_EXIT);
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
