/*
 * Store mappigs for gravity,coefficient of friction
 */

package com.cse694.phyzwizard;

public class Mappings {
	
	double gravity[] =  { 9.81,1.62,11.08,3.77};
	double friction[] = { .05,.6,0.30,0.63,0.70,0.60,0.80,0.35};
	static Mappings mapping;
	
	private Mappings()
	{
		
	}
	
	static Mappings getMappings()
	{
		if(mapping == null)
			mapping = new Mappings();
		
		return mapping;		
	}
	
	
	public double getGravity(int planet)
	{
		return gravity[planet-1];
	}
	
	public double getFriction(int texture)
	{
		return friction[texture-1];
	}
	
	
	
	
	
	
	

}
