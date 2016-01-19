package com.hilaryoi.computerlock;

import java.io.IOException;

public class Run {

	public static void main(String[] args) throws IOException {

		if (args.length != 0) {

			// its being run via cmd
			if (Util.getDate() > System.currentTimeMillis()) {
				Frame f = new Frame();
				f.setVisible(true);

			}

			return;

		}

		SetLock s = new SetLock();

		s.setVisible(true);

	}

}
