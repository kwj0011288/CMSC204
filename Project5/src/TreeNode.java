/*
 * @author Wonjae Kim
 */

/*
 * Class to represent a generic tree node
 */
public class TreeNode<T> {
	public T dataNode;
	public TreeNode<T> left;
	public TreeNode<T> right;

	/*
	 * Default constructor
	 */
	public TreeNode() {

	}

	/*
	 * Constructor for TreeNode class
	 */
	public TreeNode(T dataNode) {
		this.dataNode = dataNode;
		this.left = null;
		this.right = null;

	}

	/*
	 * Constructor for TreeNode class reference to the left and right children
	 */
	public TreeNode(TreeNode<T> left, TreeNode<T> right, T data) {
		this.left = left;
		this.right = right;
		this.dataNode = data;

	}

	/*
	 * copy constructor for TreeNode class Create a new TreeNode deep copies
	 */
	public TreeNode(TreeNode<T> node) {
		this(node.left, node.right, node.getData());

	}

	/*
	 * get data
	 */
	public T getData() {
		return dataNode;

	}
}
