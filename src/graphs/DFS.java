package graphs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DFS {
	final static String filename = "graph.txt";
	static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
	static Boolean visited[];
	static Queue<Integer> queue = new LinkedList<Integer>();

	public static void main(String[] args) {
		readGraph(filename);
		System.out.println(graph);
		dfs(0);
		System.out.println();
		falsifyVisited();
		bfs(0);
	}
	
	static void dfs(int v){
		visited[v] = true; 
		System.out.print("Visiting " + String.valueOf(v) + ": ");
		printBoolArray();
		for (int u : graph.get(v)){
			if (!visited[u]){
				dfs(u);
			}
		}
	}
	
	static void bfs(int v){
		visited[v] = true; 
		System.out.print("Visiting " + String.valueOf(v) + ": ");
		printBoolArray();
		for (int u : graph.get(v)){
			if (!visited[u] && !queue.contains(u)){
				queue.add(u);
			}			
		}
		if (!queue.isEmpty()){
			bfs(queue.poll());		
		}		
	}
	
	static void readGraph(String filename){
		// read input
		Scanner sc = null;
		try {
			sc = new Scanner(new BufferedReader(new FileReader(filename)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (sc.hasNext()){
			String line = sc.nextLine();
			Scanner sc2 = new Scanner(line);
			int v = sc2.nextInt();
			ArrayList<Integer> edges = new ArrayList<Integer>();
			while (sc2.hasNext()){
				edges.add(sc2.nextInt());
			}
			graph.put(v, edges);
			sc2.close();
		}
		sc.close();		
		visited = new Boolean[graph.size()];		
		falsifyVisited();
	}

	static void printBoolArray(){
		for (int i =0; i< visited.length; i ++){
			System.out.print(visited[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	static void falsifyVisited(){
		for (int i =0; i< visited.length; i ++){
			visited[i] = false;
		}
	}
}
