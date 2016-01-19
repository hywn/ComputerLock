package com.hilaryoi.computerlock;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Frame extends JFrame {

	static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss 'at' yyyy/MM/dd");

	private static final long serialVersionUID = 1L;

	public Frame() {

		// check if the time is up

		long date = Util.getDate();

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		GraphicsDevice gd = ge.getScreenDevices()[0];

		gd.setFullScreenWindow(this);

		// this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		this.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowLostFocus(WindowEvent arg0) {
				// shutdown computer

				try {
					Runtime runtime = Runtime.getRuntime();
					runtime.exec("shutdown -s -f -t 0");
					System.exit(0);

				} catch (IOException e) {
					System.err.println("shoot - could not shut down");
					e.printStackTrace();

				}

			}

		});

		this.setBackground(Color.BLACK);

		this.setLayout(new FlowLayout());

		JLabel status = new JLabel("status");

		JTextField pass = new JTextField("enter emergency password", 30);

		pass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (pass.getText().equals(Util.getPassword())) {

						System.exit(0);

					} else {
						status.setText("sorry wrong password");

					}

				} catch (Exception e1) {
					System.err.println("no can get password");
					status.setText("can't fetch password");
					e1.printStackTrace();
					return;

				}

			}

		});

		JButton unlock = new JButton("it's time!");

		unlock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (date < System.currentTimeMillis()) {
					// unlock
					System.exit(0);

				} else {
					status.setText("you still have a long way to go bud");

				}

			}

		});

		this.add(new JLabel("you will be able to use the computer " + format.format(date) + "\n"));
		this.add(pass);
		this.add(unlock);
		this.add(status);

		this.setLocationRelativeTo(null);

		this.requestFocus();

		this.repaint();

	}
}
