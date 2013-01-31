package edu.kit.curiosity.behaviors;

import edu.kit.curiosity.Settings;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class SensorHeadPosition implements Behavior {

	private boolean suppressed = false;

	@Override
	public boolean takeControl() {

		return (Motor.A.getTachoCount() > 5 || Motor.A.getTachoCount() < -5);
	}

	@Override
	public void action() {
		suppressed = false;
		Motor.A.rotateTo(Settings.motorAAngle);
		while (Motor.A.isMoving() && !suppressed) {
			Thread.yield();
		}
		Motor.A.flt(true);
	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
