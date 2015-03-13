/* (c) 2015 Marian Longa. http://marianlonga.com. */

package com.marianlonga.turtle;

import java.applet.*;
import java.awt.*;

public class Main extends Applet {
	
	private static final long serialVersionUID = 1L;
	Turtle t;
	static int
		width	= 800,
		height	= 800;
	
	public void init() {
		setSize(width, height);
		this.t = new Turtle(width/2, height/2);
		setBackground(Color.white);
	}
	
	public void paint(Graphics g) {
		t.setGraphics(g);
		
		t.setPos(100, 400); t.setAngle(0); t.setColor(new Color(255, 100, 100)); t.steps(8, 20);
		t.setPos(20, 370); t.setAngle(0); t.setColor(new Color(0, 200, 200)); t.steps(30, 5);
		
		t.setPos(400, 400); t.setAngle(0); t.tree(8, 100, 50, 1.3);
		t.setPos(300, 400); t.setAngle(0); t.tree(8, 40, 30, 1.3);
		t.setPos(700, 400); t.setAngle(0); t.tree(8, 60, 15, 1.2);
		
		t.setPos(400, 500); t.setAngle(0); t.setColor(new Color(100, 100, 255)); t.triangle(6, 300);
		t.setPos(40, 150); t.setAngle(0); t.setColor(new Color(200, 200, 200)); t.triangle(4, 150);
		
		t.setPos(50, 500); t.setAngle(0); t.kochStar(7, 300);
		t.setPos(500, 600); t.setAngle(0); t.kochStar(4, 100);
	}

}
