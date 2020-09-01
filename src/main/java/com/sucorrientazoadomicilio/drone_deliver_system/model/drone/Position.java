package com.sucorrientazoadomicilio.drone_deliver_system.model.drone;

import lombok.Data;

/**
 * A Position object has a position on Y, X, and a char cardinal direction: N,S,E,W.
 * 
 * @author Daniel Bohórquez.
 *
 */
@Data
public class Position {

	int positionX;
	int positionY;
	char cardinalDirection;

	/**
	 * Constructor class. 
	 */
	public Position() {
		positionX = 0;
		positionY = 0;
		cardinalDirection = 'N';
	}
}
