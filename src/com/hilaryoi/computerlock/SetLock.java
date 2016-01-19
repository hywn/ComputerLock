package com.hilaryoi.computerlock;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class SetLock extends JFrame {

	private static final long serialVersionUID = 1L;

	JButton button;

	public SetLock() {

		this.setLayout(new FlowLayout());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(400, 400);

		JSpinner day = new JSpinner(new SpinnerDateModel());

		day.setEditor(new JSpinner.DateEditor(day, "HH:mm:ss 'at' yyyy.MM.dd"));

		button = new JButton("K.");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					save(((Date) day.getValue()).getTime());
				} catch (IOException e1) {
					System.err.println("uh oh ~");
					e1.printStackTrace();
				}

				Frame f = new Frame();
				f.setVisible(true);

				setVisible(false);

			}

		});

		add(new JLabel("set a lock until: "));
		add(day);
		add(button);

	}

	public void save(long date) throws IOException {

		File file = new File(System.getenv("AppData") + "\\bobsoft\\locker\\lock.txt");

		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();

		}

		PrintWriter w = new PrintWriter(file);

		w.write(String.valueOf(date));
		w.close();

	}

}
