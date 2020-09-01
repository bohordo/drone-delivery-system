package com.sucorrientazoadomicilio.drone_deliver_system;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

import com.sucorrientazoadomicilio.drone_deliver_system.service.DroneService;
import com.sucorrientazoadomicilio.drone_deliver_system.service.ReportService;

/**
 * This class contains the test of {@link DroneService}.
 * 
 * @author Daniel Bohórquez.
 *
 */
public class DroneServiceTest {

	/**
	 * Test the deployDroneFleet method.
	 * 
	 * @throws IOException
	 */
	@Test
	public void deployDroneFleet_more_drones_should_work() throws IOException {
		// Test when are more drones than files
		String info = "AAA\nDAA\nIAA";
		Files.write(Paths.get("input/inTEST.txt"), info.getBytes());
		ArrayList<String> fileNames = new ArrayList<>();
		fileNames.add("inTEST.txt");
		new DroneService().deployDroneFleet(fileNames);
		fileNames.remove(0);
		Files.delete(Paths.get("input/inTEST.txt"));

	}

	@Test
	public void deployDroneFleet_less_drones_should_work() throws IOException {
		ArrayList<String> fileNames = new ArrayList<>();
		// Test when are more files than drones
		for (int i = 0; i < 30; i++) {
			fileNames.add("inTest" + new ReportService().fix(i) + ".txt");
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 3; j++) {
				sb.append(new ReportService().commandGeneration() + "\n");
			}
			Files.write(Paths.get("input/inTest" + new ReportService().fix(i) + ".txt"), sb.toString().getBytes());
		}
		new DroneService().deployDroneFleet(fileNames);
		for (int i = 0; i < 30; i++) {
			Files.delete(Paths.get("input/inTest" + new ReportService().fix(i) + ".txt"));
		}

	}

	/**
	 * Test the assingOrder method.
	 * 
	 * @throws IOException
	 */
	@Test
	public void assingOrder_should_work() throws IOException {
		String info1 = "AAA\nAA\nAA";
		Files.write(Paths.get("input/inTEST01.txt"), info1.getBytes());
		new DroneService().assingOrder("inTEST01.txt", 1);
		Files.delete(Paths.get("input/inTEST01.txt"));
		String info2 = "AAAAAAAAAAAA\nAA\nAA";
		Files.write(Paths.get("input/inTEST.txt"), info2.getBytes());
		new DroneService().assingOrder("inTEST.txt", 1);
		Files.delete(Paths.get("input/inTEST.txt"));
	}

	/**
	 * Test the nameDirection method.
	 */
	@Test
	public void nameDirection_should_work() {
		assertEquals("Norte", new DroneService().nameDirection('N'));
		assertEquals("Sur", new DroneService().nameDirection('S'));
		assertEquals("Occidente", new DroneService().nameDirection('W'));
		assertEquals("Oriente", new DroneService().nameDirection('E'));
	}
}
