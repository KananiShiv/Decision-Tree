import java.util.Scanner;

/**
 * The Tree class is used to create a tree structure composed of TreeNode objects. It
 * provides methods to manipulate and traverse the tree, as well as to interact
 * with the user during a session.
 * 
 * @author Shiv Kanani 
 * 		   SBU ID: 115171965 
 * 		   Homework #4 for CSE 214, Summer 2023
 */
public class Tree {

	private TreeNode root; // The root node of the tree
	private boolean sessionExitFlag = false;

	/**
	 * Constructs a Tree object with a null root.
	 */
	public Tree() {
		root = null;
	}

	/**
	 * Adds a new TreeNode to the tree with the specified label, prompt, message,
	 * and parent label.
	 *
	 * @param label       The label of the new TreeNode
	 * @param prompt      The prompt associated with the new TreeNode
	 * @param message     The message associated with the new TreeNode
	 * @param parentLabel The label of the parent TreeNode to which the new TreeNode
	 *                    is added
	 * @return True if the node was added successfully, false otherwise
	 */
	public boolean addNode(String label, String prompt, String message, String parentLabel) {
		// Create a new TreeNode
		TreeNode newNode = new TreeNode(label, prompt, message);

		if (root == null) {
			root = newNode;
			return true;
		}

		// Get reference to the parent node
		TreeNode parentNode = getNodeReference(parentLabel);

		if (parentNode == null) {
			return false;
		}

		// Add the new node to an available child slot
		if (parentNode.getLeftNode() == null) {
			parentNode.setLeftNode(newNode);
		} else if (parentNode.getMiddleNode() == null) {
			parentNode.setMiddleNode(newNode);
		} else if (parentNode.getRightNode() == null) {
			parentNode.setRightNode(newNode);
		} else {
			System.out.println("Cannot add more children. Node: " + newNode.getLabel());
			return false;
		}

		return true;
	}

	/**
	 * Retrieves the reference to a TreeNode with the specified label.
	 *
	 * @param label The label of the TreeNode to retrieve
	 * @return The reference to the TreeNode with the given label, or null if not
	 *         found
	 */
	public TreeNode getNodeReference(String label) {
		return getNodeReferenceHelper(root, label);
	}

	/**
	 * Helper function to recursively find a TreeNode reference with a given label.
	 *
	 * @param currentNode The current TreeNode being checked
	 * @param label       The label of the TreeNode to find
	 * @return The reference to the TreeNode with the given label, or null if not
	 *         found
	 */
	private TreeNode getNodeReferenceHelper(TreeNode currentNode, String label) {
		if (currentNode == null) {
			return null;
		}

		TreeNode foundNode = getNodeReferenceHelper(currentNode.getLeftNode(), label);
		if (foundNode == null) {
			foundNode = getNodeReferenceHelper(currentNode.getMiddleNode(), label);
		}
		if (foundNode == null) {
			foundNode = getNodeReferenceHelper(currentNode.getRightNode(), label);
		}

		if (currentNode.getLabel().equals(label)) {
			return currentNode;
		}

		return foundNode;
	}

	/**
	 * Traverses the tree in pre-order and prints the label, prompt, and message of
	 * each node.
	 */
	public void preOrder() {
		preOrderHelper(root);
	}

	/**
	 * Helper function to recursively perform a pre-order traversal and print node
	 * information.
	 *
	 * @param root The current root node being traversed
	 */
	public void preOrderHelper(TreeNode root) {
		if (root == null)
			return;

		System.out.println("Label: " + root.getLabel());
		System.out.println("Prompt: " + root.getPrompt());
		System.out.println("Message: " + root.getMessage());
		System.out.println();

		if (root.getLeftNode() != null) {
			preOrderHelper(root.getLeftNode());
		}

		if (root.getMiddleNode() != null) {
			preOrderHelper(root.getMiddleNode());
		}

		if (root.getRightNode() != null) {
			preOrderHelper(root.getRightNode());
		}
	}

	/**
	 * Initiates a session that guides the user through the tree based on their
	 * choices.
	 */

	public void beginSession() {
		Scanner scanner = new Scanner(System.in);
		TreeNode current = root;

		while (current != null) {
			if (!current.getLabel().equalsIgnoreCase("Root")) {
				System.out.println(current.getPrompt());

			}
			System.out.println(current.getMessage());

			if (current.getLeftNode() != null) {
				System.out.println("1) " + current.getLeftNode().getPrompt());
			}
			if (current.getMiddleNode() != null) {
				System.out.println("2) " + current.getMiddleNode().getPrompt());
			}
			if (current.getRightNode() != null) {
				System.out.println("3) " + current.getRightNode().getPrompt());
			}

			System.out.println("0) Exit session");

			String userInput = scanner.nextLine();

			if (userInput.equals("0") || current.isLeaf()) {
				System.out.println("Exiting session and returning to TreeDriver menu.");
				System.out.println("Thank you for using this automated help service!");

				break;
			}

			else {
				if (userInput.equals("1") && current.getLeftNode() != null) {
					current = current.getLeftNode();
				} else if (userInput.equals("2") && current.getMiddleNode() != null) {
					current = current.getMiddleNode();
				} else if (userInput.equals("3") && current.getRightNode() != null) {
					current = current.getRightNode();
				} else {
					System.out.println("Invalid choice. Exiting session.");
					current = null;
				}
			}
		}

		System.out.println("Session ended.");
		scanner.close();
	}

	/**
	 * Parses input from a file scanner and constructs a Tree object.
	 *
	 * @param fileScanner The scanner used to read from the input file
	 * @return A new Tree object constructed from the file input, or null if not
	 *         successful
	 */
	public static Tree parseNode(Scanner fileScanner) {
		Tree newTree = new Tree();
		boolean treeSuccessfullyCreated = false;

		while (fileScanner.hasNextLine()) {
			String label = fileScanner.nextLine();
			String prompt = fileScanner.nextLine();
			String message = fileScanner.nextLine();

			// Create and add the new node
			boolean added = newTree.addNode(label, prompt, message, "");

			if (!added) {
				System.out.println("Node was not added: " + label);
			}

			if (fileScanner.hasNextLine()) {
				String childLine = fileScanner.nextLine();

				while (!childLine.equals(label)) {
					String childLabel = childLine;
					String childPrompt = fileScanner.nextLine();
					String childMessage = fileScanner.nextLine();

					boolean childAdded = newTree.addNode(childLabel, childPrompt, childMessage, label);

					if (!childAdded) {
						System.out.println("Child node was not added: " + childLabel);
					}

					if (!fileScanner.hasNextLine()) {
						break;
					}

					childLine = fileScanner.nextLine();
				}
			}

			treeSuccessfullyCreated = true;
		}

		if (treeSuccessfullyCreated) {
			return newTree;
		} else {
			System.out.println("Tree was not created");
			return null;
		}
	}

	/**
	 * Gets the root node of the tree.
	 *
	 * @return The root node of the tree
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * Sets the root node of the tree.
	 *
	 * @param root The new root node to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * Gets the session exit flag.
	 *
	 * @return The session exit flag
	 */
	public boolean getSessionExitFlag() {
		return sessionExitFlag;
	}

}
