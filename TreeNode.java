/**
 * The TreeNode class represents a node in the Tree class. Each node can have up
 * to three child nodes (left, middle, and right). It holds information such as
 * label, message, and prompt, and provides methods to manipulate and access
 * these properties.
 * 
 * @author Shiv Kanani 
 * 		   SBU ID: 115171965 
 * 		   Homework #4 for CSE 214, Summer 2023
 */
public class TreeNode {

	private TreeNode leftNode; // Reference to the left child node
	private TreeNode rightNode; // Reference to the right child node
	private TreeNode middleNode; // Reference to the middle child node
	private String label; // Label associated with the node
	private String message; // Message associated with the node
	private String prompt; // Prompt associated with the node

	/**
	 * Constructs a TreeNode object with default values for child nodes, label,
	 * message, and prompt.
	 */
	public TreeNode() {
		label = null;
		message = null;
		prompt = null;
		setLeftNode(null);
		setRightNode(null);
		setMiddleNode(null);
	}

	/**
	 * Constructs a TreeNode object with provided values for label, message, and
	 * prompt.
	 *
	 * @param label   The label associated with the node
	 * @param message The message associated with the node
	 * @param prompt  The prompt associated with the node
	 */
	public TreeNode(String label, String message, String prompt) {
		this.label = label;
		this.message = message;
		this.prompt = prompt;
	}

	/**
	 * Gets the label associated with the node.
	 *
	 * @return The label associated with the node
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Gets the message associated with the node.
	 *
	 * @return The message associated with the node
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the prompt associated with the node.
	 *
	 * @return The prompt associated with the node
	 */
	public String getPrompt() {
		return prompt;
	}

	/**
	 * Checks if the current node is a leaf node (has no children).
	 *
	 * @return True if the node is a leaf, false otherwise
	 */
	public boolean isLeaf() {
		return (leftNode == null && middleNode == null && rightNode == null);
	}

	/**
	 * Gets the left child node.
	 *
	 * @return The left child node
	 */
	public TreeNode getLeftNode() {
		return leftNode;
	}

	/**
	 * Sets the left child node.
	 *
	 * @param leftNode The left child node to set
	 */
	public void setLeftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}

	/**
	 * Gets the right child node.
	 *
	 * @return The right child node
	 */
	public TreeNode getRightNode() {
		return rightNode;
	}

	/**
	 * Sets the right child node.
	 *
	 * @param rightNode The right child node to set
	 */
	public void setRightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}

	/**
	 * Gets the middle child node.
	 *
	 * @return The middle child node
	 */
	public TreeNode getMiddleNode() {
		return middleNode;
	}

	/**
	 * Sets the middle child node.
	 *
	 * @param middleNode The middle child node to set
	 */
	public void setMiddleNode(TreeNode middleNode) {
		this.middleNode = middleNode;
	}

	/**
	 * Sets the label associated with the node.
	 *
	 * @param label The label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Sets the message associated with the node.
	 *
	 * @param message The message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Sets the prompt associated with the node.
	 *
	 * @param prompt The prompt to set
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
}
