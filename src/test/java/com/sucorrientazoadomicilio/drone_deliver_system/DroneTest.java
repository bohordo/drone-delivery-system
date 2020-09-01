package com.sucorrientazoadomicilio.drone_deliver_system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.sucorrientazoadomicilio.drone_deliver_system.model.drone.Drone;
import com.sucorrientazoadomicilio.drone_deliver_system.model.drone.Position;

/**
 * This class contains test of {@link Drone} and {@link Position}.
 * 
 * @author Daniel Bohórquez.
 *
 */

public class DroneTest {

	/**
	 * Test that the deliver path in within the range.
	 */
	@Test
	public void deliverPath_should_work() {
		assertTrue(new Drone().deliverPath("AAAAIAA"));
		assertFalse(new Drone().deliverPath("AAAAAAAAAAAA"));
		assertFalse(new Drone().deliverPath("AAAAAAAAAAAAAAAA"));
	}

	/**
	 * Test the method flyToPath.
	 */
	@Test
	public void flyToPath_should_work() {
		assertEquals('N', new Drone().flyToPath("IIII").getCardinalDirection());
		assertEquals('E', new Drone().flyToPath("III").getCardinalDirection());
		assertEquals('S', new Drone().flyToPath("II").getCardinalDirection());
		assertEquals('W', new Drone().flyToPath("I").getCardinalDirection());
		assertEquals(0, new Drone().flyToPath("I").getPositionX());
		assertEquals(1, new Drone().flyToPath("DA").getPositionX());
		assertEquals(-1, new Drone().flyToPath("IA").getPositionX());
		assertEquals(1, new Drone().flyToPath("A").getPositionY());
		assertEquals(-1, new Drone().flyToPath("IIA").getPositionY());

		ArrayList<String> paths = new ArrayList<>();
		paths.add("IDAA");
		paths.add("IIDA");
		paths.add("AAADA");
		Drone drone = new Drone();
		for (int i = 0; i < paths.size(); i++) {
			drone.flyToPath(paths.get(i));
		}
		ArrayList<Position> positions = new ArrayList<>();
		Position pos1 = new Position();
		pos1.setPositionY(2);
		Position pos2 = new Position();
		pos2.setCardinalDirection('W');
		pos2.setPositionX(-1);
		pos2.setPositionY(2);
		positions.add(pos1);
		positions.add(pos2);
		Position pos3 = new Position();
		pos3.setPositionX(-4);
		pos3.setPositionY(3);
		positions.add(pos3);
		assertEquals(positions, drone.getPositionHistory());
	}

	/**
	 * Test the method turnDrone.
	 */
	@Test
	public void turnDrone_should_work() {
		Drone drone = new Drone();
		assertEquals('W', drone.turnDrone('I'));
		assertEquals('E', drone.turnDrone('D'));
		drone.getPosition().setCardinalDirection('S');
		assertEquals('E', drone.turnDrone('I'));
		assertEquals('W', drone.turnDrone('D'));
		drone.getPosition().setCardinalDirection('E');
		assertEquals('N', drone.turnDrone('I'));
		assertEquals('S', drone.turnDrone('D'));
		drone.getPosition().setCardinalDirection('W');
		assertEquals('S', drone.turnDrone('I'));
		assertEquals('N', drone.turnDrone('D'));
	}

	/**
	 * Test the initial position of the drone: north at 0,0 and the historical
	 * position.
	 */
	@Test
	public void droneCreation_should_work() {
		assertEquals('N', new Drone().getPosition().getCardinalDirection());
		assertEquals(0, new Drone().getPosition().getPositionX());
		assertEquals(0, new Drone().getPosition().getPositionY());
		assertNotNull(new Drone().getPositionHistory());
		assertEquals(new ArrayList<Position>(), new Drone().getPositionHistory());

	}

	/**
	 * Test the initial position, North at 0,0.
	 */
	@Test
	public void positionCreationTest_should_work() {
		assertEquals('N', new Position().getCardinalDirection());
		assertEquals(0, new Position().getPositionX());
		assertEquals(0, new Position().getPositionY());

	}
}
