package thread;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lejos.hardware.Button;

public class legoDB extends Thread {
	static DataExchange DEdb;

	public legoDB(DataExchange DE) {
		DEdb = DE;
	}

	URL url = null;
	HttpURLConnection conn = null;
	InputStreamReader isr = null;
	BufferedReader br = null;

	public void run() {
		String s = null;
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				url = new URL("http://192.168.0.100:8080/rest/legoservice/par");
				conn = (HttpURLConnection) url.openConnection();
				System.out.println(conn.toString()); // Tulostaa vain URLin
				InputStream is = null;
				try {
					is = conn.getInputStream();
				} catch (Exception e) {
					System.out.println("Exception conn.getInputSteam()");
					e.printStackTrace();
					System.out.println("Cannot get InputStream!");
				}
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				String input = br.readLine();
				String[] parts = input.split(" ");
				String motor1 = parts[0];
				String motor2 = parts[1];
				while ((s = br.readLine()) != null) {
					System.out.println(s);
				}
				DEdb.setMotor1Speed(parts[0]);
				DEdb.setMotor2Speed(parts[1]);

				System.out.println(DEdb.getMotor1Speed());
				System.out.println(DEdb.getMotor2Speed());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Some problem!");
			}
		}
	}

}
