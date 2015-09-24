package games;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
	public static boolean exitFound = false;
	public static List<Point> path = new ArrayList<Point>();
	public static char[][] maze;
	public static Point start;

	public static void main(String[] args) throws FileNotFoundException {
		maze = readMaze("maze.txt");
		boolean stop = false;
		for (int i = 0; i < maze.length && !stop; i++)
			for (int j = 0; j < maze[0].length; j++)
				if (maze[i][j] == 'S') {
					start = new Point(i, j);
					stop = true;
					break;
				}

		move(start.x, start.y);
		if (exitFound) {
			System.out.println("Exit Was Found! The path is: ");
			//System.out.println(path);
			printMaze();
		} else {
			System.out.println("There is no way out of the maze, I promise!");
		}
	}

	private static void move(int i, int j) {
		if (!exitFound) {
			if (i < 0 || j < 0 || i >= maze.length || j >= maze[0].length) {
				return;
			} else if (maze[i][j] == 'F') {
				exitFound = true;
				return;
			} else if (maze[i][j] == '+' || maze[i][j] == '-'
					|| maze[i][j] == '|' || maze[i][j] == '.') {
				return;
			} else {
				path.add(new Point(i, j));
				maze[i][j] = '.';
				move(i, j + 1);
				if (!exitFound)
					move(i, j - 1);
				if (!exitFound)
					move(i - 1, j);
				if (!exitFound)
					move(i + 1, j);
			}
		} 
	}

	public static char[][] readMaze(String filename)
			throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
		List<String> strings = new ArrayList<String>();
		while (sc.hasNext()) {
			strings.add(sc.nextLine());
		}
		sc.close();
		char[][] maze = new char[strings.size()][strings.get(0).length()];

		for (int i = 0; i < strings.size(); i++) {
			maze[i] = strings.get(i).toCharArray();
		}
		return maze;
	}
	
	public static void printMaze(){
	    maze[start.x][start.y] = 'S';
	    for (char[] line : maze){
	        System.out.println(line);
	    }
	}

}

class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return String.format("%2d, %2d\n", x, y);
	}
}
