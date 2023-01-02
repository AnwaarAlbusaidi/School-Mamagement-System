package SchoolSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class SchoolSystem {
	private static final String Sysetm = null;

	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(new FileWriter("Student.csv", true));
		HashMap<Integer, String[]> student = new HashMap<Integer, String[]>();
		String[] StudentInf = new String[2];
		Integer key;
		System.out.println("*********************************************************");
		System.out.println("Welcomt to School Management System ");
		System.out.println("Select an action ");
		System.out.println("1. Register student ");
		System.out.println("2. Search for a students ");
		System.out.println("3. Retain Student information ");
		System.out.println("*********************************************************");
		int userChoice = scan.nextInt();

		if (userChoice == 1) {
			System.out.println("You can register a Student; ");
			key = getnewStudentID("Student.csv");
			System.out.println("Enter Student Name : ");
			String studentName = scan.next(); // to get student Name
			System.out.println("Enter Student Email : ");
			String StudentEmail = scan.next(); // to get student Email
			writer.write((key.toString()));
			writer.append(",");
			writer.write(studentName);
			writer.append(",");
			writer.write(StudentEmail);
			writer.append("\n");
			System.out.println("Student are registerd scssfully");
			writer.close();
			scan.close();
		} else if (userChoice == 2) {
			System.out.println("Enter the student ID : ");
			int ID = scan.nextInt();
			store(student);
			for (Entry<Integer, String[]> entry : student.entrySet())
				if (entry.getKey() == ID)
					System.out.println(entry.getKey() + " => " + Arrays.toString(entry.getValue()));
			scan.close();
		} else if (userChoice == 3) {
			Scanner textFile = new Scanner(new File("Student.csv"));
			System.out.println("*********************************************************");
			System.out.println("                Student Inforamtion                          ");
			while (textFile.hasNextLine()) {
				System.out.println(textFile.nextLine());
			}
			System.out.println("*********************************************************");
			textFile.close();
		} else {
			System.out.println("wrong selection");

		}
		scan.close();
	}// End of main class

	public static Integer getnewStudentID(String fileLocation) throws IOException {
		int newStudentID = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
			String line;
			while ((line = reader.readLine()) != null) {
				newStudentID++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file not found" + e);
		}
		return newStudentID + 1;
	}

	public static void store(HashMap<Integer, String[]> info) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Student.csv"));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] box = line.split(",");
				info.put(Integer.parseInt(box[0]), box);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file not found" + e);
		}
	}
}// End of SchoolSystem
