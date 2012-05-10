/*
 * Implements Box2d PhysicsWorld for level one
 */

package com.cse694.phyzwizard;


import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.content.*;
import android.view.*;


public class PhysicsWorldLevelOne extends View{


public float timeStep = (1.f/60.f);  
public int iterations = 10; 


private Body body ;

public World world;


private Paint paint;

private float size= 4;

float width = 550.0f;
float height = 5.0f;

float damping;
int count = 0;

int ground_pos = 400;

double rand = Math.random();
double destination = (rand * 100)%100 + 200;
double prev_loc = -20000;

Context context;


public PhysicsWorldLevelOne(Session session,Context c,float g,float f) {
	
	super(c);
	
	context = c;
  
  
    Vec2 gravity = new Vec2((float) 0.0, g * -1);
    boolean doSleep = true;
    world = new World(gravity, doSleep);
    
    //Initialize ground body
    BodyDef bodyDef;
    BodyDef groundBodyDef = new BodyDef();
    groundBodyDef.type = BodyType.STATIC;
    groundBodyDef.position.set(new Vec2((float) 0.0, (float) -1 * ground_pos));  
    Body groundBody = world.createBody(groundBodyDef);
    
    PolygonShape groundShapeDef = new PolygonShape();  
    groundShapeDef.setAsBox(width, height);

    
    FixtureDef fd = new FixtureDef();
    fd.friction = f;
    fd.shape = groundShapeDef;
    fd.density = 7.f;
    fd.restitution = .4f;
    groundBody.createFixture(fd);
    
    //Initialize walls
   
    BodyDef boundDef = new BodyDef();
    boundDef.type = BodyType.STATIC;
    boundDef.position.set(new Vec2((float) 460.,(float) -1 * ground_pos/2));  
     groundBody = world.createBody(boundDef);
    
    groundShapeDef = new PolygonShape();  
    groundShapeDef.setAsBox(4, ground_pos/2);

    
    fd = new FixtureDef();
    fd.friction = f;
    fd.shape = groundShapeDef;
    fd.density = 7.f;
    fd.restitution = .4f;
    groundBody.createFixture(fd);
    
    //Initialize walls
    boundDef = new BodyDef();
    boundDef.type = BodyType.STATIC;
    boundDef.position.set(new Vec2((float) 0.0, (float) -1 * ground_pos/2));  
     groundBody = world.createBody(boundDef);
    
     groundShapeDef = new PolygonShape();  
    groundShapeDef.setAsBox(4, ground_pos/2);

    
     fd = new FixtureDef();
    fd.friction = f;
    fd.shape = groundShapeDef;
    fd.density = 7.f;
    fd.restitution = .4f;
    groundBody.createFixture(fd);
    
    
    
    // step 4: initialize paint
  
    paint=new Paint();
    paint.setStyle(Style.FILL_AND_STROKE);
    paint.setColor(Color.RED);


    //Initialize Dynamic body
    bodyDef = new BodyDef();
    bodyDef.position.set(100.0f,groundBodyDef.position.y+size+height);
    bodyDef.type = BodyType.DYNAMIC;
    body = world.createBody(bodyDef);

    // Create Shape with Properties
    PolygonShape box = new PolygonShape();
    box.setAsBox(size, size);
    FixtureDef sd = new FixtureDef();
    sd.density = (float) 2.;
    sd.friction = .3f;
    sd.shape = box;

    // Assign fixture to Body
    body.createFixture(sd);
    body.setLinearVelocity(new Vec2(0.f,0.f));

    body.setLinearDamping(.01f);
    

    
       
}

//Calculates world forces at time-steps and redraws view
public void update() {  
		
world.step(timeStep, iterations,iterations);
if(prev_loc == body.getPosition().x)
{
    if(++count==1)
	((Session)context).endGame();
	
}
else
	prev_loc = body.getPosition().x;

postInvalidate();

}


public void applyForce(float force)
{
	Vec2 f = new Vec2(force,0);
	body.applyLinearImpulse(f, new Vec2(body.getPosition().x,body.getPosition().y));
		
}

protected void onDraw(Canvas canvas) {
   
        canvas.drawRect(body.getPosition().x-size, -(body.getPosition().y) - size, body.getPosition().x + size, -(body.getPosition().y) + size, paint);
        Rect r = new Rect();
        r.set(0, ground_pos - 5, 500, ground_pos + 5);        
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        canvas.drawRect(r, p);
        Rect dest = new Rect();
        dest.set((int)destination,ground_pos - 25,(int)destination + 5,ground_pos - 5);
        canvas.drawRect(dest, p);
        canvas.drawText("Distance : " + ((int)destination - 100) + " meters" , 125, ground_pos + 20, p);
         
   }

}
