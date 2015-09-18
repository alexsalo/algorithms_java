package hash;

import java.util.LinkedList;
import java.util.Queue;

public class AlexHashTable {
	public static void main(String[] args) {
		ListHashTable ht = new ListHashTable();
		ht.put(5, "Excellent");
		ht.put(4, "Good");
		ht.put(3, "OK");
		ht.put(3, "Tolerable");  // Should update the OK
		ht.put(35, "HowAbout35");  // Should add item to the linked list
		System.out.println(ht);
		
		ht.put(2, "Bad");
		ht.put(1, "Fail");
		System.out.println(ht);
	}
}

class Node {
	public int key;
	public String value;
	public Node next;

	public Node() {
		this.value = null;
		this.next = null;
	}

	public Node(int key, String value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
}

class SingleLinkedList {
	Node head;

	public SingleLinkedList() {
		head = new Node();
	}

	public void append(int key, String value) {
		if (head.value == null) {
			head.key = key;
			head.value = value;
		} else {
			Node x = head;
			while (x.next != null) {
				x = x.next;
			}
			Node newNode = new Node(key, value);
			x.next = newNode;
		}
	}

	public String get(int key) {
		Node x = head;
		while (x.next != null && x.key != key)
			x = x.next;
		if (x.key == key)
			return x.value;
		else
			return null;
	}

	public int remove(int key) {
		Node x = head;
		Node prev = head;
		int depth = 0;
		while (x.key != key && x.next != null) {
			prev = x;
			x = x.next;
			depth++;
		}
		if (x.key == key) {
			// remove
			prev.next = x.next;
			return depth;
		} else { // did not find
			return -1;
		}
	}

	public boolean contains(int key) {
		Node x = head;
		while (x.next != null) {
			if (x.key == key)
				return true;
			x = x.next;
		}
		return x.key == key;
	}

	public void update(int key, String value) {
		Node x = head;
		while (x.key != key)
			x = x.next;
		x.value = value;
	}
	
	public Iterable<Integer> keys(){
		Queue<Integer> queue = new LinkedList<Integer>();
		Node x = head;
		while (x.next != null){
			queue.add(x.key);
			x = x.next;
		}
		queue.add(x.key);
		return queue;
	}

	public String toString() {
		String result = "";
		Node x = head;
		while (x.next != null) {
			result += String.valueOf(x.key) + ": " + x.value + ", ";
			x = x.next;
		}
		return result + String.valueOf(x.key) + ": " + x.value;
	}
}

class ListHashTable {
	private static int PRIME = 31;
	private static int INIT_SIZE = 4;
	private static int avg_list_length = 1;
	private int table_size;
	private int num_entries;

	private SingleLinkedList[] table;

	public ListHashTable() {
		this(INIT_SIZE);
	}
	
	public ListHashTable(int size) {
		table = new SingleLinkedList[size];
		this.table_size = size;
	}

	private int hash(int key) {
		return (key % PRIME) % table_size;
	}
	
	public int size(){
		return table_size;
	}

	public void put(int key, String value) {
		if (table[hash(key)] == null) {
			table[hash(key)] = new SingleLinkedList();
			table[hash(key)].append(key, value);
			num_entries++;
		} else {
			if (table[hash(key)].contains(key))
				table[hash(key)].update(key, value);
			else{
				table[hash(key)].append(key, value);
				num_entries++;
			}
		}
		
		if (num_entries > avg_list_length * table_size){
			resize(2 * table_size);
		}
	}
	
	private void resize(int newSize){		
		ListHashTable newtable = new ListHashTable(newSize);
		
		for (int i = 0; i < table_size; i++){
			for (int key : table[i].keys()){
				newtable.put(key, table[i].get(key));
			}
		}
		
		this.table_size = newtable.table_size;
		this.num_entries = newtable.num_entries;
		this.table = newtable.table;
		
	}

	public String get(int key) {
		return table[hash(key)].get(key);
	}

	public String toString() {
		String result = "Hash Table of size: " + String.valueOf(table_size) + "\n";
		for (int i = 0; i < table_size; i++) {
			if (table[i] != null) {
				result += table[i] + "\n";
			}
		}
		return result;
	}
}
