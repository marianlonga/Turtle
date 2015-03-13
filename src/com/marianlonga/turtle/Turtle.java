/* (c) 2015 Marian Longa. http://marianlonga.com. */

package com.marianlonga.turtle;

import java.awt.*;

public class Turtle {
	
	double x, y, save_x, save_y;
	double angle, save_angle;
	boolean penDown;
	Color color;
	Graphics g;
	
	public Turtle(int x, int y) {
		this.x = this.save_x = x;
		this.y = this.save_y = y;
		this.angle = this.save_angle = 0.0;
		this.color = Color.black;
		penDown = true;
	}
	
	public double getX() {return x;}
	public void setX(double x) {this.x = x;}
	public double getY() {return y;}
	public void setY(double y) {this.y = y;}
	public double getAngle() {return angle;}
	public void setAngle(double angle) {this.angle = angle;}
	
	public void penDown() {this.penDown = true;}
	public void penUp() {this.penDown = false;}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setColor(Color color) {this.color = color;}
	public Color getColor() {return this.color;}
	
	//public void saveState() {save_x = this.getX(); save_y = this.getY(); save_angle = this.getAngle();}
	//public void loadState() {this.x = this.save_x; this.y = this.save_y; this.angle = this.save_angle;}

	public void setGraphics(Graphics g) {this.g = g;}
	
	public void move(int distance) {
		double old_x = x;
		double old_y = y;
		x += distance * Math.sin(angle);
		y -= distance * Math.cos(angle);
		
		g.setColor(this.color);
		if(penDown == true) g.drawLine((int)old_x, (int)old_y, (int)x, (int)y);
	}
	
	public void rotate(double angle) {
		angle = Math.PI/180 * angle; // deg2rad
		this.angle += angle;
		while(this.angle < 0) this.angle += 2*Math.PI; // I only want positive angles
	}
	
	public void steps(int n, int length) {
		if(n == 0) return;
		this.move(length);
		this.rotate(90);
		this.move(length);
		this.rotate(-90);
		this.steps(n-1, length);
	}
	
	public void tree(int n, int length, double angle, double coefficient) {
		if(n == 0) return;
		this.setColor(new Color(0, 255-n*30, 0));
		this.move(length);
		double old_x = this.getX(), old_y=this.getY();
		double old_angle=this.getAngle();
		this.rotate(angle/2);
		this.tree(n-1, (int)(length/coefficient), angle, coefficient);
		this.setX(old_x); this.setY(old_y); this.setAngle(old_angle);
		this.rotate(-angle/2);
		this.tree(n-1, (int)(length/coefficient), angle, coefficient);
		//this.setX(old_x); this.setY(old_y); this.setAngle(old_angle);
	}
	
	public void triangle(int n, int length) {
		if(n == 0) return;
		double save_x, save_y, save_angle;
		save_x = this.getX(); save_y = this.getY(); save_angle = this.getAngle(); //saveState
		// large triangle
		this.rotate(90);
		this.move(length);
		this.rotate(-120);
		this.move(length);
		this.rotate(-120);
		this.move(length);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		// middle triangle
		this.penUp();
		this.rotate(90);
		this.move(length/2);
		this.penDown();
		this.rotate(-60);
		this.move(length/2);
		this.rotate(-120);
		this.move(length/2);
		this.rotate(-120);
		this.move(length/2);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		// upper minitriangle
		this.penUp();
		this.rotate(30);
		this.move(length/2);
		this.penDown();
		this.rotate(-30);
		triangle(n-1, length/2);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		// left down minitriangle
		triangle(n-1, length/2);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		// right down triangle
		this.rotate(90);
		this.penUp(); this.move(length/2); this.penDown();
		this.rotate(-90);
		triangle(n-1, length/2);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
	}
	
	public void kochStar(int n, int length) {
		/*double save_x, save_y, save_angle;
		save_x = this.getX(); save_y = this.getY(); save_angle = this.getAngle(); //saveState
		// large triangle
		this.rotate(90);
		this.move(length);
		this.rotate(-120);
		this.move(length);
		this.rotate(-120);
		this.move(length);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		kochStarRecursion(n, length);*/
		
		/* Draw top line */
		double save_x, save_y, save_angle;
		this.rotate(90);
		save_x = this.getX(); save_y = this.getY(); save_angle = this.getAngle(); //saveState
		this.move(length);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		kochStarRecursion(n, length); // enter recursion
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		this.penUp(); this.move(length); this.penDown();
		
		/* Draw bottom rigth line */
		this.rotate(120);
		save_x = this.getX(); save_y = this.getY(); save_angle = this.getAngle(); //saveState
		this.move(length);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		kochStarRecursion(n, length); // enter recursion
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		this.penUp(); this.move(length); this.penDown();
		
		/* Draw bottom left line */
		this.rotate(120);
		save_x = this.getX(); save_y = this.getY(); save_angle = this.getAngle(); //saveState
		this.move(length);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		kochStarRecursion(n, length); // enter recursion
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
	}
	public void kochStarRecursion(int n, int length) {
		if(n == 0) return;
		double save_x, save_y, save_angle;
		this.penUp();
		
		save_x = this.getX(); save_y = this.getY(); save_angle = this.getAngle(); //saveState
		kochStarRecursion(n-1, length/3);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		
		this.move(2*length/3);
		this.penDown();
		this.setColor(Color.white);
		this.move(-length/3);
		this.setColor(Color.black);
		this.rotate(-60);
		this.move(length/3);
		penUp(); this.move(-length/3); penDown();
		
		save_x = this.getX(); save_y = this.getY(); save_angle = this.getAngle(); //saveState
		kochStarRecursion(n-1, length/3);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		
		this.penUp();
		this.move(length/3);
		this.rotate(120);
		this.penDown();
		this.move(length/3);
		penUp(); this.move(-length/3); penDown();
		
		save_x = this.getX(); save_y = this.getY(); save_angle = this.getAngle(); //saveState
		kochStarRecursion(n-1, length/3);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		
		penUp();
		this.move(length/3);
		this.rotate(-60);
		this.move(length/3);
		penUp(); this.move(-length/3); penDown();
		
		save_x = this.getX(); save_y = this.getY(); save_angle = this.getAngle(); //saveState
		kochStarRecursion(n-1, length/3);
		this.x = save_x; this.y = save_y; this.angle = save_angle; // loadState
		
		
		
		/*this.rotate(90); 
		this.penUp(); 
		this.move(length/3);
		
		this.penDown(); 
		this.setColor(Color.white); 
		this.move(length/3); 
		this.penUp(); 
		this.move(-length/3); 
		this.penDown();
		this.setColor(Color.black); 
		this.rotate(60);
		this.move(length/3);
		this.rotate(-120);
		this.move(length/3);
		this.rotate(60);
		
		this.penUp(); 
		this.move(length/3); 
		this.rotate(-120);*/
	}
}
