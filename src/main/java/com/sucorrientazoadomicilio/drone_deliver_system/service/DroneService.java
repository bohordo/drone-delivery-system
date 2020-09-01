package com.sucorrientazoadomicilio.drone_deliver_system.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.sucorrientazoadomicilio.drone_deliver_system.App;
import com.sucorrientazoadomicilio.drone_deliver_system.model.drone.Drone;

/**
 * Service that manages the principal operations of the drones.
 * 
 * @author Daniel Bohórquez.
 *
 */
public class DroneService {

	/**
	 * Deploy the drones, max drones if are more files.
	 * 
	 * 
	 * @param ArrayList with the name of the files to process.
	 * @throws IOException
	 */
	public void deployDroneFleet(ArrayList<String> fileNames) throws IOException {
		Collections.sort(fileNames);
		if (App.DRONE_CAPACITY <= fileNames.size()) {
			for (int i = 0; i < App.DRONE_CAPACITY; i++) {
				assingOrder(fileNames.get(i), i);
			}
		}

		else {
			for (int i = 0; i < fileNames.size(); i++) {
				assingOrder(fileNames.get(i), i);
			}
		}

	}

	/**
	 * Read the commands inside an input file.
	 * 
	 * @param file name.
	 * @param id   of the process.
	 * @throws IOException
	 */
	public void assingOrder(String fileName, int id) throws IOException {
		Drone drone = new Drone();
		ArrayList<String> orders = new ArrayList<>();
		File file = new File("input/" + fileName);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				orders.add(line);
			}
		}
		for (int i = 0; i < App.ORDER_DRONE_CAPACITY; i++) {
			drone.flyToPath(orders.get(i));

		}
		new ReportService().saveReport(new ReportService().writeReports(drone), id);
	}

	/**
	 * Validates a cardinal direction char and return it names complete.
	 * 
	 * @param char cardinalDirection
	 * @return cardinal direction name.
	 */
	public String nameDirection(char cardinalDirection) {

		switch (cardinalDirection) {

		case 'N':
			return "Norte";
		case 'S':
			return "Sur";
		case 'W':
			return "Occidente";
		case 'E':
			return "Oriente";
		default:
			return cardinalDirection + "";
		}
	}

}
