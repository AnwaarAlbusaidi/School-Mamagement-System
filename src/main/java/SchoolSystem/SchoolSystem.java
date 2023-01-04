package SchoolSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class SchoolSystem {
	public static void main(String[] args) throws IOException {

		// Declaration
		Scanner scan = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(new FileWriter("Student.csv", true));
		HashMap<Integer, String[]> student = new HashMap<Integer, String[]>();
		Integer key;
		// print menu to the user
		System.out.println("*********************************************************");
		System.out.println("Welcomt to School Management System ");
		System.out.println("Select an action ");
		System.out.println("1. Register student ");
		System.out.println("2. Search for a students ");
		System.out.println("3. List all Student information ");
		System.out.println("4. update student information.");
		System.out.println("5. Delete student information.");
		System.out.println("*********************************************************");
		int userChoice = Integer.parseInt(scan.nextLine()); // get user choice
		// process to register student
		if (userChoice == 1) {
			System.out.println("You can register a Student; ");
			key = getnewStudentID("Student.csv"); // call function to get unique ID
			System.out.println("Enter Student Name : ");
			String studentName = scan.nextLine(); // to get student Name
			System.out.println("Enter Student Email : ");
			String StudentEmail = scan.nextLine(); // to get student Email
			writer.write((key.toString()));
			writer.append(",");
			writer.write(studentName);
			writer.append(",");
			writer.write(StudentEmail);
			writer.append("\n");
			System.out.println("Student are registerd scssfully");
			writer.close();
			scan.close();
		}
		// process Search for a students
		else if (userChoice == 2) {
			System.out.println("Enter the student ID : ");
			int ID = Integer.parseInt(scan.nextLine());
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
		}
		// process to update student information
		else if (userChoice == 4) {// update student information
			System.out.println("Enter the student ID to be updated : ");
			Integer ID = Integer.parseInt(scan.nextLine());
			store(student);
			if (student.containsKey(ID)) {
				System.out.println("Enter the name to be updated : ");
				String name = scan.nextLine();
				System.out.println("Enter the new email to be updated : ");
				String newEmail = scan.nextLine();
				String[] newStudent = { ID.toString(), name, newEmail };
				student.put(ID, newStudent);
				System.out.println("update Done");
				writeToFile(student);
			}
			scan.close();
		}
		// process to Delete student information
		else if (userChoice == 5) {
			System.out.println("Enter the student ID to be deleted : ");
			int ID = Integer.parseInt(scan.nextLine());
			store(student);
			if (student.containsKey(ID)) {
				student.remove(ID);
				System.out.println("Deleted done");
				writeToFile(student);
			}
		} else {
			System.out.println("wrong selection");

		}
		scan.close();
	}// End of main class

	/**
	 * This Method will count how many lines in a file and return unique id
	 * 
	 * @param fileLocation (The name of the file)
	 * @return return unique id
	 * @throws IOException
	 */
	public static Integer getnewStudentID(String fileLocation) throws IOException {
		int newStudentID = 100;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
			String line; // count how many lines we have in a file
			while ((line = reader.readLine()) != null) {
				newStudentID++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file not found" + e);
		}
		return newStudentID;
	}

	/**
	 * This Method will read the file and store and the data in a hash map
	 * 
	 * @param info(Info is a hash map)
	 * @throws IOException
	 */
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

	/**
	 * This Method will write data from a hash map to a file when update or delete
	 * done
	 * @param info (Info is a hash map)
	 * @throws IOException
	 */
	public static void writeToFile(HashMap<Integer, String[]> info) throws IOException {
		PrintWriter writer = new PrintWriter("Student.csv");
		for (Entry<Integer, String[]> entry : info.entrySet()) {
			String[] values = entry.getValue();
			for (int i = 0; i < values.length; i++) {
				writer.write(values[i]);
				writer.append(",");
			}
			writer.append("\n");
		}
		writer.close();
	}
	
}// End of SchoolSystem
