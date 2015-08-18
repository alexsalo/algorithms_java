package graphs;

public class Trees {

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.add(10);
		bst.add(6);
		bst.add(12);
		bst.add(3);
		bst.add(8);
		bst.add(11);
		bst.add(15);
		bst.add(11);
		bst.add(2);
		bst.add(16);
		bst.add(17);
		
		System.out.println(bst);
		System.out.println("BST Height is: " + bst.getHeight());
		System.out.println(bst.isBalanced());
	}
	
	public static class TreeNode{
		public int value;
		public TreeNode left;
		public TreeNode right;
		
		public TreeNode(int value){
			this.value = value;
			left = null;
			right = null;
		}
	}

	public static class BinarySearchTree{
		static TreeNode root;
		int treeDepth = 5;
		public BinarySearchTree(){
			root = null;
		}
		
		public void add(int value){
			TreeNode newnode = new TreeNode(value);
			if (root == null){
				root = newnode;
			}else{
				TreeNode node = root;
				TreeNode previous = root;
				while (node != null){
					//go down find correct leaf
					previous = node;
					node = value <= node.value ? node.left : node.right; 
				}
				if (value <= previous.value){
					previous.left = newnode;
				}else{
					previous.right = newnode;
				}
			}
		}
		
		public boolean isBalanced(){
			return isBalanced(root);
		}
		
		private static boolean isBalanced(TreeNode node){ // O(N)
			if (node == null){
				return true;
			}
			int leftHeight = checkHeight(node.left);
			if (leftHeight == -1){
				return false;
			}
			int rightHeight = checkHeight(node.right);
			if (rightHeight == -1){
				return false;
			}
				
			if (Math.abs(leftHeight - rightHeight) > 1){
				return false;
			}else{
				return isBalanced(node.left) && isBalanced(node.right);
			}					
		}
		
		private static int checkHeight(TreeNode node){
			if (node == null){
				return 0;
			}else{
				int leftHeight = getHeight(node.left);
				int rightHeight = getHeight(node.right);
				int diff = leftHeight - rightHeight;
				if (Math.abs(diff) > 1){
					return -1;
				}else{
					return leftHeight > rightHeight	 ? leftHeight + 1 : rightHeight + 1;
				}
			}
		}
		
		public int getHeight(){
			return getHeight(root);
		}
		
		private static int getHeight(TreeNode node){
			if (node == null){
				return 0;
			}else{
				int leftHeight = getHeight(node.left);
				int rightHeight = getHeight(node.right);
				return leftHeight > rightHeight	 ? leftHeight + 1 : rightHeight + 1;
			}
		}
		
		public String toString(){
			String sb = "";
			for (int i = 0; i < treeDepth; i++){
				sb += "Level " + String.valueOf(i) + ": ";
				sb += printLevel(root, i);
				sb += "\n";
			}
			return sb;
		}
		
		private String printLevel(TreeNode node, int level){
			if (node == null)
				return "";
			if (level <= 0)
				return String.valueOf(node.value) + " ";
			else{ //level > 0
				String leftStr = printLevel(node.left, level - 1);
				String rightStr = printLevel(node.right, level - 1);
				return leftStr + rightStr;
			}				
		}		
		
	}

}
