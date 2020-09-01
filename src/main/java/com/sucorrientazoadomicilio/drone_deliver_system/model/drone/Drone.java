package com.sucorrientazoadomicilio.drone_deliver_system.model.drone;

import java.util.ArrayList;

import lombok.Data;

/**
 * Drone object intended to deliver orders, it always have a position and a
 * historical of positions.
 * 
 * @author Daniel Bohórquez.
 *
 */
@Data
public class Drone {

	Position position;
	ArrayList<Position> positionHistory;

	/**
	 * Constructor class.
	 */
	public Drone() {
		position = new Position();
		positionHistory = new ArrayList<>();
	}

	/**
	 * Validate if a given path for deliver the next order is inside the valid radio
	 * of the neighborhood.
	 * 
	 * @param path of the deliver.
	 * @return boolean validation.
	 */
	public boolean deliverPath(String path) {
		Position positionToValidate = flyToPath(path);

		if (positionToValidate.getPositionX() > 11 && -11 < positionToValidate.getPositionX()
				|| positionToValidate.getPositionY() > 11 && -11 < positionToValidate.getPositionY()) {
			return false;
		}
		return true;
	}

	/**
	 * Execute the command of the next path and refresh the new position.
	 * 
	 * @param nextPath.
	 * @return new position.
	 */
	public Position flyToPath(String nextPath) {

		for (int i = 0; i < nextPath.length(); i++) {
			if (nextPath.charAt(i) == 'I' || nextPath.charAt(i) == 'D') {
				position.setCardinalDirection(turnDrone(nextPath.charAt(i)));
			}

			if (nextPath.charAt(i) == 'A' && position.getCardinalDirection() == 'N') {
				position.setPositionY(position.getPositionY() + 1);

			}
			if (nextPath.charAt(i) == 'A' && position.getCardinalDirection() == 'S') {
				position.setPositionY(position.getPositionY() - 1);

			}
			if (nextPath.charAt(i) == 'A' && position.getCardinalDirection() == 'E') {
				position.setPositionX(position.getPositionX() + 1);

			}
			if (nextPath.charAt(i) == 'A' && position.getCardinalDirection() == 'W') {
				position.setPositionX(position.getPositionX() - 1);

			}

		}
		Position temp = new Position();
		temp.setCardinalDirection(position.getCardinalDirection());
		temp.setPositionX(position.getPositionX());
		temp.setPositionY(position.getPositionY());
		positionHistory.add(temp);
		return position;
	}

	/**
	 * 
	 * @param degreeTurn
	 * @return
	 */
	public char turnDrone(char degreeTurn) {

		if (position.getCardinalDirection() == 'N' && degreeTurn == 'I') {
			return 'W';
		}
		if (position.getCardinalDirection() == 'N' && degreeTurn == 'D') {
			return 'E';
		}
		if (position.getCardinalDirection() == 'S' && degreeTurn == 'I') {
			return 'E';
		}
		if (position.getCardinalDirection() == 'S' && degreeTurn == 'D') {
			return 'W';
		}
		if (position.getCardinalDirection() == 'E' && degreeTurn == 'I') {
			return 'N';
		}
		if (position.getCardinalDirection() == 'E' && degreeTurn == 'D') {
			return 'S';
		}
		if (position.getCardinalDirection() == 'W' && degreeTurn == 'I') {
			return 'S';
		}
		if (position.getCardinalDirection() == 'W' && degreeTurn == 'D') {
			return 'N';
		}
		return position.getCardinalDirection();
	}
}
