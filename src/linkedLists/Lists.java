package linkedLists;

public class Lists {

	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList();
		for (int i = 0; i < 6; i++) {
			list.addFirst(i);
		}
		for (int i = 0; i < 6; i++) {
			list.append(i);
		}

		System.out.println(list);
		System.out.println(list.sum());
		
		list.insertAt(3, 33);
		System.out.println(list);
		
		Stack stack = new Stack();
		for (int i = 0; i < 6; i++) {
			stack.add(i);
		}
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
	}

	public static class Node {
		public Object value;
		public Node next;

		public Node(Object value) {
			this.value = value;
			next = null;
		}
	}

	public static class SinglyLinkedList {
		private Node head;
		private Node tail;

		public SinglyLinkedList() {
			head = null;
			tail = null;
		}

		public void addFirst(Object value) {
			Node node = new Node(value);
			if (head == null) {
				head = node;
				tail = node;
			} else {
				node.next = head;
				head = node;
			}
		}
		
		public void append(Object value){
			Node node = new Node(value);
			if (tail == null){
				head = node;
				tail = node;
			}else{
				tail.next = node;
				tail = node;
			}
		}
		
		public void insertAt(int i, Object value){
			Node node = head; //0
			while (i > 1){
				node = node.next;
				i--;
			}
			Node newNode = new Node(value);
			newNode.next = node.next;
			node.next = newNode;
		}

		public int sum() { // check if int
			int sum = 0;
			Node node = head;
			while (node.next != null) {
				sum += (int) node.value;
				node = node.next;
			}
			sum += (int) node.value;
			return sum;
		}

		public String toString() {
			Node node = head;
			String sb = "";
			while (node.next != null) {
				sb += String.valueOf(node.value) + " -> ";
				node = node.next;
			}
			sb += String.valueOf(node.value);
			return sb;
		}
	}

	public static class Stack{
		Node top;
		
		public Stack(){
			top = null;
		}
		
		public void add(Object o){
			Node newnode = new Node(o);
			if (top == null){
				top = newnode;
				top.next = null;
			}else{
				newnode.next = top;
				top = newnode;
			}
		}
		
		public Object pop(){
			if (top != null){
				Object value = top.value;
				Node node = top.next;
				top = node;
				return value;
			}else{
				return null;
			}			
		}
		
		public String toString(){
			Node node = top;
			String sb = "";
			while (node.next != null){
				sb += String.valueOf(node.value) + " -> ";
				node = node.next;
			}
			sb += String.valueOf(node.value);
			return sb;
		}
	}
}
