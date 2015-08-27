package search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSTtoLL {
	
	private static class BiNode{
		public int data;
		public BiNode left, right;
		
		public BiNode(int val){
			this.data = val;
			this.left = null;
			this.right = null;
		}
	}
	
	public static class binarySearchTree{
		public BiNode root;
		
		public void add(int val){
			if (root == null)
				root = new BiNode(val);
			else{
				BiNode z = new BiNode(val);
				BiNode x = root, parent = root;
				while (x != null){
					parent = x;
					if (val < x.data) x = x.left;
					else if (val > x.data) x = x.right;
					else{
						x.data = val;
						return;
					}
				}
				if (val < parent.data)	parent.left = z;
				else					parent.right = z;
				return;
			}
		}
		
		public int find(int val){
			BiNode x = root;
			while (x != null){
				if (val < x.data) x = x.left;
				else if (val > x.data) x = x.right;
				else return x.data;
			}
			return -1;
		}
		
		public String toString(){
			BiNode x = root;
			Stack<BiNode> stack = new Stack<BiNode>();
			Queue<Integer> queue = new LinkedList<Integer>();
			while (x != null || !stack.isEmpty()){
				if (x != null){
					stack.push(x);
					x = x.left;
				}else{
					x = stack.pop();
					queue.add(x.data);
					x = x.right;
				}				
			}
			return queue.toString();
		}
		
		public String rotateAndPrint(){
			BiNode x = toLinkedList(root);
			String s = "";
			while (x != null){
				s += x.data + " ";
				x = x.right;
			}
			return s;
		}
		
		private BiNode toLinkedList(BiNode x){
			if (x != null){
				BiNode leftPart = toLinkedList(x.left);
				BiNode rightPart = toLinkedList(x.right);
				return merge(leftPart, x, rightPart);
			}else
				return null;
		}

		private BiNode merge(BiNode leftPart, BiNode mid, BiNode rightPart){
			if (leftPart == null && rightPart == null) return mid;
			else if (leftPart == null){
				mid.right = rightPart;
				return mid;
			}
			else if (rightPart == null){
				BiNode x = leftPart;
				while (x.right != null)
					x = x.right;
				x.right = mid;
				return leftPart;
			}
			
			BiNode x = leftPart;
			while (x.right != null)
				x = x.right;
			x.right = mid;
			mid.right = rightPart;
			return leftPart;
		}
	}

	public static class BiNodeLinkedList{
		private BiNode head;
		
		public void addFirst(int val){
			if (head == null) 
				head = new BiNode(val);
			else{
				BiNode node = new BiNode(val);
				head.left = node;
				node.right = head;
				head = node;
			}
		}
		
		public String toString(){
			String s = "";
			BiNode x = head;
			while (x.right != null){
				s += x.data + " ";
				x = x.right;
			}
			s += x.data;
			return s;
		}
	}

	public static void main(String[] args) {
		BiNodeLinkedList list = new BiNodeLinkedList();
		for (int i = 0; i < 10; i++)
			list.addFirst(i);
		System.out.println(list.toString());
		
		binarySearchTree bst = new binarySearchTree();
		for (int i : new int[]{50, 2, 43, 101, 6, 100, 8, 20, 3, 15, -16, -15, 101, })
			bst.add(i);
		System.out.println(bst.find(50));
		System.out.println(bst.toString());
		
		System.out.println(bst.rotateAndPrint());
	}

}
