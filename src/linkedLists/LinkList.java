package linkedLists;

import java.util.LinkedList;

public class LinkList {

	public static void main(String[] args) {
		Node list = new Node(0);
		list.append(1);
		list.append(2);
		list.append(3);
		//list.insertFirst(-1);
		System.out.println(list.toString());
		
		LinkedList<Integer> li = new LinkedList<Integer>();
		//li.
	}	
	
	public static class Node{
		Object value;
		Node next = null;
		
		public Node(Object o){
			value = o;
		}
		
		public void append(Object o){
			Node newnode = new Node(o);
			Node n = this;
			while (n.next != null){
				n = n.next;
			}
			n.next = newnode;			
		}
		
		/*public void insertFirst(Object o){
			Node newnode = new Node(o);
			newnode.next = this;	
			this.value = newnode.value;
			this.next = newnode.next;
		}*/
		
		public String toString(){
			String s = "";
			Node n = this;
			while (n.next != null){
				s += String.valueOf(n.value) + " -> ";
				n = n.next;
			}
			return s + String.valueOf(n.value);
		}
	}


}
