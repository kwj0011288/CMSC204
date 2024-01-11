import java.util.ArrayList;

/*
 * @author Wonjae Kim
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	private TreeNode<String> root;

	public MorseCodeTree() {
		root = new TreeNode<>();
	}

	/**
	 * Returns a reference to the root
	 * 
	 * @return reference to root
	 */
	public TreeNode<String> getRoot() {
		return root;
	}

	/**
	 * sets the root of the Tree
	 * 
	 * @param newNode a TreeNode<T> that will be the new root
	 */
	public void setRoot(TreeNode<String> newNode) {
		this.root = new TreeNode<String>(newNode);
	}

	/**
	 * Adds result to the correct position in the tree based on the code This method
	 * will call the recursive method addNode
	 * 
	 * @param code the code for the new node to be added
	 * 
	 */
	public void insert(String code, String letter) {
		addNode(root, code, letter);
	}

	/**
	 * This is a recursive method that adds element to the correct position in the
	 * tree based on the code.
	 * 
	 * @param root   the root of the tree for this particular recursive instance of
	 *               addNode
	 * @param code   the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {

		if (code.length() == 0) {
			root.dataNode = letter;
			return;
		}

		if (code.charAt(0) == '.') {
			if (root.left == null) {
				root.left = new TreeNode<>();
			}
			addNode(root.left, code.substring(1), letter);
		} else if (code.charAt(0) == '-') {
			if (root.right == null) {
				root.right = new TreeNode<>();
			}
			addNode(root.right, code.substring(1), letter);
		}
	}

	/**
	 * Fetch the data in the tree based on the code This method will call the
	 * recursive method fetchNode
	 * 
	 * @param code the code that describes the traversals within the tree
	 * @return the result that corresponds to the code
	 */
	@Override
	public String fetch(String code) {
		if (root == null) {
			return null;
		}
		return fetchNode(root, code);
	}

	/**
	 * This is the recursive method that fetches the data of the TreeNode that
	 * corresponds with the code
	 * 
	 * @param root the root of the tree for this particular recursive instance of
	 *             addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if (code.isEmpty()) {
			return root.getData();
		}

		if (code.charAt(0) == '.') {
			if (root.left == null) {
				return null;
			}
			return fetchNode(root.left, code.substring(1));

		} else if (code.charAt(0) == '-') {
			if (root.right == null) {
				return null;
			}
			return fetchNode(root.right, code.substring(1));

		}

		return null;
	}

	/**
	 * This operation is not supported for a LinkedConverterTree
	 * 
	 * @param data data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This operation is not supported for a LinkedConverterTree
	 * 
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method builds the LinkedConverterTree by inserting TreeNodes<T> into
	 * their proper locations
	 * 
	 */
	@Override
	public void buildTree() {

		insert(" ", " ");
		insert(".-", "a");
		insert("-...", "b");
		insert("-.-.", "c");
		insert("-..", "d");
		insert(".", "e");
		insert("..-.", "f");
		insert("--.", "g");
		insert("....", "h");
		insert("..", "i");
		insert(".---", "j");
		insert("-.-", "k");
		insert(".-..", "l");
		insert("--", "m");
		insert("-.", "n");
		insert("---", "o");
		insert(".--.", "p");
		insert("--.-", "q");
		insert(".-.", "r");
		insert("...", "s");
		insert("-", "t");
		insert("..-", "u");
		insert("...-", "v");
		insert(".--", "w");
		insert("-..-", "x");
		insert("-.--", "y");
		insert("--..", "z");

	}

	/**
	 * Returns an ArrayList of the items in the linked converter Tree in LNR
	 * (Inorder) Traversal order Used for testing to make sure tree is built
	 * correctly
	 * 
	 * @return an ArrayList of the items in the linked Tree
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<>();
		LNRoutputTraversal(root, list);
		return list;
	}

	/**
	 * The recursive method to put the contents of the linked converter tree in an
	 * ArrayList<T> LNR (Inorder)
	 * 
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR
	 *             order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root == null) {
			return;
		}

		if (root.left != null) {
			LNRoutputTraversal(root.left, list);
		}

		// Add an empty string if the data is null or empty
		if (root.getData() == null || root.getData().isEmpty()) {
			list.add("");
		} else {
			list.add(root.getData());
		}

		if (root.right != null) {
			LNRoutputTraversal(root.right, list);
		}

	}

}
