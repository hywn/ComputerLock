package com.hilaryoi.computerlock;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Annoy extends JFrame {

	private static final long serialVersionUID = 1L;

	static Random rand = new Random();
	
	public Annoy() {
		
		this.setLocation(rand.nextInt(1920), rand.nextInt(1080));
		
		add(new JLabel("lol"));
		this.pack();

	}

}
