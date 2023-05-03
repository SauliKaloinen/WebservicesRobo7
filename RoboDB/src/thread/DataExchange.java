package thread;

public class DataExchange {

	// This class exchanges data between the threads.
	private boolean obstacleDetected = false;
	private int CMD = 1; // follow line
	private boolean dodge = false;
	
	private int motor1Speed;
	private int motor2Speed;

	public int getMotor1Speed() {
		return motor1Speed;
	}

	public void setMotor1Speed(String motor1Speed) {
		this.motor1Speed = Integer.parseInt(motor1Speed);
	}

	public int getMotor2Speed() {
		return motor2Speed;
	}

	public void setMotor2Speed(String motor2Speed) {
		this.motor2Speed = Integer.parseInt(motor2Speed);
	}

	public DataExchange() {

	}

	public void setObstacleDetected(boolean status) {
		obstacleDetected = status;
	}

	public boolean getObstacleDetected() {
		return obstacleDetected;
	}

	public void setCMD(int command) {
		CMD = command;
	}

	public int getCMD() {
		return CMD;
	}

	public boolean getDodge() {
		return this.dodge;
	}

	public void setDodge(boolean dodge) {
		this.dodge = dodge;
	}

}
