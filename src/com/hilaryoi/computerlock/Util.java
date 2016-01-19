package com.hilaryoi.computerlock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Util {

	public static long getDate() {

		Scanner scan;

		File file = new File(System.getenv("AppData") + "\\bobsoft\\locker\\lock.txt");

		if (!file.exists()) {
			// nothing to do
			return 0;

		}

		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e1) {
			System.err.println("No file.");
			e1.printStackTrace();
			return 0;

		}

		if (!scan.hasNext()) {
			System.err.println("wtf nothing in lock.txt");
			scan.close();

			return 0;

		}

		long date;

		try {
			date = Long.valueOf(scan.next());

		} catch (NumberFormatException e) {
			System.err.println("stop tampering with the lock file");
			return 0;

		} finally {
			scan.close();

		}

		return date;

	}

	public static String getPassword() throws Exception {
		URL website = new URL("https://gist.githubusercontent.com/seawee65/cd55c7be42824a61b9a8/raw");
		URLConnection connection = website.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String s = in.readLine();

		in.close();

		return s;

	}

}
