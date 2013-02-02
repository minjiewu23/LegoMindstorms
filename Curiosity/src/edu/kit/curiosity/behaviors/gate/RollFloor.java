package edu.kit.curiosity.behaviors.gate;

import edu.kit.curiosity.Settings;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

/**
 * The class {@code RollFloor} describes the reaction to hits during the RollFloor.
 * @author Team Curiosity
 *
 */
public class RollFloor implements Behavior {

	private boolean suppressed = false;

	private DifferentialPilot pilot;
	
	/**
	 * Constructs a new RollFloor Behavior
	 */
	public RollFloor() {
		pilot = Settings.PILOT;
	}
	
	/**
	 * The Behavior takes control
	 * if  one of the {@link TouchSensor} is pressed.
	 */
	@Override
	public boolean takeControl() {
		return (Settings.TOUCH_R.isPressed() || Settings.TOUCH_L.isPressed());
	}

	/**
	 * Rotates to the left if right TouchSensor is pressed,
	 * to the right if left.
	 */
	@Override
	public void action() {
		suppressed = false;
		if (Settings.TOUCH_R.isPressed()) {
			pilot.travel(-5);
			pilot.rotate(70);
		} else if (Settings.TOUCH_L.isPressed()) {
			pilot.travel(-5);
			pilot.rotate(-70);
		}
		while (pilot.isMoving() && !suppressed) {
			Thread.yield();
		}
		pilot.stop();

	}

	/**
	 * Initiates the cleanup when this Behavior is suppressed
	 */
	@Override
	public void suppress() {
		suppressed = true;

	}

}