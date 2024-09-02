import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The TreeDriver class provides a user interface. It perform various operations
 * like loading a tree, beginning a help session, traversing the tree, and
 * exiting the program.
 * 
 * @author Shiv Kanani 
 * 		   SBU ID: 115171965 
 * 		   Homework #4 for CSE 214, Summer 2023
 */
public class TreeDriver {

	private static boolean treeCreated = false;

	/**
	 * The main method for running the TreeDriver program. Prompts the user to
	 * select a menu option and performs the corresponding operation.
	 * 
	 * @param args command-line arguments (not used in this program)
	 */

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		Tree newTree = new Tree();

		try {
			while (flag) {
				System.out.println("L - Load a Tree.");
				System.out.println("H - Begin a Help Session.");
				System.out.println("T - Traverse the Tree in preorder.");
				System.out.println("Q - Exit the program");
				System.out.println("");
				System.out.println("Choice> ");
				String option = scanner.nextLine();

				switch (option.toUpperCase()) {
				case "L":
					newTree = parseFile(scanner);
					if (treeCreated)
						System.out.println("Tree created successfully! ");
					else
						System.out.println("Failed to create the tree");
					System.out.println("");
					break;

				case "H":
					if (newTree.getRoot() == null) {
						System.out.println("The tree is empty!");
					} else {
						System.out.println("Help session starting.....");
						newTree.beginSession();
					}
					System.out.println("");
					break;

				case "T":
					System.out.println("Traversing the tree in preorder:");
					newTree.preOrder();
					System.out.println("");
					break;

				case "Q":
					System.out.println("Thank you for using our automated help services!");
					flag = false;
					System.out.println("");
					break;

				default:
					if (!option.equalsIgnoreCase("Q")) {
						System.out.println("Invalid option");
					}
					break;
				}

			}
		} catch (Exception e) {
			System.out.println("An unexpected error occurred. Please check your inputs are correct!");

		} finally {
			scanner.close();
		}
	}

	/**
	 * Parses a file and constructs a Tree object.
	 *
	 * @param scanner The scanner used for user input
	 * @return A new Tree object constructed from the file input
	 * @throws IOException If an error occurs while reading the file
	 */

	private static Tree parseFile(Scanner scanner) throws IOException {

		System.out.println("Enter the file name> ");
		String name = scanner.nextLine();
		Tree newTree = new Tree();

		try (Scanner fileScanner = new Scanner(new File(name))) {
			String label = "";
			int count = 0;

			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine().trim();

				if (line.isEmpty()) {
					continue;
				}

				String[] values = new String[3];

				if (newTree.getRoot() == null) {
					values[0] = line;
					values[2] = fileScanner.nextLine().trim(); // Interchanged prompt and message
					values[1] = fileScanner.nextLine().trim(); // Interchanged prompt and message

					newTree.addNode(values[0], values[1], values[2], "");
				} else {
					switch (count) {
					case 0:
						label = line.substring(0, line.length() - 1).trim();
						count += line.charAt(line.length() - 1) - '0';
						values[0] = fileScanner.nextLine().trim();
						break;

					default:
						values[0] = line;

					}
					values[2] = fileScanner.nextLine().trim(); // Interchanged prompt and message
					values[1] = fileScanner.nextLine().trim(); // Interchanged prompt and message
					newTree.addNode(values[0], values[1], values[2], label);
					count--;
				}
			}
			treeCreated = true;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		return newTree;
	}

}
