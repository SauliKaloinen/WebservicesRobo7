package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.ws.rs.Path;

@Entity
public class Parameters {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int motor1Speed;
	private int motor2Speed;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMotor1Speed() {
		return motor1Speed;
	}
	public void setMotor1Speed(int motor1Speed) {
		this.motor1Speed = motor1Speed;
	}
	public int getMotor2Speed() {
		return motor2Speed;
	}
	public void setMotor2Speed(int motor2Speed) {
		this.motor2Speed = motor2Speed;
	}
}
