package thread;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class ObstacleDetector extends Thread {
	private DataExchange DEObj;
	private EV3UltrasonicSensor us;
	private final int securityDistance = 20;
	private static LineFollowerV2 LFObj;
	public int lap;

	public ObstacleDetector(DataExchange DE) {
		DEObj = DE;
		us = new EV3UltrasonicSensor(SensorPort.S1);
	}

	public void run() {
		SampleProvider sp = us.getDistanceMode();
		float[] distanceSample = new float[sp.sampleSize()];

		while (true) {
			sp.fetchSample(distanceSample, 0);
			float distance = distanceSample[0] * 100;
			if (distance > securityDistance && !DEObj.getDodge()) {
				DEObj.setCMD(1);
			} else if (distance < securityDistance) {
				DEObj.setCMD(0);
				DEObj.setDodge(true);
			}
		}
	}
}