package thread;

import java.io.File;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class LineFollowerV2 extends Thread {
	DataExchange DEObj;

	private int lap;
	private EV3ColorSensor cs;
	private EV3UltrasonicSensor us;

	public LineFollowerV2(DataExchange DE) {
		DEObj = DE;
		cs = new EV3ColorSensor(SensorPort.S3);

		float redSample[];
		SensorMode redMode = cs.getRedMode();
		redSample = new float[cs.sampleSize()];
	}

	// GITHUB BRANCH TEST.

	public void run() {
		float lower = 0.15f;
		float upper = 0.55f;
		float redSample[];
		SensorMode redMode = cs.getRedMode();
		redSample = new float[cs.sampleSize()];
		RegulatedMotor m1 = new EV3LargeRegulatedMotor(MotorPort.C);
		RegulatedMotor m2 = new EV3LargeRegulatedMotor(MotorPort.B);

		while (true) {
			redMode.fetchSample(redSample, 0);
			LCD.clear();
			LCD.drawString(String.valueOf(redSample[0]), 1, 3);
			if (DEObj.getCMD() == 1) {
				if (lower <= redSample[0] && redSample[0] <= upper) {
					m1.setSpeed(DEObj.getMotor1Speed());
					m1.forward();
					m2.setSpeed(DEObj.getMotor2Speed());
					m2.forward();

				} else if (redSample[0] < lower) {
					m1.setSpeed(DEObj.getMotor2Speed());
					m1.forward();
					m2.setSpeed(DEObj.getMotor1Speed());
					m2.forward();
				} else if (redSample[0] > upper) {
					m1.setSpeed(DEObj.getMotor1Speed());
					m1.forward();
					m2.setSpeed(DEObj.getMotor2Speed());
					m2.forward();
				}
				LCD.refresh();
			}

			// program shut down + end dance after 1 full round.
			else if (lap == 1) {
				m1.stop();
				m2.stop();
				Delay.msDelay(1000);
				LCD.clear();
				LCD.drawString("Track complete!.", 0, 1);
				m1.setSpeed(200);
				m1.backward();
				m2.setSpeed(200);
				m2.backward();
				Delay.msDelay(1000);
				m1.setSpeed(250);
				m1.forward();
				m2.setSpeed(250);
				m2.backward();
				Sound.playSample(new File("Biisi.wav"), Sound.VOL_MAX);
				System.exit(0);

			}

			else {
				// obstacle dodge.
				lap++;
				LCD.drawString("Objects found: " + lap, 0, 1);
				Sound.beep();
				LCD.refresh();
				m1.setSpeed(100);
				m2.stop();
				m1.forward();
				Delay.msDelay(1800);
				m1.setSpeed(150);
				m1.forward();
				m2.setSpeed(225);
				m2.forward();
				Delay.msDelay(4500);
				m1.setSpeed(110);
				m1.forward();
				m2.setSpeed(90);
				m2.forward();
				Delay.msDelay(2750);
				DEObj.setDodge(false);
				Sound.beepSequence();
			}
		}
	}
}
