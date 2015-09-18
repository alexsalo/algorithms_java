package strings;

import java.util.List;

public class RetrievalTree {
	// Assuming we have this tree already filled out for us
	public static RetrievalTreeNode retrievalTree;

	public static void main(String[] args) {
		retrievalTree = new RetrievalTreeNode();
	}

	public static class RetrievalTreeNode {
		public boolean isWord;
		public char letter;
		public List<RetrievalTreeNode> leaves;
		public RetrievalTreeNode parent;
		public int depth;

		// To create root
		public RetrievalTreeNode() {
			this.parent = null;
			this.depth = 0;
		}

		public RetrievalTreeNode(char letter, boolean isWord) {
			this.letter = letter;
			this.isWord = isWord;
		}
	}

	

	// Assuming we used only words from dictionary
	public static String restoreSpaces(String input) {
		char[] chars = input.toCharArray();
		int current = 0;
		String result = "";

		while (current < chars.length) {
			RetrievalTreeNode root = retrievalTree;
			while (root.leaves.contains(chars[current])) {
				root = root.leaves.get(chars[current]);
			}
			while (!root.isWord) {
				root = root.parent;
			}
			result += input.substring(current, root.depth) + " ";
			current = root.depth;
		}
		return result;
	}
}
