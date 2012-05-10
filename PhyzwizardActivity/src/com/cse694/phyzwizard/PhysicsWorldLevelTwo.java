/*
 * Implements Box2d PhysicsWorld for level two
 */

package com.cse694.phyzwizard;


import org.jbox2d.collision.shapes.*;
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
import android.content.*;
import android.view.*;


public class PhysicsWorldLevelTwo extends View{


public float timeStep = (1.f/60.f);  
public int iterations = 8; 


private Body body ;

public World world;

private Paint paint;

private float radius= 6;

float width = 250.0f;
float height = 5.0f;

private int count = 0;
private Context context;



public PhysicsWorldLevelTwo(Context c,float g) {
	
    super(c);
    
    context = c;
    
    //Create World
    Vec2 gravity = new Vec2((float) 0.0, g * -1);
    boolean doSleep = true;
    world = new World( gravity, doSleep);

    //Create Ground Body
    BodyDef bodyDef;
    BodyDef groundBodyDef = new BodyDef();
    groundBodyDef.type = BodyType.STATIC;
    groundBodyDef.position.set(new Vec2((float) 0.0, (float)-500.0));  
    Body groundBody = world.createBody(groundBodyDef);
    
    PolygonShape groundShapeDef = new PolygonShape();  
    groundShapeDef.setAsBox(width, height);

    
    FixtureDef fd = new FixtureDef();
    fd.friction = .5f;
    fd.shape = groundShapeDef;
    fd.density = 7.f;
    fd.restitution = .4f;
    groundBody.createFixture(fd);
    
    
    // initialize paint 
    
    paint=new Paint();
    paint.setStyle(Style.FILL_AND_STROKE);
    paint.setColor(Color.RED);


 // Create Dynamic Body
    bodyDef = new BodyDef();
    bodyDef.position.set(100.0f,-100.0f);
    bodyDef.type = BodyType.DYNAMIC;
    body = world.createBody(bodyDef);

    // Create Shape with Properties
    CircleShape circle = new CircleShape();
    circle.m_radius= (float) radius ;
    
    FixtureDef sd = new FixtureDef();
    sd.density = (float) 2.;
    sd.friction = .5f;
    sd.shape = circle;

    // Assign fixture to Body
    body.createFixture(sd);
    
    body.setLinearVelocity(new Vec2(0.f,0.f)); 
    
    
}

//Calculates world forces at time-steps and redraws view
public void update() {  
	
world.step(timeStep, iterations,iterations);
postInvalidate();
if(!body.isAwake() && ++count == 1)
	((Session)context).endGame();
	


}  

protected void onDraw(Canvas canvas) {
   
	//Draw  Sphere
        canvas.drawCircle(body.getPosition().x,-body.getPosition().y, radius, paint);
    //Draw Ground Body    
        Rect r = new Rect();
        r.set(0, 495, 500, 505);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        canvas.drawRect(r, p);
        
 
   }

}
