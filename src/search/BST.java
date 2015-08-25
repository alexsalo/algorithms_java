package search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST {
	private Node root;
	
	private class Node{
		private int key;
		private int val;
		private Node left, right;
		
		public Node(int key, int val){
			this.key = key;
			this.val = val;
		}
	}
	
	public void put(int key, int val){
		Node z = new Node(key, val);
		if (root == null){
			root = z;
			return;
		}
		
		Node parent = null, x = root;
		while (x != null){
			parent = x;
			if 		(key < x.key) x = x.left;
			else if (key > x.key) x = x.right;
			else {
				x.key = key;
				return;
			}
		}
		if (key < parent.key) parent.left = z;
		else 				  parent.right = z;
	}
	
	public int get(int key){
		Node x = root;
		while (x != null){
			if		(key < x.key) x = x.left;
			else if (key > x.key) x = x.right;
			else return x.val;
		}
		return -1;
	}
	
	public Queue<Integer> keys(){
		Stack<Node> stack = new Stack<Node>();
		Queue<Integer> queue = new LinkedList<Integer>();
		Node x = root;
		while (x != null || !stack.isEmpty()){
			if (x != null){
				stack.push(x);
				x = x.left;
			}else{
				x = stack.pop();
				queue.add(x.key);
				x = x.right;
			}			
		}
		return queue;
	}
	

	public static void main(String[] args) {
		BST bst = new BST();
		for (int i = 0; i < 10; i++)
			bst.put(i, i);
		for (int key : bst.keys())
			System.out.println(key + " " + bst.get(key));

	}

}
