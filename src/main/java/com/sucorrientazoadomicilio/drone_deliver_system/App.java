package com.sucorrientazoadomicilio.drone_deliver_system;

import java.io.IOException;

import com.sucorrientazoadomicilio.drone_deliver_system.service.DroneService;
import com.sucorrientazoadomicilio.drone_deliver_system.service.ReportService;

/**
 * Main of the project.
 * 
 * @author Daniel Bohórquez.
 *
 */
public class App {

	public static final int DRONE_CAPACITY = 20;
	public static final int ORDER_DRONE_CAPACITY = 3;
	static boolean generateInputFiles = true;

	public static void main(String[] args) throws IOException {

		if (generateInputFiles) {
			// Generate the input files
			new ReportService().generateInputFiles();
		}
		// Deploy the drone fleet, ready to deliver the orders.
		new DroneService().deployDroneFleet(new ReportService().orderFileList());
	}
}
