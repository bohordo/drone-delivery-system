package com.sucorrientazoadomicilio.drone_deliver_system;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.sucorrientazoadomicilio.drone_deliver_system.model.drone.Drone;
import com.sucorrientazoadomicilio.drone_deliver_system.model.drone.Position;
import com.sucorrientazoadomicilio.drone_deliver_system.service.ReportService;

/**
 * This class contains test of {@link ReportService}
 * 
 * @author Daniel Bohórquez.
 *
 */
public class ReportServiceTest {

	/**
	 * Test the method commandGeneration.
	 */
	@Test
	public void commandGeneration_should_work() {
		// Command generated should be not null.
		assertNotNull(new ReportService().commandGeneration());
		// Command generated shouldn't be empty.
		assertNotEquals("", new ReportService().commandGeneration());
		// Command generated length shouldn't be not zero.
		assertNotEquals(0, new ReportService().commandGeneration().length());
	}

	/**
	 * Test the generateInputFiles method.
	 * 
	 * @throws IOException
	 */
	@Test
	public void generateInputFiles_should_work() throws IOException {
		new ReportService().generateInputFiles();
		for (int i = 0; i < 20; i++) {
			Files.delete(Paths.get("input/in" + new ReportService().fix(i) + ".txt"));
		}
	}

	/**
	 * Test the method orderFileList.
	 * 
	 * @throws IOException
	 */
	@Test
	public void orderFileListTest_should_work() throws IOException {
		new ReportService().orderFileList();
	}

	/**
	 * Test the method generateInputFiles.
	 * 
	 * @throws IOException
	 */
	@Test
	public void fix_should_work() throws IOException {
		assertEquals("01", new ReportService().fix(0));
		assertEquals("02", new ReportService().fix(1));
		assertEquals("11", new ReportService().fix(10));

	}

	@Test
	public void saveReport_should_work() throws IOException {
		String report = "== Reporte de entregas ==\n" + "\n" + "(0,1) dirección Occidente\n" + "\n"
				+ "(-1,1) dirección Occidente\n" + "\n" + "(-2,1) dirección Sur";
		new ReportService().saveReport(report, 20);
		Files.delete(Paths.get("output/out21.txt"));

	}

	@Test
	public void writeReports_should_work() throws IOException {
		String report = "== Reporte de entregas ==\n" + "\n" + "(0,0) dirección Norte\n" + "\n"
				+ "(0,0) dirección Norte\n" + "\n" + "(0,0) dirección Norte\n" + "\n" + "";
		Drone drone = new Drone();
		drone.getPositionHistory().add(new Position());
		drone.getPositionHistory().add(new Position());
		drone.getPositionHistory().add(new Position());
		assertEquals(report, new ReportService().writeReports(drone));
	}

}
