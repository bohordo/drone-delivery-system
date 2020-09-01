package com.sucorrientazoadomicilio.drone_deliver_system.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import com.sucorrientazoadomicilio.drone_deliver_system.App;
import com.sucorrientazoadomicilio.drone_deliver_system.model.drone.Drone;

/**
 * Service that manages the principal operations of the report.
 * 
 * @author Daniel Bohórquez.
 *
 */
public class ReportService {

	/**
	 * Generate a random command with the characters that the drone could read to
	 * fly to a path.
	 * 
	 * @return random string command.
	 */
	public String commandGeneration() {
		Random random = new Random();
		// Is specified that only the characters A,I and D are valid.
		char[] chars = "AID".toCharArray();
		StringBuilder sb = new StringBuilder();
		// Is added plus one (+1) in the random number to avoid a command with zero
		// characters
		for (int i = 0; i < random.nextInt(10) + 1; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * Generate 20 input files with 3 commands in each file.
	 * 
	 * @throws IOException
	 */
	public void generateInputFiles() throws IOException {
		for (int i = 0; i < App.DRONE_CAPACITY; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 3; j++) {
				String report = new ReportService().commandGeneration() + "\n";
				sb.append(report);
			}
			Files.write(Paths.get("input/in" + fix(i) + ".txt"), sb.toString().getBytes());
		}
	}

	/**
	 * Fill and ArrayList with the string names of the files inside the folder
	 * input.
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> orderFileList() throws IOException {
		return (ArrayList<String>) Files.list(Paths.get("input/")).filter(Files::isRegularFile)
				.filter(s -> s.toString().startsWith("in")).filter(s -> s.toString().endsWith(".txt"))
				.map(p -> p.getFileName().toString()).collect(Collectors.toList());
	}

	/**
	 * Quick "fix" to have the right format name of the input files.
	 * 
	 * @param i
	 * @return i with right format.
	 */
	public String fix(int i) {
		i++;
		if (i <= 9) {
			return "0" + i;
		}
		return "" + i;
	}

	/**
	 * Saves the output file.
	 * 
	 * @param report
	 * @param id
	 * @throws IOException
	 */
	public void saveReport(String report, int id) throws IOException {
		Files.write(Paths.get("output/out" + new ReportService().fix(id) + ".txt"), report.getBytes());
	}

	/**
	 * 
	 * @param drone
	 * @return
	 */
	public String writeReports(Drone drone) {
		StringBuilder bld = new StringBuilder();
		bld.append("== Reporte de entregas ==\n");
		bld.append("\n");
		for (int i = 0; i < App.ORDER_DRONE_CAPACITY; i++) {

			if (drone.getPositionHistory().get(i).getCardinalDirection() == 'X') {
				bld.append("Esta posición de destino y/o la anterior es invalidad porque es fuera del radio del barrio");
				bld.append("\n");
			} else {

				bld.append(
						"(" + drone.getPositionHistory().get(i).getPositionX() + ","
								+ drone.getPositionHistory().get(i).getPositionY() + ") dirección " + new DroneService()
										.nameDirection(drone.getPositionHistory().get(i).getCardinalDirection())
								+ "\n");
			}
			bld.append("\n");
		}
		return bld.toString();
	}

}
